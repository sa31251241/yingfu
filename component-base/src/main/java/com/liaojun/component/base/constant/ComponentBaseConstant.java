package com.liaojun.component.base.constant;

/**
 * Created by ChamIt-001 on 2017/10/18.
 */
public class ComponentBaseConstant {

    public static enum SORT_REQUEST_DIRECT {
        ASC("asc"),DESC("desc");
        private String value;
        SORT_REQUEST_DIRECT(String value){this.value = value;}
        public String getValue(){return this.value;}
    }

    public static final class BASE_ERROR_CODE {
        public static final String REQUEST_PARSE_ERROR = "-32700";
        public static final String INVALID_REQUEST = "-32600";
        public static final String METHOD_NOT_FOUND = "-32601";
        public static final String INVALID_PARAMS = "-32602";
        public static final String INTERNAL_ERROR = "-32603";
        public static final String SUCCESS = "0";
        public static final String PARAM_CHECK_ERROR = "100";
        public static final String CONVERT_ERROR = "110";
        public static final String AUTH_NOT_LOGIN = "401";
        public static final String AUTH_FORBIDDEN = "403";
    }

    public static final class COMMON_STATUS {
        public static final Integer YES = 1;
        public static final Integer NO = 0;
    }

    public static enum OPERATION_TYPE {
        SAVE(1),UPDATE(2),DELETE(3),SAVEORUPDATE(4);
        private Integer value;
        OPERATION_TYPE(Integer value){this.value = value;}
        public Integer getValue(){return this.value;}
    }
}
