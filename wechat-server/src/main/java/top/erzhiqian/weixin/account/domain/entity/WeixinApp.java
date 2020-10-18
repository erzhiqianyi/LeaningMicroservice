package top.erzhiqian.weixin.account.domain.entity;

import top.erzhiqian.weixin.core.domain.entity.AutoIncrementEntity;
import top.erzhiqian.weixin.lang.EmailAddress;
import top.erzhiqian.weixin.lang.WeixinAppId;

public class WeixinApp extends AutoIncrementEntity {

   private WeixinAppId appId;

   private WeixinAppId originalId;

   private EmailAddress loginAccount;

}
