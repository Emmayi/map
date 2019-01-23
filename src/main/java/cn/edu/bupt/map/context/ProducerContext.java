package cn.edu.bupt.map.context;

import cn.edu.bupt.map.util.WebsocketUtil;

import javax.websocket.Session;
import java.util.ArrayList;
import java.util.Iterator;
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
    /**
     * @author litengfei
     * @created 2019/1/23 19:00
     * @email: tengfei_2017@sina.com
     *
     *  简要描述方法的作用：获取所有数据上传客户端的session

     */
    public static ArrayList<Session> getContext(){
        if(copyOnWriteArraySet ==null){
            return new ArrayList<>();
        }else{
            Iterator<WebsocketUtil> iterator = copyOnWriteArraySet.iterator();
            ArrayList<Session> sessions = new ArrayList<>();
            while(iterator.hasNext()){
                WebsocketUtil websocketUtil =  (WebsocketUtil)iterator.next();
                sessions.add(websocketUtil.getSession());
            }

            return sessions;
        }
    }
    public static void setContext(WebsocketUtil websocketUtil){
        copyOnWriteArraySet.add(websocketUtil);
    }
    public static void removeContext(WebsocketUtil websocketUtil){
        copyOnWriteArraySet.remove(websocketUtil);
    }
}
