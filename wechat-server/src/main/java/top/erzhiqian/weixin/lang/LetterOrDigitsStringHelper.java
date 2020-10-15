package top.erzhiqian.weixin.lang;

import java.util.OptionalInt;
import java.util.Random;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.joining;

public class LetterOrDigitsStringHelper {
    private static String[] LETTER_OR_DIGITS_ARRAY = new String[]
            {
                    "0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
                    "a", "b", "c", "d", "e", "f", "g", "h", "i", "j",
                    "k", "l", "m", "n", "o", "p", "q", "r", "s", "t",
                    "u", "v", "w", "x", "y", "z",
                    "A", "B", "C", "D", "E", "F", "G", "H", "H", "J",
                    "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T",
                    "U", "V", "W", "X", "Y", "Z",
            };


    /**
     * 判断字符串中是否含有非大小写字母或数字
     *
     * @param token
     * @return true 如果包含非大小写字母或数字得字符 false 只包含数字和大小写字符
     */
    public static boolean notOnlyLetterOrDigits(String token) {
        OptionalInt optional =
                token.chars()
                        .filter(c -> !isLetterOrDigits(c))
                        .findFirst();
        return optional.isPresent();
    }


    /**
     * 判断数字是否是大小写或数字
     * 2020/10/15 8:55
     * 二之前一
     */
    public static boolean isLetterOrDigits(int c) {
        return Character.isLowerCase(c) || Character.isUpperCase(c) || Character.isDigit(c);
    }


    public static String generateStringBetween(int minLength, int maxLength) {
        if (minLength > maxLength) {
            throw new IllegalArgumentException(" min length must less than max length");
        }
        int length = LETTER_OR_DIGITS_ARRAY.length;
        Random random = new Random();
        String value = random.ints(maxLength, 0, length)
                .mapToObj(index -> LETTER_OR_DIGITS_ARRAY[index])
                .collect(joining());
        if (minLength != maxLength) {
            int subLength = random.nextInt(minLength);
            value = value.substring(subLength, maxLength);
        }
        return value;
    }
}
