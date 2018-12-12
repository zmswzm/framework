package com.neal.nealcore.exception.beans.impl;

import com.neal.nealcore.common.CommonUtils;
import com.neal.nealcore.exception.beans.BaseExceptionDes;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/5/23.
 */
public class RpcExceptionDesc extends BaseExceptionDes {

    private Boolean success;
    private String resultCode;
    private String time;
    private String message;

    public RpcExceptionDesc() {
    }

    public RpcExceptionDesc(String resultCode, String message) {
        super();
        this.success = false;
        this.resultCode = resultCode;
        this.time = CommonUtils.DateUtil.formatData(new Date(), CommonUtils.DateUtil.DATE_PATTERN.yyyy_MM_dd_HH_mm_ss);
        this.message = message;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public Map<String, Object> getSerializedData() {

        Map<String,Object> data = new HashMap<String,Object>();
        data.put("success",success);
        data.put("resultCode",resultCode);
        data.put("time",time);
        data.put("message",message);

        return data;
    }
}
