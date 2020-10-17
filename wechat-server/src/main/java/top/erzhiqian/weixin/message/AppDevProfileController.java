package top.erzhiqian.weixin.message;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;
import top.erzhiqian.weixin.lang.LetterOrDigitsString;
import top.erzhiqian.weixin.message.app.DevProfileApp;
import top.erzhiqian.weixin.message.client.cmd.OpenAppDevProfileCmd;
import top.erzhiqian.weixin.message.client.vo.AppDevProfileVO;
import top.erzhiqian.weixin.message.domain.entity.AppDevProfile;
import top.erzhiqian.weixin.message.domain.valueobject.WeixinAppId;

@RestController
@Log4j2
public class AppDevProfileController {

    private DevProfileApp devProfileApp;

    public AppDevProfileController(DevProfileApp devProfileApp) {
        this.devProfileApp = devProfileApp;
    }

    /**
     * 生成指定长度的只含字母或数字的随机字符串
     *
     * @param max 最大长度
     * @param min 最小长度
     * @return 生成指定长度的只含字母或数字的随机字符串
     * @author 二之前一
     */
    @GetMapping("util/letter_or_digits")
    public LetterOrDigitsString generateLetterOrDigitsString(Integer min, Integer max) {
        if (null == min || null == max) {
            throw new IllegalArgumentException(" min or max length can't be null.");
        }
        return LetterOrDigitsString.stringBetween(min, max);
    }


    /**
     * 添加微信服务器配置,需要和微信公众平台或小程序平台添加开启的服务器配置一致
     *
     * @param cmd 微信服务器配置
     * @param appId 小程序或公众号appId
     * @return
     */
    @PostMapping("/devprofile/open/{appId}")
    public AppDevProfileVO openDevProfile(@RequestBody OpenAppDevProfileCmd cmd,
                                          @PathVariable("appId") String appId) {
        WeixinAppId app = WeixinAppId.app(appId);
        AppDevProfile profile = devProfileApp.openDevProfile(app, cmd);
        return AppDevProfileVO.from(profile);
    }

}
