package top.erzhiqian.weixin.security.infrastrure.repository.jdbc;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import top.erzhiqian.weixin.security.infrastrure.po.AppAccessTokenPO;

import java.util.Optional;

@Repository
public interface AppAccessTokenJdbcRepository extends CrudRepository<AppAccessTokenPO, Long> {

    Optional<AppAccessTokenPO> findTop1ByAppIdOrderByCreateAtDesc(String appId);
}
