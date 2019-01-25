package cn.edu.bupt.map.util;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import cn.edu.bupt.map.actor.ConsumerActor;
import cn.edu.bupt.map.actor.ProducerActor;
import cn.edu.bupt.map.context.ActorContext;
import cn.edu.bupt.map.context.ProducerContext;
import cn.edu.bupt.map.context.TopicContext;
import cn.edu.bupt.map.exception.CloseException;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;

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
    private Session session;

    @OnOpen
    public void onOPen(Session session){
        this.session = session;
        String usertype = StringUtil.getRequestParameter(session, "usertype");
        if(usertype.equals("producer")){
            ProducerContext.setContext(this);
        }else if(usertype.equals("consumer")) {
            String sessionId = StringUtil.getRequestParameter(session, "sessionId");
            String sessionIds = StringUtil.getRequestParameter(session, "sessionIds");
            ArrayList<String> ids = new ArrayList<>();
            if (sessionId != null) {
                if(session.equals("all")){
                    for(Session sess : ProducerContext.getContext()){
                        ids.add(StringUtil.getRequestParameter(sess,"username"));
                    }
                }else{
                    ids.add(sessionId);
                }
            }
            if (sessionIds != null) {
                ids.addAll(Arrays.asList(sessionIds.split(",")));
            }
            for (String id : ids) {
                if (TopicContext.getContext( id)) {
                    String fromUserName = id;
                    String toUserName = StringUtil.getRequestParameter(session, "username");
                    String actorName = fromUserName + "-" + toUserName+"-"+UUID.randomUUID().toString().replace("-","");
                    ActorSystem actorSystem = (ActorSystem) SpringUtil.getBean("actorSystem");
                    ActorRef actorRef = actorSystem.actorOf(Props.create(ConsumerActor.class), actorName);
                    actorRef.tell(session, ActorRef.noSender());

                }
            }
        }
        System.out.println("连接成功");
    }
    @OnMessage
    public void onMessage(String message) {
        String actorName = StringUtil.getRequestParameter(session, "username");
        ActorContext.removeContext(actorName);
        ActorSystem actorSystem = (ActorSystem)SpringUtil.getBean("actorSystem");
        actorName = actorName+"-"+UUID.randomUUID().toString().replace("-","");
        ActorRef actorRef = actorSystem.actorOf(Props.create(ProducerActor.class),actorName);
        ActorContext.setContext(actorRef);
        TopicContext.setContext(actorName);
        actorRef.tell(message,ActorRef.noSender());
    }

    @OnClose
    public void onClose(){
        String usertype = StringUtil.getRequestParameter(session, "usertype");
        if(usertype.equals("producer")){
            ProducerContext.removeContext(this);
        }
        System.out.println("断开连接");
    }
    public Session getSession() {
        return session;
    }
}
