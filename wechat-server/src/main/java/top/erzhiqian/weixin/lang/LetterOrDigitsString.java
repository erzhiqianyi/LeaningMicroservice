package top.erzhiqian.weixin.lang;

import org.springframework.util.StringUtils;

public class LetterOrDigitsString {
    private final String value;

    public LetterOrDigitsString(String str) {
        if (StringUtils.isEmpty(str)) {
            throw new IllegalArgumentException("illegal string. string must be letter or digits");
        }
        boolean notOnlyLetterOrDigits = LetterOrDigitsStringHelper.notOnlyLetterOrDigits(str);
        if (notOnlyLetterOrDigits) {
            throw new IllegalArgumentException("illegal string. string must be letter or digits");
        }
        this.value = str;
    }

    public String getValue() {
        return value;
    }

    public static LetterOrDigitsString stringBetween(int min, int max) {
        String value = LetterOrDigitsStringHelper.generateStringBetween(min, max);
        return new LetterOrDigitsString(value);
    }

    public static LetterOrDigitsString ofLength(int length){
        String value = LetterOrDigitsStringHelper.generateStringBetween(length, length);
        return new LetterOrDigitsString(value);
    }
}
