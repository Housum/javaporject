package com.hotusm.concurrent;

import java.util.Date;

/**
 * Created by luqibao on 2016/12/30.
 */
public class Logger {

    public static void info(String msg){
        System.out.println("INFO:  ts: "+new Date().getTime()/1000+"  msg:"+msg);
    }

    public static void error(String msg){
        System.err.println("ERROR: ts: "+new Date().getTime()/1000+"  msg:"+msg);
    }

}
