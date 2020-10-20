package top.erzhiqian.weixin.account.infrastructure.repository.jdbc;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import top.erzhiqian.weixin.account.infrastructure.po.WeixinAppAccountPO;

import java.util.Optional;

@Repository
public interface WeixinAppJdbcRepository extends CrudRepository<WeixinAppAccountPO, Long> {
    Optional<WeixinAppAccountPO> findByAppId(String appId);
}
