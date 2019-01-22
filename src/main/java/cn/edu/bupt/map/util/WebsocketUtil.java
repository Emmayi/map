package cn.edu.bupt.map.util;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import cn.edu.bupt.map.actor.ProducerActor;
import cn.edu.bupt.map.exception.CloseException;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.util.List;
import java.util.Map;

/**
 * @author litengfei
 * @email: 1tengfei_2017@sina.com
 * @time 2019/1/21 10:54
 * @Description 描述:
 * websocket通信工具类
 */
@ServerEndpoint(value = "/api/v1/map/websocket")
@Component
public class WebsocketUtil {

    @OnOpen
    public void onOPen(Session session){
        //KafkaTemplate kafkaTemplate = (KafkaTemplate)SpringUtil.getBean("kafkaTemplate");
        Map<String, List<String>> requestParameterMap = session.getRequestParameterMap();
        System.out.println(requestParameterMap);
        ActorSystem actorSystem = (ActorSystem)SpringUtil.getBean("actorSystem");
        ActorRef pf = actorSystem.actorOf(Props.create(ProducerActor.class), "pf");
        pf.tell(session.getId(),ActorRef.noSender());
        System.out.println("************************************");
        //System.out.println(kafkaTemplate);
        System.out.println(actorSystem);
        System.out.println("连接成功");
    }

    @OnClose
    public void onClose(){
        ActorSystem actorSystem = (ActorSystem)SpringUtil.getBean("actorSystem");
        ActorRef pf = actorSystem.actorFor("akka://ActorSystem/user/pf");
        pf.tell(new CloseException(),ActorRef.noSender());
        System.out.println("断开连接");
    }
}
