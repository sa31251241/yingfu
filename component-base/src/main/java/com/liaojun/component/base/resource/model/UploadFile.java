package com.liaojun.component.base.resource.model;

import com.liaojun.component.base.util.PathUtil;
import com.liaojun.component.base.util.StringUtil;
import org.apache.commons.io.FilenameUtils;

/**
 * Created by ChamIt-001 on 2017/11/16.
 */
public class UploadFile extends ResourceFile {

    private String relativeSavePath;

    public UploadFile(){super();}

    public UploadFile(String relativeFileName){
        super(relativeFileName);
        String path = FilenameUtils.getPath(relativeFileName);
        setRelativeSavePath(path);
    }

    public String getRelativeSavePath() {
        return relativeSavePath;
    }

    public void setRelativeSavePath(String relativeSavePath) {
        this.relativeSavePath = relativeSavePath;
    }

    public String getFullSavePathName(){
        return StringUtil.concat(PathUtil.getUploadSavePath(),this.relativeSavePath,this.fileName);
    }

    @Override
    public String getFullVisitPathName() {
        return StringUtil.concat(PathUtil.getUploadVisitPath(),this.relativeVisitPath,this.fileName);
    }
}
