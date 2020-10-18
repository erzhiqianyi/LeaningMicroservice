package top.erzhiqian.weixin.security.infrastrure.adapt;

import org.springframework.stereotype.Service;
import top.erzhiqian.weixin.lang.WeixinAppId;
import top.erzhiqian.weixin.security.domain.entity.AccessToken;
import top.erzhiqian.weixin.security.domain.repository.AccessTokenRepository;
import top.erzhiqian.weixin.security.infrastrure.po.AppAccessTokenPO;
import top.erzhiqian.weixin.security.infrastrure.repository.jdbc.AppAccessTokenJdbcRepository;

import java.util.Optional;

@Service
public class AccessTokenRepositoryAdapt implements AccessTokenRepository {


    private AppAccessTokenJdbcRepository jdbcRepository;

    public AccessTokenRepositoryAdapt(AppAccessTokenJdbcRepository jdbcRepository) {
        this.jdbcRepository = jdbcRepository;
    }

    @Override
    public void cacheAccessToken(AccessToken accessToken) {
        AppAccessTokenPO po = convertToPO(accessToken);
        jdbcRepository.save(po);
    }

    private AppAccessTokenPO convertToPO(AccessToken accessToken) {
        AppAccessTokenPO po = new AppAccessTokenPO();
        po.setAppId(accessToken.getApp().appId());
        po.setAccessToken(accessToken.getAccessToken().token());
        po.setCreateAt(accessToken.getExpiredTime().getCreateAt());
        po.setTimeToLive(accessToken.getExpiredTime().getTimeToLive());
        return po;
    }

    @Override
    public AccessToken findAccessToken(WeixinAppId app) {
        if (null == app) {
            throw new IllegalArgumentException("illegal app.");
        }
        AccessToken accessToken = AccessToken.appAccessToken(app);
        Optional<AppAccessTokenPO> optional = jdbcRepository.findTop1ByAppIdOrderByCreateAtDesc(app.appId());
        if (optional.isPresent()) {
            AppAccessTokenPO po = optional.get();
            accessToken.restoreToken(po.getAccessToken(), po.getCreateAt(), po.getTimeToLive());
        }
        return accessToken;
    }


}
