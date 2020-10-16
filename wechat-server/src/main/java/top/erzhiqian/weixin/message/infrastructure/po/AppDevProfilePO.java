package top.erzhiqian.weixin.message.infrastructure.po;

import lombok.Data;

import javax.persistence.*;
import java.time.Instant;


@Data
@Table(name = AppDevProfilePO.TABLE_NAME)
@Entity
public class AppDevProfilePO {

    public static final String TABLE_NAME = "app_dev_profile";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(columnDefinition = "varchar(50) not null comment '公众号或小程序AppID' ")
    private String appId;

    @Column(columnDefinition = "varchar(500) not null comment '服务器地址' ")
    private String serverUrl;

    @Column(columnDefinition = "varchar(32) not null comment '令牌必须为英文或数字，长度为3-32字符' ")
    private String serverToken;

    @Column(columnDefinition = "varchar(43) not null comment '消息加密密钥,消息加密密钥由43位字符组成，字符范围为A-Z,a-z,0-9' ")
    private String aesKey;

    @Column(columnDefinition = "varchar(30) not null comment '消息加密类型' ")
    private String encryptionMethod;

    @Column(columnDefinition = "varchar(30) not null comment '消息格式类型' ")
    private String dataType;

    private Instant createAt;

    private Instant lastModified;


}
