package cn.edu.bupt.map.context;

import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author litengfei
 * @email: 1tengfei_2017@sina.com
 * @time 2019/1/23 19:19
 * @Description 描述:
 */
public class TopicContext {
    private static CopyOnWriteArraySet<String> copyOnWriteArraySet = new CopyOnWriteArraySet<String>();
    public static boolean getContext(String topic){
        if(copyOnWriteArraySet == null){
            return  false;
        }else{
            return copyOnWriteArraySet.contains(topic);
        }
    }


    public static void setContext(String topic){
        copyOnWriteArraySet.add(topic);
    }
    public static void removeContext(String topic){
        copyOnWriteArraySet.remove(topic);
    }
}
