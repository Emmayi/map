package cn.edu.bupt.map.util;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import cn.edu.bupt.map.actor.ConsumerActor;
import cn.edu.bupt.map.actor.ProducerActor;
import cn.edu.bupt.map.context.ProducerContext;
import cn.edu.bupt.map.exception.CloseException;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArraySet;

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
    private static CopyOnWriteArraySet<ActorRef> copyOnWriteArraySet = new CopyOnWriteArraySet<ActorRef>();

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
                        ids.add(sess.getId());
                    }
                }else{
                    ids.add(sessionId);
                }
            }
            if (sessionIds != null) {
                ids.addAll(Arrays.asList(sessionIds.split(",")));
            }
            for (String id : ids) {
                Session s = ProducerContext.getContext(id);
                if (s != null) {
                    String fromUserName = StringUtil.getRequestParameter(s, "username");
                    String toUserName = StringUtil.getRequestParameter(session, "username");
                    String actorName = fromUserName + "-" + toUserName;
                    ActorSystem actorSystem = (ActorSystem) SpringUtil.getBean("actorSystem");
                    ActorRef actorRef = actorSystem.actorFor("akka://ActorSystem/user/" + actorName);
                    if (actorRef == null) {
                        actorRef = actorSystem.actorOf(Props.create(ConsumerActor.class), actorName);
                    }
                    copyOnWriteArraySet.add(actorRef);
                    actorRef.tell(session, ActorRef.noSender());
                }
            }
        }

        //KafkaTemplate kafkaTemplate = (KafkaTemplate)SpringUtil.getBean("kafkaTemplate");
//        Map<String, List<String>> requestParameterMap = session.getRequestParameterMap();
//        System.out.println(requestParameterMap);
//        ActorSystem actorSystem = (ActorSystem)SpringUtil.getBean("actorSystem");
//        ActorRef pf = actorSystem.actorOf(Props.create(ProducerActor.class), "pf");
//        pf.tell(session.getId(),ActorRef.noSender());
//        System.out.println("************************************");
//        //System.out.println(kafkaTemplate);
//        System.out.println(actorSystem);
        System.out.println("连接成功");
    }
    @OnMessage
    public void onMessage(String message) {
        String userName = StringUtil.getRequestParameter(session, "username");
        String actorName = userName+"-"+"producer";
        ActorSystem actorSystem = (ActorSystem)SpringUtil.getBean("actorSystem");
        ActorRef actorRef = actorSystem.actorFor("akka://ActorSystem/user/" + actorName);
        if(actorRef == null){
            actorRef = actorSystem.actorOf(Props.create(ProducerActor.class),actorName);
        }
        actorRef.tell(message,ActorRef.noSender());
    }

    @OnClose
    public void onClose(){
        String usertype = StringUtil.getRequestParameter(session, "usertype");
        if(usertype.equals("producer")){
            String userName = StringUtil.getRequestParameter(session, "username");
            String actorName = userName+"-"+"producer";
            ActorSystem actorSystem = (ActorSystem)SpringUtil.getBean("actorSystem");
            ActorRef actorRef = actorSystem.actorFor("akka://ActorSystem/user/" + actorName);
            actorRef.tell(new CloseException(),ActorRef.noSender());
            ProducerContext.removeContext(this);
        }else if(usertype.equals("consumer")){
            Iterator<ActorRef> iterator = copyOnWriteArraySet.iterator();
            while (iterator.hasNext()){
                ActorRef actorRef = (ActorRef)iterator.next();
                actorRef.tell(new CloseException(),ActorRef.noSender());
            }
        }

        System.out.println("断开连接");
    }
    public Session getSession() {
        return session;
    }
}
