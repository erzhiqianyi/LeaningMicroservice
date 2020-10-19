package top.erzhiqian.weixin.account.infrastructure.po;

import lombok.Data;

import javax.persistence.*;

@Data
@Table(name = WeixinAppHostPO.TABLE_NAME)
@Entity
public class WeixinAppHostPO {

    public static final String  TABLE_NAME = "weixn_app_host";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "varchar(50) not null comment '公众号或小程序AppID' ")
    private String appId;

    @Column(columnDefinition = "varchar(50) not null comment '小程序或公众号主体id' ")
    private String hostId ;

    private Long createAt;




}
