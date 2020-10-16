package top.erzhiqian.weixin.message.domain.valueobject;

import org.apache.commons.codec.digest.DigestUtils;
import top.erzhiqian.weixin.message.client.cmd.WeixinVerifyMessageCmd;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Sha1SignTokenWeixin extends ServerToken implements IWeixinServerToken {

    public Sha1SignTokenWeixin(String token) {
        super(token);
    }

    private String signData(String timestamp, String nonce) {
        String sortedValue = Stream
                .of(getToken(), timestamp, nonce)
                .sorted()
                .collect(Collectors.joining());
        return DigestUtils.sha1Hex(sortedValue);
    }


    @Override
    public boolean checkMessage(WeixinVerifyMessageCmd message) {
        String sign = signData(message.getTimestamp(), message.getNonce());
        return sign.equals(message.getSignature());
    }
}


