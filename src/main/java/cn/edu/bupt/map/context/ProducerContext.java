package cn.edu.bupt.map.context;

import cn.edu.bupt.map.util.WebsocketUtil;

import javax.websocket.Session;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author litengfei
 * @email: 1tengfei_2017@sina.com
 * @time 2019/1/23 14:17
 * @Description 描述:保存生产数据的客户端
 */
public class ProducerContext {
    private static CopyOnWriteArraySet<WebsocketUtil> copyOnWriteArraySet = new CopyOnWriteArraySet<WebsocketUtil>();
    public static Session getContext(String sessionId){
        Session session = null;
        for (WebsocketUtil webSocket : copyOnWriteArraySet) {
            if (webSocket.getSession().getId().equals(sessionId)) {
                session = webSocket.getSession();
                break;
            }
        }
        return session;
    }
    public static void setContext(WebsocketUtil websocketUtil){
        copyOnWriteArraySet.add(websocketUtil);
    }
    public static void removeContext(WebsocketUtil websocketUtil){
        copyOnWriteArraySet.remove(websocketUtil);
    }
}
