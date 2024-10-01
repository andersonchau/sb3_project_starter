package ac.proj.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Slf4j
public class DebugUtils {

    public static void printList(List<? extends Object> myList , String... msgList ) {
        for(int i=0;i<msgList.length;i++) {
            log.info(msgList[i]);
        }
        if (CollectionUtils.isEmpty(myList))
            log.info( "List is Empty");
        else {
            log.info( "List is has size {} " , myList.size());
            myList.stream().forEach((obj -> log.info(obj.toString())));
        }
    }

}
