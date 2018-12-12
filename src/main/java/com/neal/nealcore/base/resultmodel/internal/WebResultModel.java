package com.neal.nealcore.base.resultmodel.internal;

import com.neal.nealcore.base.resultmodel.ResultModel;
import com.neal.nealcore.common.CommonUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by suwei on 2017/5/23.
 */
public class WebResultModel extends ResultModel {

    private Boolean success;
    private String resultCode;
    private String time;
    private String message;
    private Object data;
    private String NORMAL_CODE = "0000000";

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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public WebResultModel(Object data, String message) {
        this.success = true;
        this.resultCode = NORMAL_CODE;
        this.time = CommonUtils.DateUtil.formatData(new Date(), CommonUtils.DateUtil.DATE_PATTERN.yyyy_MM_dd_HH_mm_ss);
        this.data = data;
        this.message = message;
    }

    public WebResultModel(){}

    @Override
    protected Object getObject() {

        Map<String,Object> map = new HashMap<String,Object>();
        map.put("success",success);
        map.put("resultCode",resultCode);
        map.put("time",time);
        map.put("message",message);
        if(success){
            map.put("data",data);
        }
        return map;
    }
}
