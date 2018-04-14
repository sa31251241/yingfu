package com.liaojun.component.base.resource.model;

import com.liaojun.component.base.util.PathUtil;
import com.liaojun.component.base.util.StringUtil;
import org.apache.commons.io.FilenameUtils;

/**
 * Created by ChamIt-001 on 2017/12/29.
 */
public class ResourceFile {

    protected String relativeVisitPath;
    protected String fileName;
    protected String suffix;

    public ResourceFile(){}

    public ResourceFile(String relativeFileName){
        String path = FilenameUtils.getPath(relativeFileName);
        setRelativeVisitPath(path);
        setFileName(FilenameUtils.getName(relativeFileName));
    }

    public String getRelativeVisitPath() {
        return relativeVisitPath;
    }

    public void setRelativeVisitPath(String relativeVisitPath) {
        this.relativeVisitPath = relativeVisitPath;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
        this.suffix = FilenameUtils.getExtension(fileName);
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public String getFullVisitPathName(){
        return StringUtil.concat(PathUtil.getDefaultVisitPath(),this.relativeVisitPath,this.fileName);
    }
}
