package top.erzhiqian.auth.authenticate.infrastructure.facade;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;
import top.erzhiqian.auth.authenticate.dto.WechatCode2SessionDto;

@FeignClient("wechat-server")
public interface WechatFacade {

    @GetMapping("login/code")
    String loginByByCode(@SpringQueryMap WechatCode2SessionDto code);

}
