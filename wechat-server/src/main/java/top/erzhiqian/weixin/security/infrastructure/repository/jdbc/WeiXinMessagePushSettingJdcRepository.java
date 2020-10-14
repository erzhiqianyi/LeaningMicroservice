package top.erzhiqian.weixin.security.infrastructure.repository.jdbc;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import top.erzhiqian.weixin.security.infrastructure.po.WeiXinMessagePushSettingPO;

import java.util.Optional;

@Repository
public interface WeiXinMessagePushSettingJdcRepository extends CrudRepository<WeiXinMessagePushSettingPO, Long> {


    @Query(" select  token from  WeiXinMessagePushSettingPO   where appId = :appId ")
    Optional<WeiXinMessagePushSettingPO> selectMessageToken(String appId);
}
