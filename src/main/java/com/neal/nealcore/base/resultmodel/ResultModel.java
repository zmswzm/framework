package com.neal.nealcore.base.resultmodel;

import com.neal.nealcore.common.CommonUtils;
import org.apache.log4j.Logger;

/**
 * Created by suwei on 2017/5/23.
 */
public abstract class ResultModel {

    protected Logger logger = Logger.getLogger(getClass());

    @Override
    public String toString() {

        Object obj = getObject();
        if (null != obj) {
            if (obj instanceof String) {
                return obj + "";
            }else {
                return CommonUtils.JsonUtil.object2Json(obj);
            }
        }
        return null;
    }

    protected abstract Object getObject();
}
