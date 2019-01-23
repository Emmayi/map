package cn.edu.bupt.map.actor;

import akka.actor.UntypedActor;
import cn.edu.bupt.map.exception.CloseException;
import cn.edu.bupt.map.util.SpringUtil;
import cn.edu.bupt.map.util.StringUtil;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.springframework.kafka.core.ConsumerFactory;

import javax.websocket.Session;
import java.util.Collections;

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
            ConsumerFactory consumerFactory = (ConsumerFactory) SpringUtil.getBean("consumerFactory");
            if (message instanceof Session) {
                Session session = (Session) message;
                Consumer consumer = consumerFactory.createConsumer();
                consumer.subscribe(Collections.singletonList(StringUtil.getUserNameFromPath(getSelf().path().toString()).split("-")[0]));
                ConsumerRecords<String, String> records = consumer.poll(100);
                for (ConsumerRecord<String, String> record : records) {
                    session.getBasicRemote().sendText(record.key()+":"+record.value());
                    System.out.println(record.key());
                    System.out.println(record.value());
                }
            }

        }
    }
}
