package cn.edu.bupt.map.context;

import akka.actor.ActorRef;
import cn.edu.bupt.map.exception.CloseException;
import cn.edu.bupt.map.util.WebsocketUtil;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArraySet;

public class ActorContext {
    private static CopyOnWriteArraySet<ActorRef> copyOnWriteArraySet = new CopyOnWriteArraySet<ActorRef>();
    public static void setContext(ActorRef actorRef){
        copyOnWriteArraySet.add(actorRef);

    }
    public static  void removeContext(String actorName){
        Iterator<ActorRef> iterator = copyOnWriteArraySet.iterator();
        ArrayList<ActorRef> list = new ArrayList<>();
        while (iterator.hasNext()){
            ActorRef actorRef = (ActorRef)iterator.next();
            if(actorRef.path().toString().contains(actorName)){
                actorRef.tell(new CloseException(),ActorRef.noSender());
                list.add(actorRef);
            }
        }
        for(int i = 0 ; i< list.size();i++){
            copyOnWriteArraySet.remove(list.get(i));
        }
    }
    public static void removeContext(ActorRef actorRef){
        copyOnWriteArraySet.remove(actorRef);
    }
}
