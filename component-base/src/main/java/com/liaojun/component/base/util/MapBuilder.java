package com.liaojun.component.base.util;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ChamIt-001 on 2017/12/12.
 */
public class MapBuilder {

    private Map<String,Object> queryMap;

    private MapBuilder(){
        queryMap = new HashMap<>();
    }

    public static MapBuilder create(){
        return new MapBuilder();
    }

    public static MapBuilder create(String key,Object value){
        return new MapBuilder().set(key,value);
    }

    public MapBuilder set(String key, Object value){
        queryMap.put(key,value);
        return this;
    }

    public MapBuilder setAll(Map<String,Object> queryMap){
        if(queryMap != null) {
            this.queryMap.putAll(queryMap);
        }
        return this;
    }

    public Map<String,Object> build(){
        return this.queryMap;
    }

    public Map<String,String> buildStringValues() {
        Map<String,String> resultMap = new HashMap<>();
        for(Map.Entry<String,Object> entry:this.queryMap.entrySet()){
            resultMap.put(entry.getKey(),entry.getValue().toString());
        }
        return resultMap;
    }
}
