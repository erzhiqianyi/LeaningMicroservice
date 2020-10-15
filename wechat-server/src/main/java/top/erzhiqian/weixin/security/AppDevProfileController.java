package top.erzhiqian.weixin.security;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;
import top.erzhiqian.weixin.lang.LetterOrDigitsString;
import top.erzhiqian.weixin.security.app.DevProfileApp;
import top.erzhiqian.weixin.security.client.cmd.OpenAppDevProfileCmd;
import top.erzhiqian.weixin.security.client.vo.AppDevProfileVO;
import top.erzhiqian.weixin.security.domain.entity.AppDevProfile;
import top.erzhiqian.weixin.security.domain.entity.WeixinAppId;

@RestController
@Log4j2
public class AppDevProfileController {

    private DevProfileApp devProfileApp;

    public AppDevProfileController(DevProfileApp devProfileApp) {
        this.devProfileApp = devProfileApp;
    }

    /**
     * 生成指定长度的只含字母或数字的随机字符串
     * 2020/10/15 13:35
     * 二之前一
     */
    @GetMapping("util/letter_or_digits")
    public LetterOrDigitsString generateLetterOrDigitsString(Integer min, Integer max) {
        if (null == min || null == max) {
            throw new IllegalArgumentException(" min or max length can't be null.");
        }
        return LetterOrDigitsString.stringBetween(min, max);
    }


    @PostMapping("/devprofile/open/{appId}")
    public AppDevProfileVO openDevProfile(@RequestBody OpenAppDevProfileCmd cmd,
                                          @PathVariable("appId") String appId) {
        WeixinAppId app = WeixinAppId.app(appId);
        AppDevProfile profile = devProfileApp.openDevProfile(app, cmd);
        return AppDevProfileVO.from(profile);
    }

}
