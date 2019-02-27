package hu.davidszabo.davefilesbackend.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

@Service
public class BeanUtil implements ApplicationContextAware {
    private static ApplicationContext applicationContext;
    private static Logger logger = LoggerFactory.getLogger(BeanUtil.class);

    public static <T> T getBean(Class<T> beanClass) {
        if (applicationContext == null)
            logger.error("applicationContext is null");
        else
            logger.info("applicationContext is not null");
        return applicationContext.getBean(beanClass);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContextParameter) throws BeansException {
        logger.info("applicationContext have been set");
        applicationContext = applicationContextParameter;
    }
}
