package cn.edu.bupt.map.config;

import akka.actor.ActorSystem;
import static cn.edu.bupt.map.util.SpringExtension.SpringExtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author litengfei
 * @email: 1tengfei_2017@sina.com
 * @time 2019/1/22 10:29
 * @Description 描述: akka的配置文件
 */
@Configuration
public class AkkaConfig {
    @Autowired
    private ApplicationContext applicationContext;
    @Bean(value = "actorSystem")
    public ActorSystem actorSystem() {
        ActorSystem actorSystem = ActorSystem.create("ActorSystem");
        SpringExtProvider.get(actorSystem).initialize(applicationContext);
        return actorSystem;
    }
}
