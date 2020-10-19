package top.erzhiqian.weixin.account.domain.service;

import top.erzhiqian.weixin.account.domain.entity.WeixinAppContext;
import top.erzhiqian.weixin.account.domain.valueobject.WeixinAccountHostType;
import top.erzhiqian.weixin.account.domain.valueobject.WeixinAppType;
import top.erzhiqian.weixin.account.domain.valueobject.WeixinCertifiedState;
import top.erzhiqian.weixin.account.domain.valueobject.state.*;

public class WeixinAppStateHelper {
    public static WeixinAppState checkState(
            WeixinAppType type, WeixinCertifiedState certifiedState,
            WeixinAccountHostType hostType,
            WeixinAppContext context) {
        if (null == hostType) {
            throw new IllegalArgumentException("illegal host type.");
        }
        switch (hostType) {
            case PERSONAL:
                return checkPersonalApp(type, context);
            case GOVERNMENT:
            case COMPANY:
            case NGO:
                return checkNotPersonalApp(type, certifiedState, context);
            default:
                throw new IllegalArgumentException("illegal host type.");
        }

    }

    private static WeixinAppState checkNotPersonalApp(
            WeixinAppType type,
            WeixinCertifiedState certifiedState,
            WeixinAppContext context) {

        switch (certifiedState) {
            case CERTIFIED:
                return checkCertifiedNotPersonalApp(type, context);
            case UN_CERTIFIED:
                return checkUncertifiedNotPersonalApp(type, context);
            default:
                throw new IllegalArgumentException("illegal certified");
        }

    }

    private static WeixinAppState checkUncertifiedNotPersonalApp(WeixinAppType type, WeixinAppContext context) {
        switch (type) {
            case SUBSCRIBE:
                return new UncertifiedOrganizationSubscribe(context);
            case SERVICES:
                return new UnCertifiedServiceApp(context);
            case MINI_PROGRAM:
                return new UnCertifiedMiniProgram(context);
            default:
                throw new IllegalArgumentException(" illegal app type.");
        }
    }

    private static WeixinAppState checkCertifiedNotPersonalApp(WeixinAppType type, WeixinAppContext context) {
        switch (type) {
            case SUBSCRIBE:
                return new CertifiedOrganizationSubscribe(context);
            case SERVICES:
                return new CertifiedServiceApp(context);
            case MINI_PROGRAM:
                return new CertifiedMiniProgram(context);
            default:
                throw new IllegalArgumentException(" illegal app type.");

        }
    }

    private static WeixinAppState checkPersonalApp(WeixinAppType type, WeixinAppContext context) {
        switch (type) {
            case SUBSCRIBE:
                return new PersonalSubscribe(context);
            case MINI_PROGRAM:
                return new PersonalMiniProgram(context);
            default:
                throw new IllegalArgumentException("illegal app type.");

        }

    }
}
