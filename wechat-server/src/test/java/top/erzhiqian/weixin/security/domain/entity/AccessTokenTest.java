package top.erzhiqian.weixin.security.domain.entity;

import org.junit.Before;
import org.junit.Test;
import top.erzhiqian.weixin.message.domain.valueobject.WeixinAppId;
import top.erzhiqian.weixin.security.domain.repository.IAccessTokenStrategy;
import top.erzhiqian.weixin.security.domain.valueobject.AccessTokenString;
import top.erzhiqian.weixin.security.dto.AccessTokenDTO;

import java.util.Optional;

import static org.junit.Assert.*;

public class AccessTokenTest {


    private AccessTokenDTO accessTokenDTO;


    @Before
    public void init(){
        accessTokenDTO = new AccessTokenDTO()     ;
        accessTokenDTO.setAccessToken(AccessTokenString.generateToken().token());
        accessTokenDTO.setTimeToLive(7200l);
    }

    @Test
    public void refreshAccessToken() {
        WeixinAppId app = new WeixinAppId("g2342t");
        AccessToken accessToken = AccessToken.restoreAccessToken(app,null);
        assertNotNull(accessToken);
        IAccessTokenStrategy accessTokenStrategy = app1 -> Optional.of(accessTokenDTO);
        accessToken.refreshAccessToken(accessTokenStrategy);
        assertFalse(accessToken.expired());
    }
}