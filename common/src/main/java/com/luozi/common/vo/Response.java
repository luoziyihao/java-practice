package com.luozi.common.vo;

/**
 * 响应对象
 *
 * @author huangyong
 * @since 1.0.0
 */
public class Response {

    private static final String OK = "ok";
    private static final String ERROR = "error";

    private static final int CODE_OK = 0;
    private static final int CODE_FAIL = 1;

    private Meta meta;
    private DevMeta devMeta;
    private Object data;

    public Response success() {
        this.meta = new Meta(true, OK);
        this.devMeta = new DevMeta(CODE_OK, OK);
        return this;
    }

    public Response success(Object data) {
        this.meta = new Meta(true, OK);
        this.devMeta = new DevMeta(CODE_OK, OK);
        this.data = data;
        return this;
    }

    /* 必须指定错误有异常码 异常码种类 > 异常消息种类 */
    public Response failure() {
        this.meta = new Meta(false, ERROR);
        this.devMeta = new DevMeta(CODE_FAIL, ERROR);
        return this;
    }

    public Response failure(String message) {
        this.meta = new Meta(false, message);
        this.devMeta = new DevMeta(CODE_FAIL, message);
        return this;
    }

    public Response failure(int code, String message, String devMsg) {
        this.meta = new Meta(false, message);
        this.devMeta = new DevMeta(code, devMsg);
        return this;
    }

    /* 必须指定错误有异常码 异常码种类 > 异常消息种类 */
    public Response failure(int code, String message) {
        this.meta = new Meta(false, message);
        this.devMeta = new DevMeta(code, message);
        return this;
    }

    public Meta getMeta() {
        return meta;
    }

    public Object getData() {
        return data;
    }

    public class Meta {

        private boolean success;
        private String message;

        public Meta(boolean success) {
            this.success = success;
        }

        public Meta(boolean success, String message) {
            this.success = success;
            this.message = message;
        }

        public boolean isSuccess() {
            return success;
        }

        public String getMessage() {
            return message;
        }
    }

    public class DevMeta {

        private int code;
        private String message;

        public DevMeta(int code, String message) {
            this.code = code;
            this.message = message;
        }

        public String getMessage() {
            return message;
        }
    }
}
