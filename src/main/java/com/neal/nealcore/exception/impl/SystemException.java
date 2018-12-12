package com.neal.nealcore.exception.impl;

import com.neal.nealcore.exception.BaseException;
import com.neal.nealcore.exception.ExceptionType;
import com.neal.nealcore.exception.beans.impl.SystemExceptionDesc;
import com.neal.nealcore.exception.impl.internal.framework.FrameworkInternalException;

/**
* @author :suwei
* @date ：2016年2月2日 下午5:05:23
*
*/
public abstract class SystemException extends BaseException {

	public SystemException(SystemExceptionDesc desc){
		super(desc);
	}
	
	public SystemExceptionDesc getDesc(){
		return (SystemExceptionDesc) super.getDesc();
	}

	@Override
	public String defShortName() {
		return "系统异常";
	}

	@Override
	public ExceptionType defExceptionType() {
		return ExceptionType.SYSTEM_EXCEPTION;
	}

	public static SystemException convertFromNativeException(Exception e) {

		return new FrameworkInternalException(new SystemExceptionDesc(e));
	}
}
