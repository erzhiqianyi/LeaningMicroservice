package top.erzhiqian.weixin.message;

import com.alibaba.fastjson.JSON;
import lombok.extern.log4j.Log4j2;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import top.erzhiqian.weixin.message.app.ServerVerifyApp;
import top.erzhiqian.weixin.message.app.WeiXinMessageProcessApp;
import top.erzhiqian.weixin.message.client.cmd.WeixinVerifyMessageCmd;
import top.erzhiqian.weixin.message.client.weixin.WeixinMessageCmd;
import top.erzhiqian.weixin.message.client.weixin.WeixinMessageResponse;
import top.erzhiqian.weixin.message.domain.valueobject.WeixinAppId;

import java.io.IOException;

/**
 * 微信消息推送配置和服务器配置,用于验证服务器,成为开发者,接收微信消息回调
 * 具体参考微信文档
 * <a href="https://developers.weixin.qq.com/miniprogram/dev/framework/server-ability/message-push.html">消息推送</a>
 * <a href="https://developers.weixin.qq.com/doc/offiaccount/Getting_Started/Overview.html">微信公众平台开发概述</a>
 * 2020/8/24 10:30
 *
 * @author 二之前一
 */
@RestController
@Log4j2
public class WeixinMessageController {


    private ServerVerifyApp verifyApp;

    private WeiXinMessageProcessApp messageProcessApp;

    public WeixinMessageController(ServerVerifyApp verifyApp, WeiXinMessageProcessApp messageProcessApp) {
        this.verifyApp = verifyApp;
        this.messageProcessApp = messageProcessApp;
    }

    /**
     * 验证来自微信服务器消息
     * 2020/10/14 8:36
     * 检验 signature 对请求进行校验（下面有校验方式）。若确认此次 GET 请求来自微信服务器，原样返回 echostr 参数内容，
     * 则接入生效，成为开发者成功，否则接入失败。
     * <p>
     * 加密校验流程如下：
     * <p>
     * 将token、timestamp、nonce三个参数进行字典序排序
     * 将三个参数字符串拼接成一个字符串进行sha1加密
     * 开发者获得加密后的字符串可与signature对比，标识该请求来源于微信
     * 验证URL有效性成功后即接入生效，成为开发者。
     * </p>
     *
     * @param appId 小程序appId 或公众号 appId
     * @author 二之前一
     */

    @GetMapping("app/weixin/{appId}")
    public String verifyWeixinMessage(@PathVariable("appId") String appId, @Validated WeixinVerifyMessageCmd cmd) {
        log.info("请求参数 " + cmd);
        WeixinAppId app = WeixinAppId.app(appId);
        boolean validMessage = verifyApp.checkMessage(app, cmd);
        log.info("校验结果" + validMessage);
        if (validMessage) {
            return cmd.getEchostr();
        } else {
            return cmd.getSignature();
        }
    }


    /**
     * 接收微信消息
     *
     * @param appId   小程序appId 或公众号 appId
     * @param message 微信消息体，xml或json格式
     * @author 二之前一
     */


    @PostMapping("app/weixin/{appId}")
    public String processWeixinMessage(@PathVariable("appId") String appId, @RequestBody WeixinMessageCmd message) throws IOException {
        log.info(appId + "|微信消息参数|" + JSON.toJSONString(message));
        //todo 使用拦截器校验app状态和数据解密
        WeixinAppId app = WeixinAppId.app(appId);
        messageProcessApp.processMessage(app, message);
        return WeixinMessageResponse.success();
    }
}
