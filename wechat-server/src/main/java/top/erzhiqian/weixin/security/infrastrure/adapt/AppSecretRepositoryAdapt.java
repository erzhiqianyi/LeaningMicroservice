package top.erzhiqian.weixin.security.infrastrure.adapt;

import org.springframework.stereotype.Service;
import top.erzhiqian.weixin.message.domain.valueobject.WeixinAppId;
import top.erzhiqian.weixin.security.domain.entity.AppSecret;
import top.erzhiqian.weixin.security.domain.repository.AppSecretRepository;
import top.erzhiqian.weixin.security.infrastrure.convert.AppSecretConvert;
import top.erzhiqian.weixin.security.infrastrure.po.AppSecretPO;
import top.erzhiqian.weixin.security.infrastrure.repository.jdbc.AppSecretJdbcRepository;

import java.util.Optional;

@Service
public class AppSecretRepositoryAdapt implements AppSecretRepository {

    private AppSecretJdbcRepository repository;

    private AppSecretConvert convert;

    public AppSecretRepositoryAdapt(AppSecretJdbcRepository repository, AppSecretConvert convert) {
        this.repository = repository;
        this.convert = convert;
    }

    @Override
    public AppSecret findAppSecret(WeixinAppId app) {
        if (null == app) {
            throw new IllegalArgumentException(" illegal app.");
        }
        Optional<AppSecretPO> optional = repository.findByAppId(app.appId());
        if (optional.isPresent()){
            AppSecretPO po = optional.get();
            return AppSecret.restore(po.getId(),app,po.getAppSecret());
        }else {
            return AppSecret.emptySecret(app);
        }
    }

    @Override
    public void saveAppSecret(AppSecret appSecret) {
        AppSecretPO original = selectById(appSecret.id());
        AppSecretPO po = convert.convertToPO(appSecret,original);
        repository.save(po);
    }

    private AppSecretPO selectById(Long id) {
        if (null == id){
            return null;
        }
        Optional<AppSecretPO> optional = repository.findById(id);
        if (optional.isPresent()){
            return optional.get();
        }else {
            return null;
        }

    }
}
