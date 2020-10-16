package top.erzhiqian.weixin.spring;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class ApplicationContextHolder implements ApplicationContextAware {
    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        synchronized (this) {
            if (ApplicationContextHolder.applicationContext == null) {
                ApplicationContextHolder.applicationContext = applicationContext;
            }
        }
    }

    public static <T> T getBean(Class<T> clazz) {
        try {
            return applicationContext.getBean(clazz);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public static <T> T getBean(String qualifier, Class<T> clazz) {
        try {
            return applicationContext.getBean(qualifier, clazz);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
