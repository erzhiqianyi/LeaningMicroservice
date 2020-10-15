package top.erzhiqian.weixin.lang;

import org.junit.Test;

import static org.junit.Assert.*;

public class LetterOrDigitsStringHelperTest {

    @Test
    public void chineseLetter() {
        String value = "测试汉字";
        boolean result = LetterOrDigitsStringHelper.notOnlyLetterOrDigits(value);
        assertTrue(result);
    }

    @Test
    public void sharpLetter() {
        String value = "#3#";
        boolean result = LetterOrDigitsStringHelper.notOnlyLetterOrDigits(value);
        assertTrue(result);
    }

    @Test
    public void onlyLeterOrDigits() {
        String value = "3243DFdf3";
        boolean result = LetterOrDigitsStringHelper.notOnlyLetterOrDigits(value);
        assertFalse(result);
    }

    @Test
    public void isLetterOrDigits() {
        String a = "a";
        boolean result = LetterOrDigitsStringHelper.isLetterOrDigits(a.charAt(0));
        assertTrue(result);
    }

    @Test
    public void notLetterOrDigits() {
        String a = "中";
        boolean result = LetterOrDigitsStringHelper.isLetterOrDigits(a.charAt(0));
        assertFalse(result);
    }


    @Test
    public void generateStringBetween() {
        int min = 3;
        int max = 32;
        String value = LetterOrDigitsStringHelper.generateStringBetween(min, max);
        assertTrue(value.length() >= min);
        assertTrue(value.length() <= max);
        boolean onlyLetterAndDigits = LetterOrDigitsStringHelper.notOnlyLetterOrDigits(value);
        assertFalse(onlyLetterAndDigits);

    }

    @Test
    public void ofLength(){
        int length = 43;
        String value = LetterOrDigitsStringHelper.generateStringBetween(length,length);
        assertTrue(value.length() == length);
        boolean onlyLetterAndDigits = LetterOrDigitsStringHelper.notOnlyLetterOrDigits(value);
        assertFalse(onlyLetterAndDigits);

    }
}