package cn.edu.bupt.map.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @author litengfei
 * @email: 1tengfei_2017@sina.com
 * @time 2019/1/21 16:26
 * @Description 描述:
 */
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

     * (这里描述这个方法适用条件 – 可选)

     * (这里描述这个方法的执行流程 – 可选)

     * (这里描述这个方法的使用方法 – 可选)

     * (这里描述这个方法的注意事项 – 可选)
     */
    public static Object getBean(String name) {
        return applicationContext.getBean(name);
    }
}
