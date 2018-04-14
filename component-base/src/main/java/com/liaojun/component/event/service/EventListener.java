package com.liaojun.component.event.service;

import com.liaojun.component.event.model.EventObject;

/**
 * Created by ChamIt-001 on 2017/11/3.
 */
public interface EventListener {

    void handleEvent(EventObject event);
}
