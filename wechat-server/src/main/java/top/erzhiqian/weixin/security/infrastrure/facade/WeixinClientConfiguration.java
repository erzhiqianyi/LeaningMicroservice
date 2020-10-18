package top.erzhiqian.weixin.security.infrastrure.facade;

import feign.codec.Decoder;
import org.springframework.context.annotation.Bean;

public class WeixinClientConfiguration {

    @Bean
    public Decoder feignDecoder() {
        return new WeixinResponseDecoder();
    }
}
