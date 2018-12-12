package com.neal.nealcore.exception;


import com.neal.nealcore.exception.beans.BaseExceptionDes;


public abstract class BaseException extends RuntimeException{

	private BaseExceptionDes desc;
	
	public BaseException(BaseExceptionDes desc){
		this.setDesc(desc);
	}

	public BaseExceptionDes getDesc() {
		return desc;
	}

	public void setDesc(BaseExceptionDes desc) {
		this.desc = desc;
	}

	/**
	 * 定义简称
	 * @return
	 */
	public abstract String defShortName();

	/**
	 * 定义异常类型
	 * @return
	 */
	public abstract ExceptionType defExceptionType();

	public String getMessage(){
		return this.desc.toJson();
	}
}
