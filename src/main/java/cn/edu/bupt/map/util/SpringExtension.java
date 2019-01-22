package cn.edu.bupt.map.util;

import akka.actor.*;
import org.springframework.context.ApplicationContext;

/**
 * @author litengfei
 * @email: 1tengfei_2017@sina.com
 * @time 2019/1/22 10:34
 * @Description 描述:
 */
public class SpringExtension extends AbstractExtensionId<SpringExtension.SpringExt> {
    public static final SpringExtension SpringExtProvider = new SpringExtension();
    @Override
    public SpringExt createExtension(ExtendedActorSystem system) {
        return new SpringExt();
    }
    public static class SpringExt implements Extension {
        private volatile ApplicationContext applicationContext;

        public void initialize(ApplicationContext applicationContext) {
            this.applicationContext = applicationContext;
        }

        public Props props(String actorBeanName) {
            return Props.create(SpringActorProducer.class, applicationContext, actorBeanName);
        }
    }
}