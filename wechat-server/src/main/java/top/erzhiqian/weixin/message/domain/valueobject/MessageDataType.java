package top.erzhiqian.weixin.message.domain.valueobject;

public class MessageDataType {

    private final DataType type;

    public MessageDataType(String dataType) {
        this.type = DataType.valueOf(dataType);
    }

    public enum DataType {
        JSON,
        XML;
    }

    public String type() {
        return type.name();
    }
}
