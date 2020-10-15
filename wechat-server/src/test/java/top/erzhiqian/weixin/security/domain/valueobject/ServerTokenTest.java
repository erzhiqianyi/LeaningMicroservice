package top.erzhiqian.weixin.security.domain.valueobject;

import org.junit.Test;

import static org.junit.Assert.*;

public class ServerTokenTest {
    @Test
    public void validToken() {
        String token = "43534fkjfgdJDFJG";
        ServerToken verifyToken = new ServerToken(token);
        assertNotNull(verifyToken);
    }

    @Test(expected = IllegalArgumentException.class)
    public void moreThanMaxLength() {
        String token = "123456789123456789123456789123456789123456789123456789";
        ServerToken verifyToken = new ServerToken(token);
        assertNull(verifyToken);
    }

    @Test(expected = IllegalArgumentException.class)
    public void lessThanMinLength() {
        String token = "1";
        ServerToken verifyToken = new ServerToken(token);
        assertNull(verifyToken);
    }

    @Test(expected = IllegalArgumentException.class)
    public void notLetterOrDigits() {
        String token = "#$3234534fkgjfd";
        ServerToken verifyToken = new ServerToken(token);
        assertNull(verifyToken);
    }


}