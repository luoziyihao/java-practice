/*
 * JsonParserFactory.java
 * @author xiangyutian
 * V 4.5.0
 * Create at 2014-3-21 下午6:14:59
 */
package com.luozi.json.gson.http.parser;

import com.alibaba.fastjson.JSONException;
import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSyntaxException;
import com.luozi.json.domain.AbstractBaseModel;
import com.luozi.json.gson.http.ServerErrorCode;
import com.luozi.json.gson.http.exception.HttpClientApiException;

import java.io.IOException;
import java.lang.reflect.Type;

/**
 * 数据解析器工厂
 *
 * @author xiangyutian <br/>
 *         create at 2014-3-21 下午6:14:59
 */
public class JsonParserFactory {
    /**
     * TAG
     */
    private static final String TAG = "JsonParserFactory";

    public static <T extends AbstractBaseModel> T parseStringJson(Class<T> cls, Object context) throws JSONException,
            HttpClientApiException, IOException {
        final T response;
        try {
            response = new Gson().fromJson((String) context, cls);
        } catch (JsonSyntaxException e) {
            throw new JSONException(e.getMessage());
        } catch (JsonIOException e) {
            throw new IOException(e.getMessage());
        } catch (JsonParseException e) {
            throw new JSONException(e.getMessage());
        } catch (IllegalArgumentException e) {
            throw new JSONException(e.getMessage());
        }

        if (response == null) {
            throw new JSONException(TAG + " JsonParser is null.");
        }

        if (response.getCode() != ServerErrorCode.STATUS_SUCCESS) {
            throw new HttpClientApiException(response.getCode(), response.getMsg());
        }

        return response;
    }


    public static <T extends AbstractBaseModel> T parseStringJson(Type type, Object context) throws JSONException,
            HttpClientApiException, IOException {
        final T response;
        try {
            response = new Gson().fromJson((String) context, type);
        } catch (JsonSyntaxException e) {
            throw new JSONException(e.getMessage());
        } catch (JsonIOException e) {
            throw new IOException(e.getMessage());
        } catch (JsonParseException e) {
            throw new JSONException(e.getMessage());
        } catch (IllegalArgumentException e) {
            throw new JSONException(e.getMessage());
        }

        if (response == null) {
            throw new JSONException(TAG + " JsonParser is null.");
        }

        if (response.getCode() != ServerErrorCode.STATUS_SUCCESS) {
            throw new HttpClientApiException(response.getCode(), response.getMsg());
        }

        return response;
    }

    /**
     * 解析基本数据类型
     *
     * @param cls
     * @return
     */
    public static <T extends AbstractBaseModel> IResultDataParser<T> parseBaseModel(final Class<T> cls) {

        return new IResultDataParser<T>() {
            @Override
            public T parse(Object response) throws JSONException, HttpClientApiException, IOException {
                return parseStringJson(cls, (String) response);
            }
        };
    }

    /**
     * 解析基本数据类型
     *
     * @param type
     * @return
     */
    public static <T extends AbstractBaseModel> IResultDataParser<T> parseBaseModel(final Type type) {

        return new IResultDataParser<T>() {
            @Override
            public T parse(Object response) throws JSONException, HttpClientApiException, IOException {
                return parseStringJson(type, (String) response);
            }
        };
    }

}
