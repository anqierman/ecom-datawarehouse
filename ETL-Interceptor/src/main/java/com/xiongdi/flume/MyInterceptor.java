package com.xiongdi.flume;

import com.xiongdi.utils.ETLUtil;
import org.apache.flume.Context;
import org.apache.flume.Event;
import org.apache.flume.interceptor.Interceptor;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 自定义flume拦截器
 */
public class MyInterceptor implements Interceptor {
    private  List<Event> results = new  ArrayList();
    private String startFlag ="\"en\":\"start\"";
    //初始化方法
    public void initialize() {

    }
    //拦截器处理方法
    //过滤掉不符合要求的数据,为每个Event的Header添加key
    public Event intercept(Event event) {
        byte[] body = event.getBody();
        Map<String, String> headers = event.getHeaders();
        String bodyStr = new String(body,Charset.forName("utf-8"));
        boolean flag = true;
        if(bodyStr.contains(startFlag)){
            flag = ETLUtil.validStartLog(bodyStr);
            headers.put("type","topic_start");
        }else{
            headers.put("type","topic_event");
            flag =ETLUtil.validEventLog(bodyStr);
        }
        if(flag == false){
            return  null;
        }
        return  event;
    }
    //拦截器处理方法：批处理
    public List<Event> intercept(List<Event> events) {
        results.clear();
        for (Event event : events) {
            Event intercept = intercept(event);
            if (intercept !=null){
               results.add(event);
            }
        }
        return results;
    }
    //关闭
    public void close() {

    }

    //额外提供一个内部内部的Builder，因为Flume在创建拦截器对象时，固定调用Builder来获取
    public static class Builder implements org.apache.flume.interceptor.Interceptor.Builder {

        //读取配置文件中的参数
        public void configure(Context context) {

        }
        //返回一个拦截器对象
        public Interceptor build() {
            return new MyInterceptor();
        }


    }
}



