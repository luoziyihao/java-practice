package com.luozi.common.spring;

import com.google.gson.GsonBuilder;
import org.springframework.http.converter.json.GsonHttpMessageConverter;

/**
 * Created by luoziyihao on 10/13/16.
 */
public class CommonGsonHttpMessageConverter extends GsonHttpMessageConverter {


    public CommonGsonHttpMessageConverter(){
        super();
        //更换Gson转换器
        super.setGson(new GsonBuilder()
                .serializeNulls()		//null值属性也需要序列化
//                .setDateFormat("yyyy-MM-dd HH:mm:ss") //设置日期转换
                .create());
    }

}
