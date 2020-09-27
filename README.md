# LeaningMicroservice
学习微服务记录，使用SpringBoot及相关Spring组件搭建微服务。并搭建一个兼容多小程序的小程序服务应用。

## 搭建公网访问环境
可以使用花生壳进行内网穿透，通过域名访问本地服务。

本地再安装一个nginx，进行转发。


## 搭建服务治理中心

- 创建项目
使用 [spring initializr](https://start.spring.io/) 创建一个　SpringBoot 项目，添加 ```Eureka Server```,生成项目即可。

- 启用Eureka服务治理中心
在启动类上，添加 ```@EnableEurekaServer``` 

```java
@SpringBootApplication
//注解开启 Eureka 服务治理中心
@EnableEurekaServer
public class EurekaRegisterServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurekaRegisterServerApplication.class, args);
	}

}
```

- 运行应用
运行启动的类的main() 方法，启动应用。应用默认端口为8080。

- 修改配置
修改项目配置 ```application.yml``` ,取消服务获取和服务注册，并修改服务端口 
```yml
eureka:
  instance:
    # 指定服务治理中心服务器ip
    hostname: 192.168.0.88
  client:
    # 取消注册，服务自己是治理中心，开发环境取消注册
    register-with-eureka: false
    # 取消服务获取
    fetch-registry: false
#    service-url:
#      defaultZone: http://${eureka.instance.hostname}:${server.port}/eureka
  server:
    enable-self-preservation:  false

server:
  # 指定端口
  port: 20000
  servlet:
    context-path: /register


spring:
  application:
    # 配置微服务名称
    name: registter-server

```
nginx中修改配置，对注册中心进行转发
```
        location /register {
                proxy_pass  http://127.0.0.1:20000;  #pass到应用服务器
                proxy_redirect     off;
                proxy_set_header   Host             $host;
                proxy_set_header   X-Real-IP        $remote_addr; # 转发ip
                proxy_set_header   X-Forwarded-For  $proxy_add_x_forwarded_for;

        }
```

可以访问以下 [链接](https://erzhiqian.top/register/) 预览效果
```
https://erzhiqian.top/register/
```