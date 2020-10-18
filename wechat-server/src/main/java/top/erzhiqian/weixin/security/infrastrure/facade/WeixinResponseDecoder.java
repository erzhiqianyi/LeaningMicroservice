package top.erzhiqian.weixin.security.infrastrure.facade;


import com.alibaba.fastjson.JSON;
import feign.FeignException;
import feign.Response;
import feign.Util;
import feign.codec.Decoder;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.lang.reflect.Type;

@Log4j2
public class WeixinResponseDecoder implements Decoder {

    @Override
    public Object decode(Response response, Type type) throws FeignException, IOException {
        if (null == response) {
            throw new IllegalAccessError();
        }
        String bodyStr = Util.toString(response.body().asReader(Util.UTF_8));
        WeixinResponse weixinResponse = JSON.parseObject(bodyStr,WeixinResponse.class);
        if (weixinResponse.success()){
            return JSON.parseObject(bodyStr,type);
        }else {
            throw new IllegalArgumentException("微信服务请求失败。"+weixinResponse);
        }
    }

}
