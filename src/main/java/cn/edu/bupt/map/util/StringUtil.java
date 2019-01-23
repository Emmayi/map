package cn.edu.bupt.map.util;

import javax.websocket.Session;
import java.util.List;
import java.util.Map;

/**
 * @author litengfei
 * @email: 1tengfei_2017@sina.com
 * @time 2019/1/23 16:48
 * @Description 描述:获取参数
 */
public class StringUtil {
    /**
     * @author litengfei
     * @created 2019/1/23 17:19
     * @email: tengfei_2017@sina.com
     *
     *  简要描述方法的作用：从websocket请求头中获取参数
     */
    public static String getRequestParameter(Session session,String key){
        Map<String, List<String>> requestParameterMap = session.getRequestParameterMap();
        List<String> strings = requestParameterMap.get(key);
        return strings.get(0);
    }
    /**
     * @author litengfei
     * @created 2019/1/23 17:20
     * @email: tengfei_2017@sina.com
     *
     *  简要描述方法的作用：从akka的path中获取用户名

     */
    public static String getUserNameFromPath(String path ){
        String[] strings = path.split("/");
        String userName = "";
        for(int i = 0 ; i < strings.length; i++){
            if(strings[i].contains("-")){
                userName = strings[i];
                break;
            }
        }
        return userName;
    }
}
