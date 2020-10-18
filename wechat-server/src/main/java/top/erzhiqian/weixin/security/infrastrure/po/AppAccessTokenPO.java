package top.erzhiqian.weixin.security.infrastrure.po;


import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Data
@Table(name = AppAccessTokenPO.TABLE_NAME)
@Entity
@DynamicUpdate
public class AppAccessTokenPO {
    public static final String TABLE_NAME = "app_access_token";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "varchar(50) not null comment '公众号或小程序AppID' ")
    private String appId;

    @Column(columnDefinition = "varchar(500) not null comment 'access token' ")
    private String accessToken;


    @Column(columnDefinition = " bigint(20) not null comment '创建时间' ")
    private Long createAt;

    @Column(columnDefinition = " int comment '过期事件，单位秒' ")
    private Integer timeToLive;

}

