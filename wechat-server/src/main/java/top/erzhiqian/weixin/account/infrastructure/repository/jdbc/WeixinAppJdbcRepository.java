package top.erzhiqian.weixin.account.infrastructure.repository.jdbc;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import top.erzhiqian.weixin.account.infrastructure.po.WeixinAppPO;

@Repository
public interface WeixinAppJdbcRepository  extends CrudRepository<WeixinAppPO,Long> {
}
