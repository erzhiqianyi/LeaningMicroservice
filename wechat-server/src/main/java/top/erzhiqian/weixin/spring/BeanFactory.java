package top.erzhiqian.weixin.spring;

public class BeanFactory {

    public static <S> S getBusinessImpl(String serviceName, Class<S> strategyType) {
        S strategy = ApplicationContextHolder.getBean(serviceName, strategyType);
        if (null == strategy) {
            throw new IllegalArgumentException("illegal service name.");
        }
        return strategy;
    }

}
