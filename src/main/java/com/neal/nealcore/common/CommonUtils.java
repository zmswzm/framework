package com.neal.nealcore.common;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.neal.nealcore.exception.beans.impl.SystemExceptionDesc;
import com.neal.nealcore.exception.impl.SystemException;
import com.neal.nealcore.exception.impl.internal.framework.FrameworkInternalException;

import java.io.StringWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CommonUtils {

    public static class JsonUtil {

        private static ObjectMapper mapper = new ObjectMapper();


        public static String object2Json(Object object) {
            try {
                StringWriter sw = new StringWriter();
                JsonGenerator gen = new JsonFactory().createJsonGenerator(sw);
                mapper.writeValue(gen, object);
                gen.close();
                return sw.toString();
            }catch (Exception e){
                throw new FrameworkInternalException(new SystemExceptionDesc(e));
            }
        }

    }

    public static class DateUtil{

        public enum DATE_PATTERN{
            /**
             * yyyyMMdd
             */
            yyyyMMdd("yyyyMMdd", "^\\d{2,4}\\d{1,2}\\d{1,2}$"),

            /**
             * yyyy/MM
             */
            yyyy_MM("yyyy/MM", "^\\d{2,4}/\\d{1,2}$"),

            /**
             * yyyy-MM
             */
            yyyy_MM2("yyyy-MM", "^\\d{2,4}-\\d{1,2}$"),

            /**
             * yyyy/MM/dd
             */
            yyyy_MM_dd("yyyy/MM/dd", "^\\d{2,4}/\\d{1,2}/\\d{1,2}$"),

            /**
             * yyyy-MM-dd
             */
            yyyy_MM_dd2("yyyy-MM-dd", "^\\d{2,4}-\\d{1,2}-\\d{1,2}$"),

            /**
             * yyyy/MM/dd HH:mm
             */
            yyyy_MM_dd_HH_mm("yyyy/MM/dd HH:mm", "^\\d{2,4}/\\d{1,2}/\\d{1,2}\\s.{1,2}\\d{1,2}:\\d{1,2}$"),

            /**
             * yyyy-MM-dd HH:mm
             */
            yyyy_MM_dd_HH_mm2("yyyy-MM-dd HH:mm", "^\\d{2,4}-\\d{1,2}-\\d{1,2}\\s.{1,2}\\d{1,2}:\\d{1,2}$"),

            /**
             * yyyy/MM/dd HH:mm:ss
             */
            yyyy_MM_dd_HH_mm_ss("yyyy/MM/dd HH:mm:ss", "^\\d{2,4}/\\d{1,2}/\\d{1,2}\\s.{1,2}\\d{1,2}:\\d{1,2}:\\d{1,2}$"),

            /**
             * yyyy-MM-dd HH:mm:ss
             */
            yyyy_MM_dd_HH_mm_ss2("yyyy-MM-dd HH:mm:ss", "^\\d{2,4}-\\d{1,2}-\\d{1,2}\\s.{1,2}\\d{1,2}:\\d{1,2}:\\d{1,2}$"),

            /**
             * yyyy/MM/dd HH:mm:ss.S
             */
            yyyy_MM_dd_HH_mm_ss_S("yyyy/MM/dd HH:mm:ss.S", "^\\d{2,4}/\\d{1,2}/\\d{1,2}\\s.{1,2}\\d{1,2}:\\d{1,2}:\\d{1,2}\\.\\d{1,}$"),

            /**
             * yyyy-MM-dd HH:mm:ss.S
             */
            yyyy_MM_dd_HH_mm_ss_S2("yyyy-MM-dd HH:mm:ss.S", "^\\d{2,4}-\\d{1,2}-\\d{1,2}\\s.{1,2}\\d{1,2}:\\d{1,2}:\\d{1,2}\\.\\d{1,}$");

            private String value;

            private String pattern;

            private DATE_PATTERN(String value,String pattern){
                this.value = value;
                this.pattern = pattern;
            }

            @Override
            public String toString() {
                return value;
            }

            public static DATE_PATTERN getPatternBySample(String date){

                if(null != date){
                    date = date.trim();

                    for (DATE_PATTERN value : DATE_PATTERN.values()){
                        if(date.matches(value.pattern)){
                            return value;
                        }
                    }
                }
                throw new FrameworkInternalException(new SystemExceptionDesc("日期为空或是不支持的样本格式：" + date));
            }
        }

        public static String formatData(Date date, DATE_PATTERN datePattern){

            DateFormat df = new SimpleDateFormat(datePattern.toString());
            return df.format(date);
        }

        public static  Date parseDate(String date){
            if(null == date){
                return null;
            }

            date = date.trim();
            if (-1 != date.indexOf("中国标准时间") || -1 != date.indexOf("CST")){
                return new Date(Date.parse(date));
            }
            return parseDate(date,getPatternBySample(date));
        }

        public static Date parseDate(String date,DATE_PATTERN pattern){

            try{
                DateFormat df = new SimpleDateFormat(pattern.toString());
                return df.parse(date);
            }catch (Exception e){
                throw new FrameworkInternalException(new SystemExceptionDesc(e));
            }
        }

        public static DATE_PATTERN getPatternBySample(String date){

            if(null != date){

                date = date.trim();

                for (DATE_PATTERN value : DATE_PATTERN.values()){

                    if (date.matches(value.pattern)){
                        return value;
                    }
                }
            }
            throw new FrameworkInternalException(new SystemExceptionDesc("日期为空或是不支持的样本格式：" + date));
        }

    }
}
