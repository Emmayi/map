package cn.edu.bupt.map.controller;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import cn.edu.bupt.map.actor.ConsumerActor;
import cn.edu.bupt.map.context.ProducerContext;
import cn.edu.bupt.map.util.SpringUtil;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.Session;
import java.util.List;
import java.util.Map;

/**
 * @author litengfei
 * @email: 1tengfei_2017@sina.com
 * @time 2019/1/23 13:56
 * @Description 描述: 通过websocket推送消息
 */
@RestController
@RequestMapping("/api/v1/map")
@CrossOrigin
public class WebsocketController  {

    @RequestMapping(value = "single")
    @ResponseBody
    public String sendSingleMessage(String sessionId) throws Exception{
        if(sessionId ==null){
            return "缺少必传参数";
        }else{
            Session session = ProducerContext.getContext(sessionId);
            Map<String, List<String>> requestParameterMap = session.getRequestParameterMap();
            String userName = requestParameterMap.get("userName").get(0);
            String actorName = userName+"-"+"consumer";
            ActorSystem actorSystem = (ActorSystem)SpringUtil.getBean("actorSystem");
            ActorRef actorRef = actorSystem.actorFor("akka://ActorSystem/user/" + actorName);
            if(actorRef == null){
                actorRef = actorSystem.actorOf(Props.create(ConsumerActor.class),actorName);
            }
            actorRef.tell(session,ActorRef.noSender());
            return "发送成功";
        }
    }

    /**
     * @author litengfei
     * @created 2019/1/23 14:15
     * @email: tengfei_2017@sina.com
     *
     *  简要描述方法的作用：向连接的客户端群发消息
     */
    @RequestMapping(value = "group")
    @ResponseBody
    public String sendGroupMessage() throws Exception{
        return "发送成功";
    }
}
