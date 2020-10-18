package top.erzhiqian.weixin.security.infrastrure.po;


import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.Instant;

@Data
@Table(name = AppSecretPO.TABLE_NAME)
@Entity
@DynamicUpdate
public class AppSecretPO {

    public static final String  TABLE_NAME = "app_secret";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "varchar(50) not null comment '公众号或小程序AppID' ")
    private String appId;

    @Column(columnDefinition = "varchar(255) not null comment '公众号或小程序appSecret' ")
    private String appSecret;

    private Long createAt;

    private Long lastModified;




}
