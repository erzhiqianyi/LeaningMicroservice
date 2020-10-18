package top.erzhiqian.weixin.security.infrastrure.repository.jdbc;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import top.erzhiqian.weixin.security.infrastrure.po.AppAccessTokenPO;

import java.util.Optional;
import java.util.UUID;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AppAccessTokenJdbcRepositoryTest {

    @Autowired
    private AppAccessTokenJdbcRepository repository;

    @Test
    public void findTop1ByAppIdOrderByCreateAtDesc() {
        String appId = "wd4954953";
        AppAccessTokenPO po = new AppAccessTokenPO();
        po.setCreateAt(System.currentTimeMillis());
        po.setTimeToLive(1000);
        po.setAppId(appId);
        po.setAccessToken(UUID.randomUUID().toString());
        repository.save(po);
        Optional<AppAccessTokenPO> optional = repository.findTop1ByAppIdOrderByCreateAtDesc(appId);
        assertTrue(optional.isPresent());
        AppAccessTokenPO saved = optional.get();
        assertEquals(po.getAccessToken(),saved.getAccessToken());
        assertEquals(po.getAccessToken(),saved.getAccessToken());
        assertEquals(po.getTimeToLive(),saved.getTimeToLive());
        assertEquals(po.getCreateAt(),saved.getCreateAt());
        assertEquals(po.getAppId(),saved.getAppId());

    }
}