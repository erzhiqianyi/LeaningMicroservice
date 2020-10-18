package top.erzhiqian.weixin.security.infrastrure.po;


import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.Instant;

@Data
@Table(name = AppBusinessStrategyPO.TABLE_NAME)
@Entity
@DynamicUpdate
public class AppBusinessStrategyPO {

    public static final String TABLE_NAME = "app_business_strategy";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "varchar(50) not null comment '公众号或小程序AppID' ")
    private String appId;

    @Column(columnDefinition = "varchar(50) not null comment '业务类型' ")
    private String businessType;

    @Column(columnDefinition = "varchar(50) not null comment '策略' ")
    private String strategy;

    @Column(columnDefinition = "varchar(500) default null comment '业务回调地址' ")
    private String callBackUrl;

    private Long createAt;

    private Long lastModified;


}
