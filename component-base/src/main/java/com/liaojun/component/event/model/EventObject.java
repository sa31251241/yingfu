package com.liaojun.component.event.model;

/**
 * Created by ChamIt-001 on 2017/11/3.
 */
public class EventObject {

    private String type;
    private Object source;
    private Object data;

    public EventObject(String type){
        setType(type);
    }
    public EventObject(String type,Object data){
        setType(type);
        setData(data);
    }
    public EventObject(String type,Object data,Object source){
        setType(type);
        setData(data);
        setSource(source);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Object getSource() {
        return source;
    }

    public void setSource(Object source) {
        this.source = source;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
