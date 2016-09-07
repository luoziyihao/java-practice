/*
 * IResultParser.java
 * @author xiangyutian
 * V 4.5.0
 * Create at 2014-3-20 下午5:58:32
 */
package com.luozi.json.gson.http.parser;

import com.alibaba.fastjson.JSONException;
import com.luozi.json.gson.http.exception.HttpClientApiException;

import java.io.IOException;



/**
 * 解析器基类
 * 
 * @author xiangyutian <br/>
 *         create at 2014-3-20 下午5:58:32
 */
public interface IResultDataParser<T> {

    /**
     * 解析服务器响应
     * 
     * @return T 解析response后得到的数据类型
     * @throws JSONException 解析服务器响应的JSON
     * @throws RemoteException 服务器返回的错误代码
     * @throws IOException 获取服务器响应异常
     */
    T parse(Object response) throws JSONException, HttpClientApiException, IOException;
}
