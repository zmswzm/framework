package com.neal.nealcore.exception.wrapper.impl;

import com.neal.nealcore.common.CommonUtils;
import com.neal.nealcore.exception.beans.BaseExceptionDes;
import com.neal.nealcore.exception.beans.impl.RpcExceptionDesc;
import com.neal.nealcore.exception.beans.impl.SystemExceptionDesc;
import com.neal.nealcore.exception.wrapper.IExceptionWrapper;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by suwei on 2017/5/24.
 */
public abstract class BaseExceptionWrapper implements IExceptionWrapper {


    protected Properties properties;

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    public Object wrapUnexpectedException(BaseExceptionDes desc) {

        Map<String,Object> data = new HashMap<String,Object>();
        data.put("success",false);
        data.put("message","未知的异常类型");
        return data;
    }

    public Object getWrappedResult(BaseExceptionDes des) {

        if(des instanceof SystemExceptionDesc){
            return wrapSystemException(des.getSerializedData());
        }
        if (des instanceof RpcExceptionDesc){
            return wrapRpcException(des.getSerializedData());
        }
        return wrapUnexpectedException(des);
    }

    public String getWrappedResultAsJson(BaseExceptionDes des) {
        return CommonUtils.JsonUtil.object2Json(getWrappedResult(des));
    }
}
