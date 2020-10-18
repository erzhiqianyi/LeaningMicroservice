package top.erzhiqian.weixin.lang;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class EmailAddressTest {

    @Test
    public void validEmail(){
        String email = "erzhiqianyi@gmail.com";
        EmailAddress address = new EmailAddress(email)     ;
        assertNotNull(address);
    }

    @Test(expected = IllegalArgumentException.class)
    public void invalidEmail(){
        String email = "erzhiqianyigmail.com";
        EmailAddress address = new EmailAddress(email)     ;
        assertNull(address);
    }

}