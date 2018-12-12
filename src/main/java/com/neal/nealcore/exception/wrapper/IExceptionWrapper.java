package com.neal.nealcore.exception.wrapper;

import com.neal.nealcore.exception.beans.BaseExceptionDes;

import java.util.Map;

/**
 * Created by suwei on 2017/5/24.
 */
public interface IExceptionWrapper {

    /**
     * 包装系统异常
     * @param serializedData
     * @return
     */
    Object wrapSystemException(Map<String, Object> serializedData);

    /**
     * 包装协议异常
     * @param serializedData
     * @return
     */
    Object wrapRpcException(Map<String, Object> serializedData);

    /**
     * 包装未知异常
     * @param desc
     * @return
     */
    Object wrapUnexpectedException(BaseExceptionDes desc);

    /**
     * 获取包装结果
     * @param des
     * @return
     */
    Object getWrappedResult(BaseExceptionDes des);

    /**
     * 获取包装结果
     * @param des
     * @return
     */
    String getWrappedResultAsJson(BaseExceptionDes des);
}
