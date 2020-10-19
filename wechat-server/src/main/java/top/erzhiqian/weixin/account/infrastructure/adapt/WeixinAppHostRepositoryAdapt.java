package top.erzhiqian.weixin.account.infrastructure.adapt;

import org.springframework.stereotype.Service;
import top.erzhiqian.weixin.account.domain.entity.WeixinAppHost;
import top.erzhiqian.weixin.account.domain.repository.WeixinAppHostRepository;
import top.erzhiqian.weixin.lang.WeixinAppId;

import java.util.Optional;

@Service
public class WeixinAppHostRepositoryAdapt implements WeixinAppHostRepository {

    @Override
    public Optional<WeixinAppHost> findWeixinAppHost(WeixinAppId appId) {
        if (null == appId) {
            throw new IllegalArgumentException("illegal app.");
        }

        return null;
    }
}
