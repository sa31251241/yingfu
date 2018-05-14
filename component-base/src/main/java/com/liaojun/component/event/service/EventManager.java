package com.liaojun.component.event.service;

import com.liaojun.component.base.util.ApplicationContextUtil;
import com.liaojun.component.event.model.EventObject;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ChamIt-001 on 2017/11/3.
 */
public class EventManager {

    private static EventManager eventManagerInstance;

    private Map<String,List<String>> listenerPool;
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    public EventManager(){
        listenerPool = new HashMap<>();
    }

    public static EventManager getInstance(){
        if(eventManagerInstance == null){
            eventManagerInstance = new EventManager();
        }
        return eventManagerInstance;
    }

    public void put(String type,String beanName){
        if(listenerPool.get(type) == null){
            listenerPool.put(type,new ArrayList<>());
        }
        if(!listenerPool.get(type).contains(beanName)){
            listenerPool.get(type).add(beanName);
        }
    }

    public void trigger(String type){
        trigger(type,null);
    }

    public void triggerThread(String type) { triggerThread(type,null);}

    public void trigger(String type,Object data){
        trigger(type,data,null);
    }

    public void triggerThread(String type,Object data){
        triggerThread(type,data,null);
    }

    public void trigger(String type,Object data,Object source){
        trigger(new EventObject(type,data,source),false);
    }

    public void triggerThread(String type,Object data,Object source) { trigger(new EventObject(type,data,source),true);}

    public void trigger(final EventObject event,Boolean threadExecute){
        if(listenerPool.get(event.getType()) != null){
            if(threadExecute){
                getThreadPoolTaskExecutor().execute(new Runnable() {
                    @Override
                    public void run() {
                        for(String listenerBeanName:listenerPool.get(event.getType())){
                            ((EventListener)ApplicationContextUtil.getContext().getBean(listenerBeanName)).handleEvent(event);
                        }
                    }
                });
            } else {
                for(String listenerBeanName:listenerPool.get(event.getType())){
                    ((EventListener)ApplicationContextUtil.getContext().getBean(listenerBeanName)).handleEvent(event);
                }
            }
        }
    }

    private ThreadPoolTaskExecutor getThreadPoolTaskExecutor(){
        if(threadPoolTaskExecutor == null){
            threadPoolTaskExecutor = ApplicationContextUtil.getContext().getBean(ThreadPoolTaskExecutor.class);
        }
        return threadPoolTaskExecutor;
    }
}
