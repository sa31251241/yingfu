package com.liaojun.component.event.handler;

import com.liaojun.component.event.annotation.Listen;
import com.liaojun.component.event.service.EventManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by ChamIt-001 on 2017/11/3.
 */
@Component
public class EventListenerAnnotationScaner implements ApplicationListener<ContextRefreshedEvent> {

    private static final Logger logger = LoggerFactory.getLogger(EventListenerAnnotationScaner.class);

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        Map<String,Object> listenerBeanMap = contextRefreshedEvent.getApplicationContext().getBeansWithAnnotation(Listen.class);
        for(Map.Entry<String,Object> entry:listenerBeanMap.entrySet()){
            Listen listener = entry.getValue().getClass().getDeclaredAnnotation(Listen.class);
            for(String type:listener.types()){
                if(entry.getValue() instanceof com.liaojun.component.event.service.EventListener){
                    EventManager.getInstance().put(type,entry.getKey());
                } else {
                    logger.error("eventListener regist error: class is not implements EventListener : {}",entry.getKey());
                }
            }
        }
    }
}
