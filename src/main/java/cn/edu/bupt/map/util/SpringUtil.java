package cn.edu.bupt.map.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author litengfei
 * @email: 1tengfei_2017@sina.com
 * @time 2019/1/21 16:26
 * @Description 描述: 获取springbean的util方法
 */
@Component
public class SpringUtil implements ApplicationContextAware {
    private static ApplicationContext applicationContext;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringUtil.applicationContext = applicationContext;
    }
    /**
     * @author litengfei
     * @created 2019/1/21 17:57
     * @email: tengfei_2017@sina.com
     *
     *  简要描述方法的作用：通过名称获取bean
     */
    public static Object getBean(String name) {
        return applicationContext.getBean(name);
    }
}
