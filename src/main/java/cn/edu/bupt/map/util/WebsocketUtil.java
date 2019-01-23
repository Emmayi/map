package cn.edu.bupt.map.util;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import cn.edu.bupt.map.actor.ProducerActor;
import cn.edu.bupt.map.context.ConsumerContext;
import cn.edu.bupt.map.context.ProducerContext;
import cn.edu.bupt.map.exception.CloseException;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
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
    private static CopyOnWriteArraySet<WebsocketUtil> copyOnWriteArraySet = new CopyOnWriteArraySet<WebsocketUtil>();

    @OnOpen
    public void onOPen(Session session){
        this.session = session;
        String usertype = StringUtil.getRequestParameter(session, "usertype");
        if(usertype.equals("producer")){
            ProducerContext.setContext(this);
        }else if(usertype.equals("consumer")){
            ConsumerContext.setContext(this);
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
            String userName = StringUtil.getRequestParameter(session, "username");
            String actorName = userName+"-"+"consumer";
            ActorSystem actorSystem = (ActorSystem)SpringUtil.getBean("actorSystem");
            ActorRef actorRef = actorSystem.actorFor("akka://ActorSystem/user/" + actorName);
            actorRef.tell(new CloseException(),ActorRef.noSender());
            ProducerContext.removeContext(this);
            ConsumerContext.removeContext(this);
        }
//        ActorSystem actorSystem = (ActorSystem)SpringUtil.getBean("actorSystem");
//        ActorRef pf = actorSystem.actorFor("akka://ActorSystem/user/pf");
//        pf.tell(new CloseException(),ActorRef.noSender());
        System.out.println("断开连接");
    }

/**
 * @author litengfei
 * @created 2019/1/23 13:52
 * @email: tengfei_2017@sina.com
 *
 *  简要描述方法的作用：向连接的客户端群发消息
 */
    public void sendMessage(String message) {

        for (WebsocketUtil webSocket : copyOnWriteArraySet) {
            try {
                webSocket.session.getBasicRemote().sendText(message);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * @author litengfei
     * @created 2019/1/23 13:54
     * @email: tengfei_2017@sina.com
     *
     *  简要描述方法的作用：向连接的客户端发送消息
     */
    public void sendMessage(String sessionId, String message) throws IOException {
        Session session = null;
        WebsocketUtil tempWebSocket = null;
        for (WebsocketUtil webSocket : copyOnWriteArraySet) {
            if (webSocket.session.getId().equals(sessionId)) {
                tempWebSocket = webSocket;
                session = webSocket.session;
                break;
            }
        }
        if (session != null) {
            tempWebSocket.session.getBasicRemote().sendText(message);
        }
    }

    public Session getSession() {
        return session;
    }
}
