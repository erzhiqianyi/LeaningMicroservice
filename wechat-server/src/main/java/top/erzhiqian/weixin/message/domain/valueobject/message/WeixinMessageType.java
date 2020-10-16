package top.erzhiqian.weixin.message.domain.valueobject.message;

import sun.management.snmp.jvmmib.JvmCompilationMBean;
import top.erzhiqian.weixin.message.client.weixin.WeixinMessageCmd;

import java.util.Optional;
import java.util.stream.Stream;

public enum WeixinMessageType {
    TEXT("text", "文本消息") {
        @Override
        public TextMessage createContent(WeixinMessageCmd cmd) {
            return new TextMessage(cmd.getContent());
        }
    },
    IMAGE("image", "图片消息") {
        @Override
        public ImageMessage createContent(WeixinMessageCmd cmd) {
            return new ImageMessage(cmd.getPicUrl(), cmd.getMediaId());
        }
    },
    VOICE("voice", "语音消息") {
        @Override
        public VoiceMessage createContent(WeixinMessageCmd cmd) {
            return new VoiceMessage(cmd.getMediaId(), cmd.getFormat(), cmd.getRecognition());
        }
    },
    VIDEO("video", "视频消息") {
        @Override
        public VideoMessage createContent(WeixinMessageCmd cmd) {
            return new VideoMessage(cmd.getMediaId(), cmd.getThumbMediaId());
        }
    },
    SHORT_VIDEO("shortvideo", "小视频消息") {
        @Override
        public VideoMessage createContent(WeixinMessageCmd cmd) {
            return new VideoMessage(cmd.getMediaId(), cmd.getThumbMediaId());
        }
    },
    LOCATION("location", "地理位置消息") {
        @Override
        public LocationMessage createContent(WeixinMessageCmd cmd) {
            return new LocationMessage(cmd.getLocationX(), cmd.getLocationY(), cmd.getScale(), cmd.getLabel());
        }
    },
    LINK("link", "链接消息") {
        @Override
        public LinkMessage createContent(WeixinMessageCmd cmd) {
            return new LinkMessage(cmd.getTitle(), cmd.getDescription(), cmd.getUrl());
        }
    },
    EVENT("event", "事件消息") {
        @Override
        public EventMessage createContent(WeixinMessageCmd cmd) {
            return new EventMessage(cmd.getEvent(), cmd.getEventKey(), cmd.getTicket());
        }
    };
    private String code;
    private String remark;


    WeixinMessageType(String code, String remark) {
        this.code = code;
        this.remark = remark;
    }

    public static WeixinMessageType getMessageType(String msgType) {
        Optional<WeixinMessageType> optional = Stream.of(values())
                .filter(item -> item.code.equals(msgType))
                .findFirst();
        if (optional.isPresent()) {
            return optional.get();
        } else {
            throw new IllegalArgumentException("illegal message type.");
        }

    }

    public String getCode() {
        return code;
    }

    public String getRemark() {
        return remark;
    }


    public abstract <T> T createContent(WeixinMessageCmd cmd);
}

