package top.erzhiqian.weixin.security.domain.valueobject;

import org.springframework.util.StringUtils;

import java.util.Objects;

public class AppSecretString {

    private final String secret;

    public AppSecretString(String secret) {
        if (StringUtils.isEmpty(secret)) {
            throw new IllegalArgumentException("illegal secret.");
        }
        this.secret = secret;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AppSecretString that = (AppSecretString) o;
        return secret.equals(that.secret);
    }

    @Override
    public int hashCode() {
        return Objects.hash(secret);
    }

    public boolean sameSecret(AppSecretString other) {
        if (null == other){
            return false;
        }
        return equals(other);
    }
    public String secret(){
        return secret;
    }

}
