package top.erzhiqian.weixin.account.infrastructure.repository.jdbc;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import top.erzhiqian.weixin.account.infrastructure.po.WeixinAppHostPO;

import java.util.Optional;

@Repository
public interface WeixinAppHostJdbcRepository extends CrudRepository<WeixinAppHostPO, Long> {
    Optional<WeixinAppHostPO> findByAppId(String appId);
}
