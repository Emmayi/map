package cn.edu.bupt.map.context;

import cn.edu.bupt.map.util.SpringUtil;
import org.apache.kafka.clients.consumer.Consumer;
import org.springframework.kafka.core.ConsumerFactory;

import java.util.Map;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author litengfei
 * @email: 1tengfei_2017@sina.com
 * @time 2019/1/23 19:19
 * @Description 描述:
 */
public class TopicContext {
    private static CopyOnWriteArraySet<String> copyOnWriteArraySet = new CopyOnWriteArraySet<String>();
    private static TopicContext ourInstance = new TopicContext();
    public static TopicContext getInstance() {
        return ourInstance;
    }
    private TopicContext() {
        ConsumerFactory consumerFactory = (ConsumerFactory) SpringUtil.getBean("consumerFactory");
        Consumer consumer = consumerFactory.createConsumer();
        Map listTopics = consumer.listTopics();
        for(Object key:listTopics.keySet()){
            copyOnWriteArraySet.add(key.toString());
        }
    }
    public static boolean getContext(String topic){
        if(copyOnWriteArraySet == null){
            return  false;
        }else{
            return copyOnWriteArraySet.contains(topic);
        }
    }


    public static void setContext(String topic){
        copyOnWriteArraySet.add(topic);
    }
    public static void removeContext(String topic){
        copyOnWriteArraySet.remove(topic);
    }
}
