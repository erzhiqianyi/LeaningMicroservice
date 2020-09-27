package top.erzhiqian.register;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
//注解开启 Eureka 服务治理中心
@EnableEurekaServer
public class EurekaRegisterServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurekaRegisterServerApplication.class, args);
	}

}
