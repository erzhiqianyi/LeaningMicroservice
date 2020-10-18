package top.erzhiqian.weixin.lang;


import org.springframework.util.StringUtils;

import javax.validation.constraints.Email;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailAddress {
    private static final String EMAIL_ADDRESS_REGEX =
            "^([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)*@([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)+[\\.][A-Za-z]{2,3}([\\.][A-Za-z]{2})?$";
    private static final Pattern regex = Pattern.compile(EMAIL_ADDRESS_REGEX);

    @Email
    private final String address;

    public EmailAddress(String address) {
        if (StringUtils.isEmpty(address)) {
            throw new IllegalArgumentException("illegal email address.");
        }
        boolean validAddress = checkAddress(address);
        if (!validAddress){
            throw new IllegalArgumentException("illegal email address.");
        }
        this.address = address;
    }

    private boolean checkAddress(String address) {
        Matcher matcher = regex.matcher(address);
        return matcher.matches();
    }
}
