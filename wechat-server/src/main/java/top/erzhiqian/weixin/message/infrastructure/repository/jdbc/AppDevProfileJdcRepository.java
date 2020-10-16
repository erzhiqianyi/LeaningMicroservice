package top.erzhiqian.weixin.message.infrastructure.repository.jdbc;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import top.erzhiqian.weixin.message.infrastructure.po.AppDevProfilePO;

import java.util.Optional;

@Repository
public interface AppDevProfileJdcRepository extends CrudRepository<AppDevProfilePO, Long> {

    Optional<AppDevProfilePO> findByAppId(String appId);

    @Query(" select  serverToken from  AppDevProfilePO   where appId = :appId ")
    Optional<String> selectMessageToken(String appId);

}

