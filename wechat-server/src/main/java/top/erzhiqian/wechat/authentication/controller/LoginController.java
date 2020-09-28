package top.erzhiqian.wechat.authentication.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Log4j2
public class LoginController {

    @GetMapping("login/code")
    public String loginByByCode(String code) {
        log.info("授权码: " + code);
        return code;
    }

}
