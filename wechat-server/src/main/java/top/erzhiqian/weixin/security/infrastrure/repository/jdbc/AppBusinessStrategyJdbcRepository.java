package top.erzhiqian.weixin.security.infrastrure.repository.jdbc;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import top.erzhiqian.weixin.security.infrastrure.po.AppBusinessStrategyPO;

import java.util.List;

@Repository
public interface AppBusinessStrategyJdbcRepository
        extends CrudRepository<AppBusinessStrategyPO, Long> {
    List<AppBusinessStrategyPO> findByAppId(String appId);
    List<AppBusinessStrategyPO> findByBusinessType(String businessType);
}
