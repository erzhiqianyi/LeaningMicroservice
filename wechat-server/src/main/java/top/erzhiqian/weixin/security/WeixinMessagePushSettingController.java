package top.erzhiqian.weixin.security;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.RestController;
import top.erzhiqian.weixin.security.client.vo.WeixinMessagePushSettingVO;

@RestController
@Log4j2
public class WeixinMessagePushSettingController {

    public WeixinMessagePushSettingVO addMessagePushSetting() {
        return new WeixinMessagePushSettingVO();
    }

}
