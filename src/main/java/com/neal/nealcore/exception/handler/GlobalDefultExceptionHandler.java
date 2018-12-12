package com.neal.nealcore.exception.handler;

import com.neal.nealcore.exception.beans.BaseExceptionDes;
import com.neal.nealcore.exception.beans.impl.RpcExceptionDesc;
import com.neal.nealcore.exception.beans.impl.SystemExceptionDesc;
import com.neal.nealcore.exception.impl.RpcException;
import com.neal.nealcore.exception.impl.SystemException;
import com.neal.nealcore.exception.wrapper.IExceptionWrapper;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalDefultExceptionHandler {

    private IExceptionWrapper exceptionWrapper;

    @PostConstruct
    private void init(){

        String className = "com.neal.nealcore.exception.wrapper.impl.internal.DefaultExceptionWrapper";
        try {
            //Todo add property
            exceptionWrapper = (IExceptionWrapper)Class.forName(className).newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //声明要捕获的异常
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public String defultExcepitonHandler(HttpServletRequest request, Exception e) {

        return processException(request, e);
    }

    private String processException(HttpServletRequest request, Exception ex){

        BaseExceptionDes targetDesc = null;

        if (ex instanceof SystemException){
            SystemException exception = (SystemException)ex;
            SystemExceptionDesc desc = exception.getDesc();
            targetDesc = desc;
        }else if(ex instanceof RpcException){

            //协议异常
            RpcException exception = (RpcException)ex;
            RpcExceptionDesc desc = exception.getDesc();
            //对于非系统异常，由于不存在原始异常，因此直接设置 e
            desc.setThrowable(ex);
            targetDesc = desc;

        }else{

            //原始异常
            SystemException exception = SystemException.convertFromNativeException(ex);
            SystemExceptionDesc desc = exception.getDesc();
            targetDesc = desc;
        }

        return exceptionWrapper.getWrappedResultAsJson(targetDesc);
    }

}