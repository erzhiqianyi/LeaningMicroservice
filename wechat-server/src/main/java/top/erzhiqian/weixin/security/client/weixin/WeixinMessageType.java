package top.erzhiqian.weixin.security.client.weixin;

public enum WeixinMessageType {
    TEXT("text","文本消息"),
    IMAGE("image","图片消息"),
    VOICE("voice","语音消息"),
    VIDEO("video","视频消息"),
    SHORT_VIDEO("shortvideo","小视频消息"),
    LOCATION("location","地理位置消息"),
    LINK("link","链接消息"),
    EVENT("event","事件消息")
    ;
    private String code;
    private String remark;


    WeixinMessageType(String code,String remark) {
        this.code = code;
        this.remark = remark;
    }
}
