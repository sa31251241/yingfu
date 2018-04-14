package com.liaojun.component.web.auth.service.impl;

import com.liaojun.component.web.auth.service.AuthTokenManager;
import com.liaojun.component.web.auth.service.AuthTokenValidationScheduler;
import org.springframework.stereotype.Service;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

/**
 * Created by ChamIt-001 on 2017/11/13.
 */
@Service("defaultAuthTokenValidationScheduler")
public class DefaultAuthTokenValidationScheduler implements AuthTokenValidationScheduler,Runnable {

    AuthTokenManager authTokenManager;
    private ScheduledExecutorService scheduledExecutorService;
    private Integer interval = 3600;
    private Boolean enable = true;

    public DefaultAuthTokenValidationScheduler(AuthTokenManager authTokenManager){
        this.authTokenManager = authTokenManager;
    }

    @Override
    public void setInterval(Integer seconds) {
        this.interval = seconds;
    }

    @Override
    public void start(AuthTokenManager manager) {
        if(this.enable && this.interval > 0L) {
            this.scheduledExecutorService = Executors.newSingleThreadScheduledExecutor(new ThreadFactory() {
                public Thread newThread(Runnable r) {
                    Thread thread = new Thread(r);
                    thread.setDaemon(true);
                    return thread;
                }
            });
            this.scheduledExecutorService.scheduleAtFixedRate(this, this.interval, this.interval, TimeUnit.SECONDS);
        }
    }

    @Override
    public void run() {
        this.authTokenManager.validateTokens();
    }
}
