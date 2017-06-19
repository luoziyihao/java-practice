/*
 * ServerErrorCode.java
 * classes : com.sh.shvideo.task.ServerErrorCode
 * @author xiangyutian
 * V 4.5.0
 * Create at 2014-3-20 下午6:48:30
 */
package com.luozi.json.gson.http;


/**
 * 错误码集合
 *
 * @author xiangyutian <br/>
 *         create at 2014-3-20 下午6:48:30
 */
public class ServerErrorCode {
    public static final String TAG = "ServerErrorCode";

    /**
     * 返回成功
     */
    public static final int STATUS_SUCCESS = 0;

    /**
     * 返回失败
     */
    public static final int STATUS_EMPTY = 1;

    /**
     * token失效
     */
    public static final int STATUS_INVALID = 2;

    /**
     * token超时
     */
    public static final int STATUS_TIMEOUT = 6;
    /**
     *系统维护
     */
    public static final int STATUS_FIX= 7;
}
