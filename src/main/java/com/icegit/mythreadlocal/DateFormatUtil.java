package com.icegit.mythreadlocal;

import java.text.SimpleDateFormat;

/**
 * ThreadLocal 解决simpledateformat线程不安全
 */
public class DateFormatUtil {
    private  static ThreadLocal<SimpleDateFormat> simpleDateFormatThreadLocal=new ThreadLocal<SimpleDateFormat>(){
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }
    };

    public static SimpleDateFormat getSimpleDateFormat(){
        return simpleDateFormatThreadLocal.get();
    }


    public static void main(String[] args) {
        SimpleDateFormat simpleDateFormat = DateFormatUtil.getSimpleDateFormat();
        SimpleDateFormat simpleDateFormat_ = DateFormatUtil.getSimpleDateFormat();
        System.out.println(simpleDateFormat);
        System.out.println(simpleDateFormat_);
    }

}