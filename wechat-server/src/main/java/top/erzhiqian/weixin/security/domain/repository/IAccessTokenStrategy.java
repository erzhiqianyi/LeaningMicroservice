package top.erzhiqian.weixin.security.domain.repository;

import top.erzhiqian.weixin.message.domain.valueobject.WeixinAppId;
import top.erzhiqian.weixin.security.dto.AccessTokenDTO;

import java.util.Optional;

public interface IAccessTokenStrategy {
    Optional<AccessTokenDTO> getAccessToken(WeixinAppId app);
}
