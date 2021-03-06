package top.erzhiqian.weixin.message.app.processors;

import lombok.extern.log4j.Log4j2;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import top.erzhiqian.weixin.message.domain.valueobject.message.TextMessage;

@RunWith(SpringRunner.class)
@SpringBootTest
@Log4j2
public class TextMessageProcessorsTest {

    @Autowired
    private TextMessageProcessors processors;

    private TextMessage message;

    @Before
    public void init() {
    }

    @Test
    public void processMessage() {

    }
}