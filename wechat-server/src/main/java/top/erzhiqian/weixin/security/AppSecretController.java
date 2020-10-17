package top.erzhiqian.weixin.security;


import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import top.erzhiqian.weixin.message.domain.valueobject.WeixinAppId;
import top.erzhiqian.weixin.security.app.AppSecretApp;
import top.erzhiqian.weixin.security.client.cmd.ChangeAppSecretCmd;

@RestController
@Log4j2
public class AppSecretController {


    private AppSecretApp appSecretApp;

    public AppSecretController(AppSecretApp appSecretApp) {
        this.appSecretApp = appSecretApp;
    }

    /**
     * 添加微信服务器配置,需要和微信公众平台或小程序平台添加开启的服务器配置一致
     * todo 用拦截器判断应用是否可以修改
     *
     * @param cmd   新密钥,由微信公众平台或小程序平台生成
     * @param appId 小程序或公众号appId
     * @return true 修改成功
     */

    @PostMapping("/appsecret/change/{appId}")
    public Boolean changeAppSecret(@PathVariable("appId") String appId,
                                   @RequestBody ChangeAppSecretCmd cmd) {
        WeixinAppId app = WeixinAppId.app(appId);
        appSecretApp.changeAppSecret(app,cmd);
        return true;
    }
}
