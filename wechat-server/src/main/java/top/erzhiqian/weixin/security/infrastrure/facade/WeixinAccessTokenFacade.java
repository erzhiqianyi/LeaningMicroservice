package top.erzhiqian.weixin.security.infrastrure.facade;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;
import top.erzhiqian.weixin.security.dto.AccessTokenDTO;
import top.erzhiqian.weixin.security.infrastrure.facade.param.AccessTokenQueryParam;

@FeignClient(name = "weixin-client", url = "${weixin.base-url}", configuration = WeixinResponseDecoder.class)
public interface WeixinAccessTokenFacade {

    @GetMapping("token")
    AccessTokenDTO getAccessToken(@SpringQueryMap AccessTokenQueryParam query);

}
