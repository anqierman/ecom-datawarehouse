package com.xiongdi.utils;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;

/**
 * 从工具类用来检查数据格式
 */

public class ETLUtil {
    public static boolean validStartLog(String source) {
        if(StringUtils.isBlank(source)){
            return  false;
        }
        String trimStr = source.trim();
        if(trimStr.startsWith("{")&&trimStr.endsWith("}")){
            return  true;
        }
        return false;
    }

    public static boolean validEventLog(String source) {
        if(StringUtils.isBlank(source)){
            return  false;
        }
        String trimStr = source.trim();
        String[] words = trimStr.split("\\|");

        if(words.length!=2){
            return  false;
        }
        if(words[0].length()!=13 || !NumberUtils.isDigits(words[0])){
            return false;
        }
        if(words[1].startsWith("{") && words[1].endsWith("}")){
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        //事件日志
        String str = "1600499051437|{\"cm\":{\"ln\":\"-86.4\",\"sv\":\"V2.6.3\",\"os\":\"8.0.8\",\"g\":\"SM8O0813@gmail.com\",\"mid\":\"47\",\"nw\":\"3G\",\"l\":\"pt\",\"vc\":\"1\n" +
                "0\",\"hw\":\"640*960\",\"ar\":\"MX\",\"uid\":\"47\",\"t\":\"1600443099281\",\"la\":\"0.6000000000000001\",\"md\":\"HTC-16\",\"vn\":\"1.1.2\",\"ba\":\"HTC\",\n" +
                "\"sr\":\"I\"},\"ap\":\"app\",\"et\":[{\"ett\":\"1600481456026\",\"en\":\"display\",\"kv\":{\"goodsid\":\"19\",\"action\":\"1\",\"extend1\":\"1\",\"place\":\"5\n" +
                "\",\"category\":\"81\"}},{\"ett\":\"1600491573548\",\"en\":\"newsdetail\",\"kv\":{\"entry\":\"3\",\"goodsid\":\"20\",\"news_staytime\":\"16\",\"loading\n" +
                "_time\":\"45\",\"action\":\"4\",\"showtype\":\"5\",\"category\":\"7\",\"type1\":\"\"}},{\"ett\":\"1600408298405\",\"en\":\"ad\",\"kv\":{\"entry\":\"1\",\"sho\n" +
                "w_style\":\"3\",\"action\":\"5\",\"detail\":\"\",\"source\":\"2\",\"behavior\":\"2\",\"content\":\"1\",\"newstype\":\"8\"}},{\"ett\":\"1600428739409\",\"en\n" +
                "\":\"praise\",\"kv\":{\"target_id\":5,\"id\":4,\"type\":3,\"add_time\":\"1600438489080\",\"userid\":6}}]}";
        //启动日志
        String str2="{\"action\":\"1\",\"ar\":\"MX\",\"ba\":\"HTC\",\"detail\":\"102\",\"en\":\"start\",\"entry\":\"2\",\"extend1\":\"\",\"g\":\"808M44P3@gmail.com\",\"hw\":\"640*\n" +
                "1136\",\"l\":\"en\",\"la\":\"-36.7\",\"ln\":\"-55.4\",\"loading_time\":\"10\",\"md\":\"HTC-16\",\"mid\":\"48\",\"nw\":\"3G\",\"open_ad_type\":\"1\",\"os\":\"8.\n" +
                "0.2\",\"sr\":\"D\",\"sv\":\"V2.9.0\",\"t\":\"1600415765727\",\"uid\":\"48\",\"vc\":\"8\",\"vn\":\"1.1.9\"}";
        System.out.println(validEventLog(str));//事件日志
        System.out.println(validStartLog(str2));//启动日志
    }
}
