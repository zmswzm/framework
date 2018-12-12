package com.neal.nealcore.exception.wrapper.impl;

import java.util.Map;

/**
 * Created by suwei on 2017/5/24.
 */
public abstract class ExceptionWrapperAdapter extends BaseExceptionWrapper{

    public Object wrapSystemException(Map<String, Object> serializedData) {
        return serializedData;
    }

    public Object wrapRpcException(Map<String, Object> serializedData) {
        return serializedData;
    }
}
