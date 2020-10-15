package top.erzhiqian.weixin.lang;

import org.junit.Test;

import static org.junit.Assert.*;

public class LetterOrDigitsStringTest {
    @Test
    public void value() {
        String value = "234gdf";
        LetterOrDigitsString string = new LetterOrDigitsString(value);
        assertEquals(value, string.getValue());
    }

    @Test(expected = IllegalArgumentException.class)
    public void notLetterOrDigits() {
        String value = "#$3234534fkgjfd";
        LetterOrDigitsString string = new LetterOrDigitsString(value);
        assertNull(string);
    }

    @Test(expected = IllegalArgumentException.class)
    public void chineseLetter() {
        String value = "测试字符34234";
        LetterOrDigitsString string = new LetterOrDigitsString(value);
        assertNull(string);
    }


    @Test
    public void stringBetween() {
        int min = 3;
        int max = 32;
        LetterOrDigitsString string = LetterOrDigitsString.stringBetween(min, max);
        assertNotNull(string);
    }

}