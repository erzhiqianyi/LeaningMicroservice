package top.erzhiqian.auth.authenticate.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;


@RestController
@Log4j2
public class WechatLoginController {

    private RestTemplate restTemplate;

    public WechatLoginController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/login/wechat/code")
    public String loginByWechatSessionCode(String code) {
        log.info("去小程序服务进行授权 " + code);
        String url = "http://wechat-server/login/code?code={code}";
        Map<String, String> param = new HashMap<>();
        param.put("code", code);
        String result = restTemplate.getForObject(url, String.class, param);
        return result;
    }
}
