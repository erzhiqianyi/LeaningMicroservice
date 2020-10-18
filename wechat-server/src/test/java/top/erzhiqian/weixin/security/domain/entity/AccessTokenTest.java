package top.erzhiqian.weixin.security.domain.entity;

import org.junit.Before;
import org.junit.Test;
import top.erzhiqian.weixin.lang.WeixinAppId;
import top.erzhiqian.weixin.security.domain.valueobject.AccessTokenString;
import top.erzhiqian.weixin.security.dto.AccessTokenDTO;

import static org.junit.Assert.*;

public class AccessTokenTest {


    private AccessTokenDTO accessTokenDTO;


    private String appId;

    @Before
    public void init() {
        accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setAccessToken(AccessTokenString.generateToken().token());
        accessTokenDTO.setExpiresIn(7200);
        appId = "wx4ff4f9c7af819999";
    }

    @Test
    public void setToken() {
        WeixinAppId app = WeixinAppId.app(appId);
        AccessToken accessToken = AccessToken.appAccessToken(app);
        assertNotNull(accessToken);
        accessToken.setToken(accessTokenDTO);
        assertFalse(accessToken.expired());
    }

}