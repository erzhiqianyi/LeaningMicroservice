package top.erzhiqian.weixin.security.infrastrure.repository.jdbc;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import top.erzhiqian.weixin.security.infrastrure.po.AppSecretPO;

import java.util.Optional;

@Repository
public interface AppSecretJdbcRepository extends CrudRepository<AppSecretPO, Long> {

    Optional<AppSecretPO> findByAppId(String appId);
}
