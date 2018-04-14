package com.liaojun.component.base.util;

/**
 * Created by ChamIt-001 on 2017/11/24.
 */
public class PathUtil {

    public static String getDefaultVisitPath() { return PropConfigPool.getString("resource.default.visitPath"); }

    public static String getUploadSavePath() {
        return PropConfigPool.getString("resource.upload.savePath");
    }

    public static String getUploadVisitPath() {
        return PropConfigPool.getString("resource.upload.visitPath");
    }
}
