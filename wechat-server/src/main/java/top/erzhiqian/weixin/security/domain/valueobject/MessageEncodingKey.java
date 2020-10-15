package top.erzhiqian.weixin.security.domain.valueobject;

import org.springframework.util.StringUtils;
import top.erzhiqian.weixin.lang.LetterOrDigitsString;

public class MessageEncodingKey {

    /**
     * 消息加密密钥长度
     */
    private static final int LENGTH = 43;

    private final EncodingType encodingType;

    private final LetterOrDigitsString key;

    public MessageEncodingKey(String encodingType, String key) {
        if (StringUtils.isEmpty(encodingType)) {
            throw new IllegalArgumentException("encoding type can't be null");
        }
        if (StringUtils.isEmpty(key)) {
            throw new IllegalArgumentException("encoding key can't be null");
        }
        if (key.length() != LENGTH) {
            throw new IllegalArgumentException("illegal key ,encoding key length must be 43");
        }
        this.encodingType = EncodingType.valueOf(encodingType);
        this.key = new LetterOrDigitsString(key);
    }

    public enum EncodingType {
        ORIGINAL("ORIGINAL", "明文模式"),
        ORIGINAL_ENCRYPT("ORIGINAL_ENCRYPT", "兼容模式，明文和密文共存"),
        ENCRYPT("ENCRYPT", "加密模式, 消息为纯密文，需要加密解密");
        private String code;
        private String remark;

        EncodingType(String code, String remark) {
            this.code = code;
            this.remark = remark;
        }

        public String getCode() {
            return code;
        }

        public String getRemark() {
            return remark;
        }


    }

    public String getEncodingType() {
        return encodingType.getCode();
    }


    public String getKey() {
        return key.getValue();
    }

}
