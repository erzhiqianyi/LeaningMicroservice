package top.erzhiqian.weixin.security.client.vo;


import lombok.Data;

@Data
public class EnumVO {

    private String code;

    private String remark;

    public EnumVO() {
    }

    public EnumVO(String code, String remark) {
        this.code = code;
        this.remark =remark;
    }
}
