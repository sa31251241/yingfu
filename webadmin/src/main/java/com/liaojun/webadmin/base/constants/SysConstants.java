package com.liaojun.webadmin.base.constants;

import com.liaojun.component.base.annotation.ConstantAnnotation;
import com.liaojun.component.base.constant.ComponentBaseConstant;
import org.springframework.stereotype.Component;
import java.lang.reflect.Field;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Author: yangzi
 * @Date: 2018/4/23 16:27
 */

@Component
public class SysConstants extends ComponentBaseConstant{

    /**
     * 初始化所有常量
     */
    static {
        try {
            // 获取所有内部类
            for (Class cls : SysConstants.class.getDeclaredClasses()) {
                // 存放key和desc的map
                Map<String, String> keyDescMap = new LinkedHashMap<String, String>();
                // 存放key和value的map
                Map<String, Object> keyValueMap = new LinkedHashMap<String, Object>();
                // 存放value和key的map
                Map<Object, String> valueKeyMap = new LinkedHashMap<Object, String>();
                // 存放value和desc的map
                Map<Object, String> valueDescMap = new LinkedHashMap<Object, String>();
                // 存放desc和value的map
                Map<String, Object> descValueMap = new LinkedHashMap<String, Object>();
                // 存放desc和key的map
                Map<String, String> descKeyMap = new LinkedHashMap<String, String>();
                // 每个内部类-获取所有属性（不包括父类的）
                for (Field fd : cls.getDeclaredFields()) {
                    fd.setAccessible(true);
                    // 每个属性获取指定的annotation的注解对象
                    ConstantAnnotation ca = fd.getAnnotation(ConstantAnnotation.class);
                    // 注解对象空，其值为该field的值
                    keyValueMap.put(fd.getName(), fd.get(cls));
                    valueKeyMap.put(fd.get(cls), fd.getName());
                    if (ca != null) {
                        // 注解对象不空，其值为注解对象中的值
                        keyDescMap.put(fd.getName(), ca.value());
                        valueDescMap.put(fd.get(cls), ca.value());
                        descValueMap.put(ca.value(), fd.get(cls));
                        descKeyMap.put(ca.value(), fd.getName());
                    }
                }
                keyValueMapCons.put(cls.getSimpleName(), keyValueMap);
                keyDescMapCons.put(cls.getSimpleName(), keyDescMap);
                descValueMapCons.put(cls.getSimpleName(), descValueMap);
                descKeyMapCons.put(cls.getSimpleName(), descKeyMap);
                valueDescMapCons.put(cls.getSimpleName(), valueDescMap);
                valueKeyMapCons.put(cls.getSimpleName(), valueKeyMap);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * 入库单状态
     */
    public static final class INVIN_STATUS {
        @ConstantAnnotation("待入库")
        public static final Integer INIT = 0;
        @ConstantAnnotation("已入库")
        public static final Integer FINISH = 1;
    }

    /**
     * 入库类型
     */
    public static final class INVIN_TYPE {
        @ConstantAnnotation("采购入库")
        public static final Integer PURCHASE = 11;
        @ConstantAnnotation("其他入库")
        public static final Integer OTHER = 13;
        @ConstantAnnotation("盘盈入库")
        public static final Integer CHECK = 12;
    }

    /**
     * 出库单状态
     */
    public static final class INVOUT_STATUS {
        @ConstantAnnotation("待出库")
        public static final Integer INIT = 0;
        @ConstantAnnotation("已出库")
        public static final Integer FINISH = 1;
    }

    /**
     * 出库类型
     */
    public static final class INVOUT_TYPE {
        @ConstantAnnotation("领用出库")
        public static final Integer TAKE = 21;
        @ConstantAnnotation("其他出库")
        public static final Integer OTHER = 23;
        @ConstantAnnotation("盘亏出库")
        public static final Integer CHECK = 22;
        @ConstantAnnotation("报废出库")
        public static final Integer SCRAP = 24;
    }

    /**
     * 出入库
     */
    public static final class INV_IN_OR_OUT {
        @ConstantAnnotation("入库")
        public static final Integer IN = 1;
        @ConstantAnnotation("出库")
        public static final Integer OUT = 2;
    }

    /**
     * 库存单据类型
     */
    public static final class INV_ORDER_TYPE {
        @ConstantAnnotation("采购入库")
        public static final Integer PURCHASEIN = 11;
        @ConstantAnnotation("其他入库")
        public static final Integer OTHERIN = 13;
        @ConstantAnnotation("盘盈入库")
        public static final Integer CHECKIN = 12;
        @ConstantAnnotation("领用出库")
        public static final Integer TAKEOUT = 21;
        @ConstantAnnotation("其他出库")
        public static final Integer OTHEROUT = 23;
        @ConstantAnnotation("盘亏出库")
        public static final Integer CHECKOUT = 22;
        @ConstantAnnotation("报废出库")
        public static final Integer SCRAPOUT = 24;
    }

    /**
     * 盘点状态
     */
    public static class INVCHECK_STATUS {
        @ConstantAnnotation("待盘点")
        public static final Integer INIT = 0;
        @ConstantAnnotation("已盘点")
        public static final Integer FINISH = 1;
    }

}
