package top.erzhiqian.weixin.account.infrastructure.adapt;

import org.springframework.stereotype.Service;
import top.erzhiqian.weixin.account.domain.entity.WeixinApp;
import top.erzhiqian.weixin.account.domain.repository.WeixinAppRepository;
import top.erzhiqian.weixin.lang.WeixinAppId;

@Service
public class WeixinAppRepositoryAdapt implements WeixinAppRepository {
    @Override
    public WeixinApp findWeixinApp(WeixinAppId weixinAppId) {
        return null;
    }
}
