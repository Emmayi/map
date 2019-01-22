package cn.edu.bupt.map.util;

import akka.actor.Actor;
import akka.actor.IndirectActorProducer;
import org.springframework.context.ApplicationContext;

/**
 * @author litengfei
 * @email: 1tengfei_2017@sina.com
 * @time 2019/1/22 10:37
 * @Description 描述:
 */
public class SpringActorProducer implements IndirectActorProducer {
    final private ApplicationContext applicationContext;
    final private String actorBeanName;

    public SpringActorProducer(ApplicationContext applicationContext, String actorBeanName) {
        this.applicationContext = applicationContext;
        this.actorBeanName = actorBeanName;
    }

    @Override
    public Actor produce() {
        return (Actor) applicationContext.getBean(actorBeanName);
    }

    @Override
    public Class<? extends Actor> actorClass() {
        return (Class<? extends Actor>) applicationContext.getType(actorBeanName);
    }
}
