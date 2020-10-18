package top.erzhiqian.weixin.security;


import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;
import top.erzhiqian.weixin.lang.WeixinAppId;
import top.erzhiqian.weixin.security.app.BusinessStrategyApp;
import top.erzhiqian.weixin.security.client.cmd.ChangeBusinessStrategyCmd;
import top.erzhiqian.weixin.security.client.vo.BusinessStrategyVO;

import javax.validation.Valid;
import java.util.List;

@RestController
@Log4j2
public class BusinessStrategyController {

    private BusinessStrategyApp app;

    public BusinessStrategyController(BusinessStrategyApp app) {
        this.app = app;
    }


    @GetMapping("/business/strategies/{appId}")
    public List<BusinessStrategyVO> listBusinessStrategy(@PathVariable("appId") String appId) {
        List<BusinessStrategyVO> businessStrategies = app.listBusinessStrategy(WeixinAppId.app(appId));
        return businessStrategies;
    }

    @PostMapping("/business/strategy/setting/{appId}")
    public List<BusinessStrategyVO> setBusinessStrategy(@PathVariable("appId") String appId,
                                                        @Valid @RequestBody ChangeBusinessStrategyCmd cmd) {
        app.setBusinessStrategy(WeixinAppId.app(appId), cmd.getStrategies());
        return app.listBusinessStrategy(WeixinAppId.app(appId));
    }
}
