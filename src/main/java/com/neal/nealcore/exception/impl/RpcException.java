package com.neal.nealcore.exception.impl;

import com.neal.nealcore.exception.BaseException;
import com.neal.nealcore.exception.ExceptionType;
import com.neal.nealcore.exception.beans.BaseExceptionDes;
import com.neal.nealcore.exception.beans.impl.RpcExceptionDesc;

/**
 * Created by suwei on 2018/10/27
 */
public class RpcException extends BaseException {

    public RpcException(BaseExceptionDes desc) {
        super(desc);
    }

    @Override
    public RpcExceptionDesc getDesc() {
        return (RpcExceptionDesc) super.getDesc();
    }

    @Override
    public String defShortName() {
        return "协议异常";
    }

    @Override
    public ExceptionType defExceptionType() {
        return ExceptionType.RPC_EXCEPTION;
    }
}
