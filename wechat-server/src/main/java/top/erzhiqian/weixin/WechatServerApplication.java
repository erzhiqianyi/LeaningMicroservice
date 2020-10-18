package top.erzhiqian.weixin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = "top.erzhiqian.weixin.security.infrastrure.facade")
public class WechatServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(WechatServerApplication.class, args);
    }


}
