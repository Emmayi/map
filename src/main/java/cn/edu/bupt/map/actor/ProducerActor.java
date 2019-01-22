package cn.edu.bupt.map.actor;

import akka.actor.OneForOneStrategy;
import akka.actor.SupervisorStrategy;
import akka.actor.UntypedActor;
import cn.edu.bupt.map.exception.CloseException;
import cn.edu.bupt.map.util.SpringUtil;
import org.springframework.kafka.core.KafkaTemplate;
import scala.concurrent.duration.Duration;

/**
 * @author litengfei
 * @email: 1tengfei_2017@sina.com
 * @time 2019/1/22 11:05
 * @Description 描述:接受实时上传数据
 */
public class ProducerActor extends UntypedActor {
    @Override
    public void onReceive(Object message) throws Exception {
        if(message instanceof String){
            System.out.println(message);
            KafkaTemplate kafkaTemplate = (KafkaTemplate)SpringUtil.getBean("kafkaTemplate");
            kafkaTemplate.send("map",getSelf().path(),message);
        }else{
            if (message instanceof  CloseException ){
                getContext().stop(getSelf());
            }
        }
    }

//    @Override
//    public SupervisorStrategy supervisorStrategy(){
//       return  strategy;
//    }
//    private final SupervisorStrategy strategy = new OneForOneStrategy(3, Duration.create("1 minute"), t -> {
//        if (t instanceof RuntimeException) {
//            return SupervisorStrategy.stop();
//        } else {
//            return SupervisorStrategy.restart();
//        }
//    });
}