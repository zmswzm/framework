package com.neal.nealcore.exception.impl.internal.framework;

import com.neal.nealcore.exception.beans.impl.SystemExceptionDesc;
import com.neal.nealcore.exception.impl.SystemException;

/**
* @author :suwei
* @date ：2016年2月2日 下午5:30:57
* 类说明
*/
public class FrameworkInternalException extends SystemException {

	public FrameworkInternalException(SystemExceptionDesc desc){
		super(desc);
	}
}
