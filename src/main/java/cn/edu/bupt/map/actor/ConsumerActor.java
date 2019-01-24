package cn.edu.bupt.map.actor;

import akka.actor.UntypedActor;
import cn.edu.bupt.map.exception.CloseException;
import cn.edu.bupt.map.factory.KafkaConsumerFactory;
import cn.edu.bupt.map.util.SpringUtil;
import cn.edu.bupt.map.util.StringUtil;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;

import javax.websocket.Session;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author litengfei
 * @email: 1tengfei_2017@sina.com
 * @time 2019/1/23 14:24
 * @Description 描述:
 */
public class ConsumerActor extends UntypedActor {
    @Override
    public void onReceive(Object message) throws Exception {
        if (message instanceof CloseException) {
            getContext().stop(getSelf());
        } else {
            KafkaConsumerFactory consumerFactory = (KafkaConsumerFactory) SpringUtil.getBean("consumerFactory");
            if (message instanceof Session) {
                Session session = (Session) message;
                String[] topics = StringUtil.getUserNameFromPath(getSelf().path().toString()).split("-");
                Map<String, Object> propsMap = new HashMap<>();
                propsMap.put(ConsumerConfig.GROUP_ID_CONFIG, topics[1]);//设置consumer的groupid
                consumerFactory.setConfigs(propsMap);
                Consumer consumer = consumerFactory.createConsumer();
                consumer.subscribe(Collections.singletonList(topics[0]));
               while(true){
                   ConsumerRecords<String, String> records = consumer.poll(1000);
                   for (ConsumerRecord<String, String> record : records) {
                       //session.getBasicRemote().sendText(record.key()+":"+record.value());
                       System.out.println(record.key());
                       System.out.println(record.value());
                   }
               }

            }

        }
    }
}
