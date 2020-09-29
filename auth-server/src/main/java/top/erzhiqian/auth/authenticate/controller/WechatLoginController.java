package top.erzhiqian.auth.authenticate.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import top.erzhiqian.auth.authenticate.dto.WechatCode2SessionDto;
import top.erzhiqian.auth.authenticate.infrastructure.facade.WechatFacade;


@RestController
@Log4j2
public class WechatLoginController {


    private WechatFacade wechatFacade;

    public WechatLoginController(WechatFacade wechatFacade) {
        this.wechatFacade = wechatFacade;
    }

    @GetMapping("/auth/login/wechat/code")
    public String loginByWechatSessionCode(String code) {
        log.info("去小程序服务进行授权 " + code);
        String result = wechatFacade.loginByByCode(new WechatCode2SessionDto(code));
        return result;
    }
}
