# LeaningMicroservice
学习微服务记录，使用SpringBoot及相关Spring组件搭建微服务。并搭建一个兼容多小程序的小程序服务应用。

## 目录
- [搭建公网访问环境](#搭建公网访问环境)
- [搭建服务治理中心](#搭建服务治理中心)
- [搭建小程序业务微服务](#搭建小程序业务微服务)
- [搭建认证授权微服务](#搭建认证授权微服务)
- [使用Openfeign调用微服务](#使用Openfeign调用微服务)
- [搭建网关](#搭建网关)
- [使用SpringBootAdmin监控微服务](#使用SpringBootAdmin监控微服务)
- [实用命令](#实用命令)
  - [启动Spring Boot应用时指定配置文件](启动Spring Boot应用时指定配置文件])
## 搭建公网访问环境
可以使用花生壳(或者其他工具)进行内网穿透，通过域名访问本地服务。

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
nginx中修改配置，对注册中心及相应文件进行转发
```conf
        location /eureka/ {
                proxy_pass  http://127.0.0.1:20000;  #pass到应用服务器
                proxy_redirect     off;
                proxy_set_header   Host             $host;
                proxy_set_header   X-Real-IP        $remote_addr; # 转发ip
                proxy_set_header   X-Forwarded-For  $proxy_add_x_forwarded_for;

        }


          location /eureka/css/ {
                proxy_pass  http://127.0.0.1:20000/eureka/css/;  #首先pass到应用服务器
                proxy_redirect     off;
                proxy_set_header   Host             $host;
                proxy_set_header   X-Real-IP        $remote_addr;
                proxy_set_header   X-Forwarded-For  $proxy_add_x_forwarded_for;

        }

        location /eureka/images/ {
                proxy_pass  http://127.0.0.1:20000/eureka/images/;  #首先pass到应用服务器
                proxy_redirect     off;
                proxy_set_header   Host             $host;
                proxy_set_header   X-Real-IP        $remote_addr;
                proxy_set_header   X-Forwarded-For  $proxy_add_x_forwarded_for;

        }




        location /eureka/js/ {
                proxy_pass  http://127.0.0.1:20000/eureka/js/;  #首先pass到应用服务器
                proxy_redirect     off;
                proxy_set_header   Host             $host;
                proxy_set_header   X-Real-IP        $remote_addr;
                proxy_set_header   X-Forwarded-For  $proxy_add_x_forwarded_for;

        }

        location /eureka/fonts/ {
                proxy_pass  http://127.0.0.1:20000/eureka/fonts/;  #首先pass到应用服务器
                proxy_redirect     off;
                proxy_set_header   Host             $host;
                proxy_set_header   X-Real-IP        $remote_addr;
                proxy_set_header   X-Forwarded-For  $proxy_add_x_forwarded_for;

        }


```

可以访问以下 [链接](https://erzhiqian.top/eureka/) 预览效果
```
https://erzhiqian.top/eureka/
```

- 启动多个服务服务治理中心 

## 搭建小程序业务微服务
小程序业务微服务主要用来处理微信相关业务，比如获取accessToken ,生成小程序码,推送消息等。 

- 创建项目

使用 [spring initializr](https://start.spring.io/) 创建一个　SpringBoot 项目，添加 ```Eureka Client``` 和 ```Start Web``` ,如果有用 ```lombok``` 的习惯，也可以添加 ```lombok``` , 推荐使用 ```lombok``` 。

- 修改配置，注册服务 
修改配置文件，添加 ```eureka``` 客户端配置，将本服务注册到服务治理中心。

```yml
spring:
  application:
    name: wechat-server

server:
  # 指定端口在20001
  port: 20001

# eureka配置
eureka:
  instance:
    hostname: 192.168.0.88
  # 客户端信息
  client:
    #  注册到eureka
    register-with-eureka: true
    fetch-registry: true
    service-url:
      # 服务中心地址 ，注意是 defaultZone ， 不是default-zone
      defaultZone: http://192.168.0.88:20000/eureka
```
- 启动应用
执行 ```main()``` 方法，启动应用。访问前面搭好的 ```eureka``` 服务端，可以查看已经注册的微服务。

- 注册多个实例

一个微服务可以启动多个实例并注册到服务治理中心要注册多个实例，只要同一个微服务在不同端口启动即可。

- 模拟小程序用户登录Rest端点
在小程序中，可以使用小程序提供的用户信息进行登录。可参考小程序文档。目前只是模拟下，提供一个服务接口，供其他服务调用。
登录凭证校验。通过 ```wx.login``` 接口获得临时登录凭证 ```code``` 后传到开发者服务器调用此接口完成登录流程。更多使用方法详见 [小程序登录](#https://developers.weixin.qq.com/miniprogram/dev/framework/open-ability/login.html)。

```java
@RestController
public class LoginController {

    @GetMapping("login/code")
    public String loginByByCode(String code) {
        return code;
    }

}
```

## 搭建认证授权微服务
将认证和鉴权独立成微服务，认证负责用户登录，鉴权负责权限校验。

- 创建项目和注册服务

和[搭建小程序业务微服务](#搭建小程序业务微服务)一样。

- 使用 ```Ribbon``` 调用微服务 
使用小程序授权码登录需要调用前面写的小程序用户登录接口，使用 ```Ribbon``` 调用它。
使用 ```Ribbon``` 需要配置 ```RestTemplate``` 。在启动类中注册```RestTemplate``` ```Bean```。
```java
@SpringBootApplication
public class AuthServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthServerApplication.class, args);
    }


    @LoadBalanced
    @Bean
    public RestTemplate initRestTemplate() {
        return new RestTemplate();
    }
}

```

添加小程序授权码登录接口，调用小程序服务接口。
```java
@RestController
public class WechatLoginController {

    private RestTemplate restTemplate;

    public WechatLoginController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/login/wechat/code")
    public String loginByWechatSessionCode(String code) {
        String url = "http://wechat-server/login/code?code={code}";
        Map<String, String> param = new HashMap<>();
        param.put("code", code);
        String result = restTemplate.getForObject(url, String.class, param);
        return result;
    }
}
```


## 使用Openfeign调用微服务

## 搭建网关

## 使用SpringBootAdmin监控微服务
- 创建 ```Spring Boot Admin``` 服务端

- 客户端注册到服务端

## 实用命令

### 启动Spring Boot应用时指定配置文件
可以在 ```resource``` 目录下创建多个配置文件，并指定一个默认激活的配置文件:
```yml
spring:
  profiles:
    # 指定激活的配置文件
    active:
```

使用默认参数启动 ```Spring Boot```应用，就会使用默认配置启动应用。如果需要在启动时指定要使用的配置，而不是打包时修改配置, 就可以增加启动参数，通过启动参数来指定相应的配置文件,命令如下
```
java -jar *.jar --spring.profiles.active=XXX
```