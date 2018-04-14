package com.liaojun.component.base.util;

import com.liaojun.component.base.util.ApplicationContextUtil;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * Created by ChamIt-001 on 2017/10/19.
 */
public class DefaultThreadPool {

    private static ThreadPoolTaskExecutor threadPoolTaskExecutor;

    private DefaultThreadPool(){}

    public static ThreadPoolTaskExecutor getInstance(){
        if(threadPoolTaskExecutor == null){
            threadPoolTaskExecutor = ApplicationContextUtil.getContext().getBean(ThreadPoolTaskExecutor.class);
        }
        return threadPoolTaskExecutor;
    }
}
