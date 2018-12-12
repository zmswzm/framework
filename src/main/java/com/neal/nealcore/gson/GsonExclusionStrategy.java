package com.neal.nealcore.gson;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;

/**
 * Created by suweui on 2018/10/27
 */
public class GsonExclusionStrategy implements ExclusionStrategy {

    public boolean shouldSkipField(FieldAttributes f) {
        return false;
    }

    public boolean shouldSkipClass(Class<?> clazz) {
        if (clazz == Throwable.class){
            return true;
        }
        return false;
    }
}
