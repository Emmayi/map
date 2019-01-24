package cn.edu.bupt.map.actor;

import akka.actor.UntypedActor;
import cn.edu.bupt.map.exception.CloseException;
import cn.edu.bupt.map.util.SpringUtil;
import cn.edu.bupt.map.util.StringUtil;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.Date;

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
            KafkaTemplate kafkaTemplate = (KafkaTemplate)SpringUtil.getBean("kafkaTemplate");
            String topic = StringUtil.getUserNameFromPath(getSelf().path().toString());
            kafkaTemplate.send(topic,String.valueOf((new Date()).getTime()),message);
        }else{
            if (message instanceof  CloseException ){
                getContext().stop(getSelf());
            }
        }
    }
}
