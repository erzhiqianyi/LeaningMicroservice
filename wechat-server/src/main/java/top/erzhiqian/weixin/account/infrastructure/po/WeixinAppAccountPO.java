package top.erzhiqian.weixin.account.infrastructure.po;


import lombok.Data;

import javax.persistence.*;

@Data
@Table(name = WeixinAppAccountPO.TABLE_NAME)
@Entity
public class WeixinAppAccountPO {

    public static final String TABLE_NAME = "weixin_app_account";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "varchar(50) not null comment '公众号或小程序AppID' ")
    private String appId;

    @Column(columnDefinition = "varchar(50) not null comment '公众号或小程序原始ID' ")
    private String originalId;

    @Column(columnDefinition = "varchar(100) default null comment '公众号或小程序微信号' ")
    private String weixinId;

    @Column(columnDefinition = "varchar(50) default null comment '公众号或小程序微信名字' ")
    private String appName;

    @Column(columnDefinition = "varchar(50) not null comment '账号类型，小程序，订阅号，服务号' ")
    private String appType;

    @Column(columnDefinition = "varchar(50) not null comment '账号认证状态，已认证，未认证' ")
    private String certifiedState;

    @Column(columnDefinition = "varchar(50) not null comment '账号主体类型' ")
    private String hostType;
}
