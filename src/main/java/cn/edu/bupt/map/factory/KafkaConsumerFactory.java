package cn.edu.bupt.map.factory;

import org.apache.kafka.clients.consumer.Consumer;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

import java.util.Map;

/**
 * @author litengfei
 * @email: 1tengfei_2017@sina.com
 * @time 2019/1/24 9:39
 * @Description 描述:
 */
public class KafkaConsumerFactory<K, V> extends DefaultKafkaConsumerFactory<K, V> {
    private Map<String, Object> configs ;
    public KafkaConsumerFactory(Map<String, Object> configs) {
        super(configs);
        this.configs = configs;
    }

    @Override
    public Consumer<K, V> createConsumer() {
        return super.createKafkaConsumer(this.configs);
    }

    /**
     * @author litengfei
     * @created 2019/1/24 10:25
     * @email: tengfei_2017@sina.com
     *
     *  简要描述方法的作用：设置consumer的参数，在创建客户端之前调用

     */
    public void setConfigs(Map<String, Object> configs) {
        this.configs.putAll(configs);
    }
}
