package top.erzhiqian.weixin.security.service.impl;

import org.springframework.stereotype.Service;
import top.erzhiqian.weixin.message.domain.valueobject.WeixinAppId;
import top.erzhiqian.weixin.security.domain.entity.AccessToken;
import top.erzhiqian.weixin.security.domain.entity.AppSecret;
import top.erzhiqian.weixin.security.domain.repository.IAccessTokenStrategy;
import top.erzhiqian.weixin.security.domain.valueobject.AppSecretString;
import top.erzhiqian.weixin.security.dto.AccessTokenDTO;

import java.util.Optional;

@Service
public class WeixinServerAccessTokenStrategy implements IAccessTokenStrategy {

    @Override
    public Optional<AccessTokenDTO> getAccessToken(WeixinAppId app) {

        return Optional.empty();
    }
}
