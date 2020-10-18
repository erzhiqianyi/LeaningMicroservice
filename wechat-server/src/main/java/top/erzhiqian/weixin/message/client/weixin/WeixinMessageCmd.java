package top.erzhiqian.weixin.message.client.weixin;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;

@Data
public class WeixinMessageCmd {

    /**
     * 小程序的原始ID 或公众号原始ID
     */
    @JacksonXmlProperty(localName = "ToUserName")
    private String toUserName;

    /**
     * 发送者的openid
     */
    @JacksonXmlProperty(localName = "FromUserName")
    private String fromUserName;

    /**
     * 消息创建时间(整型）
     */
    @JacksonXmlProperty(localName = "createTime")
    private Long createTime;

    /**
     * 消息类型
     */
    @JacksonXmlProperty(localName = "MsgType")
    private String msgType;

    /**
     * 消息id，64位整型
     */
    @JacksonXmlProperty(localName = "MsgId")
    private Long msgId;

    /**
     * 文本消息内容
     */
    @JacksonXmlProperty(localName = "Content")
    private String content;

    /**
     * 图片链接,微信生成
     */
    @JacksonXmlProperty(localName = "PicUrl")
    private String picUrl;

    /**
     * 图片消息 图片消息媒体id，可以调用[获取临时素材]((getTempMedia)接口拉取数据。
     * 语音消息 语音消息媒体id，可以调用获取临时素材接口拉取该媒体
     * 视频消息媒体id，可以调用获取临时素材接口拉取数据。
     */
    @JacksonXmlProperty(localName = "MediaId")
    private String mediaId;

    /**
     * 语音消息格式
     */
    @JacksonXmlProperty(localName = "Format")
    private String format;

    /**
     * 语音消息识别结果
     */
    @JacksonXmlProperty(localName = "recognition")
    private String Recognition;


    /**
     * 视频消息缩略图的媒体id，可以调用多媒体文件下载接口拉取数据。
     */
    @JacksonXmlProperty(localName = "ThumbMediaId")
    private String thumbMediaId;

    /**
     * 地理位置纬度
     */
    @JacksonXmlProperty(localName = "Location_X")
    private String locationX;

    /**
     * 地理位置经度
     */
    @JacksonXmlProperty(localName = "Location_Y")
    private String locationY;

    /**
     * 地图缩放大小
     */
    @JacksonXmlProperty(localName = "Scale")
    private String scale;

    /**
     * 地理位置信息
     */
    @JacksonXmlProperty(localName = "Label")
    private String label;


    /**
     * 地理位置纬度
     */
    @JacksonXmlProperty(localName = "Latitude")
    private String latitude;

    /**
     * 地理位置经度
     */
    @JacksonXmlProperty(localName = "Longitude")
    private String longitude;

    /**
     * 地理位置精度
     */
    @JacksonXmlProperty(localName = "Precision")
    private String Precision;
    /**
     * 消息标题
     */
    @JacksonXmlProperty(localName = "Title")
    private String title;

    /**
     * 消息描述
     */
    @JacksonXmlProperty(localName = "Description")
    private String description;

    /**
     * 消息链接
     */
    @JacksonXmlProperty(localName = "Url")
    private String url;

    /**
     * 事件类型，subscribe(订阅)、unsubscribe(取消订阅)
     */
    @JacksonXmlProperty(localName = "Event")
    private String event;


    /**
     * 事件KEY值，qrscene_为前缀，后面为二维码的参数值,
     * 事件KEY值，与自定义菜单接口中KEY值对应
     * 事件KEY值，设置的跳转URL
     */
    @JacksonXmlProperty(localName = "EventKey")
    private String eventKey;

    /**
     * 二维码的ticket，可用来换取二维码图片
     */
    @JacksonXmlProperty(localName = "ticket")
    private String Ticket;

    /**
     * 加密数据
     */
    @JacksonXmlProperty(localName = "Encrypt")
    private String encrypt;


}

