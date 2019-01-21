package cn.edu.bupt.map.util;

import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

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
        System.out.println("连接成功");
    }

    @OnClose
    public void onClose(){
        System.out.println("断开连接");
    }
}
