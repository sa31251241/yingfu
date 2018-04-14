package com.liaojun.component.mybatis.constant;

/**
 * Created by ChamIt-001 on 2017/10/24.
 */
public class ComponentMyBatisConstant {

    public static enum OPERATION_TYPE {
        SAVE(1),UPDATE(2),DELETE(3);
        private Integer value;
        OPERATION_TYPE(Integer value){this.value = value;}
        public Integer getValue(){return this.value;}
    }

    public static enum DB_KEY {
        ID("id");
        private String value;
        DB_KEY(String value){this.value = value;}
        public String getValue(){return this.value;}
    }
}
