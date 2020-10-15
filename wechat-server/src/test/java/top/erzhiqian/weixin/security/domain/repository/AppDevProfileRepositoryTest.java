package top.erzhiqian.weixin.security.domain.repository;

import lombok.extern.log4j.Log4j2;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import top.erzhiqian.weixin.lang.LetterOrDigitsString;
import top.erzhiqian.weixin.security.client.cmd.OpenAppDevProfileCmd;
import top.erzhiqian.weixin.security.client.vo.AppDevProfileVO;
import top.erzhiqian.weixin.security.domain.entity.AppDevProfile;
import top.erzhiqian.weixin.security.domain.entity.WeixinAppId;
import top.erzhiqian.weixin.security.domain.valueobject.MessageDataType;
import top.erzhiqian.weixin.security.domain.valueobject.MessageEncodingKey;

import javax.transaction.Transactional;

import static org.junit.Assert.assertEquals;


@RunWith(SpringRunner.class)
@SpringBootTest
@Log4j2
public class AppDevProfileRepositoryTest {

    private AppDevProfile profile;

    private OpenAppDevProfileCmd cmd;

    private WeixinAppId appId;
    @Autowired
    private AppDevProfileRepository repository;

    @Before
    public void init() {
        appId = WeixinAppId.app("g21245s");
        profile = AppDevProfile.emptyProfile(appId);
        initCmd();
        profile.openDevProfile(cmd);

    }

    private void initCmd() {
        cmd = new OpenAppDevProfileCmd();
        cmd.setServerUrl("https://erzhiqian.top/wechat/server/verify/wx3776b9225ad085af");
        cmd.setServerToken("4324325646DDF3432");
        cmd.setAesKey(LetterOrDigitsString.ofLength(43).getValue());
        cmd.setEncryptionMethod(MessageEncodingKey.EncodingType.ENCRYPT.getCode());
        cmd.setDataType(MessageDataType.DataType.JSON.name());
    }

    @Test
    @Transactional
    public void findAppDevProfile() {
        AppDevProfileVO originalVO = AppDevProfileVO.from(profile);
        repository.saveProfile(profile);
        AppDevProfile savedProfile = repository.findAppDevProfile(appId);
        AppDevProfileVO savedVO = AppDevProfileVO.from(savedProfile);
        assertEquals(originalVO, savedVO);
    }

    @Test
    @Transactional
    public void saveProfile() {
        AppDevProfileVO originalVO = AppDevProfileVO.from(profile);
        repository.saveProfile(profile);
        AppDevProfile savedProfile = repository.findAppDevProfile(appId);
        AppDevProfileVO savedVO = AppDevProfileVO.from(savedProfile);
        assertEquals(originalVO, savedVO);

    }
}