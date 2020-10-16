package top.erzhiqian.weixin.message.infrastructure.adapt;

import org.springframework.stereotype.Service;
import top.erzhiqian.weixin.message.domain.entity.AppDevProfile;
import top.erzhiqian.weixin.message.domain.valueobject.WeixinAppId;
import top.erzhiqian.weixin.message.domain.repository.AppDevProfileRepository;
import top.erzhiqian.weixin.message.infrastructure.convert.AppDevProfileConvert;
import top.erzhiqian.weixin.message.infrastructure.po.AppDevProfilePO;
import top.erzhiqian.weixin.message.infrastructure.repository.jdbc.AppDevProfileJdcRepository;

import java.time.Instant;
import java.util.Optional;


@Service
public class AppDevProfileRepositoryAdapt implements AppDevProfileRepository {


    private AppDevProfileJdcRepository jdcRepository;

    private AppDevProfileConvert convert;

    public AppDevProfileRepositoryAdapt(AppDevProfileJdcRepository jdcRepository,
                                        AppDevProfileConvert convert) {
        this.jdcRepository = jdcRepository;
        this.convert = convert;
    }

    @Override
    public AppDevProfile findAppDevProfile(WeixinAppId app) {
        if (null == app) {
            throw new IllegalArgumentException(" illegal app.");
        }
        Optional<AppDevProfilePO> optional = jdcRepository.findByAppId(app.appId());
        if (optional.isPresent()) {
            return optional.map(convert::convertToEntity).get();
        } else {
            return AppDevProfile.emptyProfile(app);
        }
    }

    @Override
    public void saveProfile(AppDevProfile profile) {
        AppDevProfilePO po = convert.convertToPO(profile);
        if (null != profile.id()) {
            po.setLastModified(Instant.now());
        } else {
            po.setCreateAt(Instant.now());
            po.setLastModified(Instant.now());
        }
        po.setAppId(profile.appId());

        jdcRepository.save(po);
    }
}
