package com.luozi.log;

/**
 * Created by luoziyihao on 7/9/16.
 */
public class LogUtils {

    private static final Logger LOGGER = new Logger("common_info");

    public static Logger getLOGGER() {
        return LOGGER;
    }

    /**
     * ERROR级别主要用来记录没有预料到的异常，和业务处理中出现错误的返回值，会影响程序运行的异常错误
     * 需要开发人员注意的
     * 这个级别会打印所有的异常栈，不要记录一些常见的无关紧要的异常，比如网络断开，字符串数字转换错误等
     */
    public static void error(Object message) {
        LOGGER.error(message, null);
    }

    /**
     * ERROR级别主要用来记录没有预料到的异常，和业务处理中出现错误的返回值，会影响程序运行的异常错误
     * 需要开发人员注意的是
     * 此级别会打印所有的异常栈，不要记录一些常见的无关紧要的异常，比如网络断开，字符串数字转换错误等
     */
    public static void error(Throwable t, Object... params) {
        LOGGER.error(t, params);
    }

    /**
     * ERROR级别主要用来记录没有预料到的异常，和业务处理中出现错误的返回值，会影响程序运行的异常错误
     * 需要开发人员注意的是
     * 此级别会打印所有的异常栈，不要记录一些常见的无关紧要的异常，比如网络断开，字符串数字转换错误等
     */
    public static void error(Throwable t) {
        LOGGER.error(t);
    }

    /**
     * ERROR级别主要用来记录没有预料到的异常，和业务处理中出现错误的返回值，会影响程序运行的异常错误
     * 需要开发人员注意的是
     * 此级别会打印所有的异常栈，不要记录一些常见的无关紧要的异常，比如网络断开，字符串数字转换错误等
     */
    public static void error(Object message, Throwable t) {
        LOGGER.error(message, t);
    }

    /**
     * INFO一般来用记录跟踪业务流程，事务过程，关键参数或数据，提供story级别的描述，可以大致还原运行场景和流程
     * 发布环境下也一般开启INFO日志
     * 一般还会写一些成功，失败的
     * 此级别数据量不要太大，但是要很精炼
     */
    public static void info(Object message) {
        LOGGER.info(message);
    }

    /**
     * INFO一般来用记录跟踪业务流程，事务过程，关键参数或数据，提供story级别的描述，可以大致还原运行场景和流程
     * 发布环境下也一般开启INFO日志
     * 一般还会写一些成功，失败的
     * 此级别数据量不要太大，但是要很精炼
     */
    public static void info(Throwable t, Object... params) {
        LOGGER.info(t, params);
    }

    public static void info(Object message, Throwable t) {
        LOGGER.info(message, t);
    }

    /**
     * INFO一般来用记录跟踪业务流程，事务过程，关键参数或数据，提供story级别的描述，可以大致还原运行场景和流程
     * 发布环境下也一般开启INFO日志
     * 一般还会写一些成功，失败的
     * 此级别数据量不要太大，但是要很精炼
     */
    public static void info(Object... params) {
        LOGGER.info(params);
    }

    /**
     * INFO一般来用记录跟踪业务流程，事务过程，关键参数或数据，提供story级别的描述，可以大致还原运行场景和流程
     * 发布环境下也一般开启INFO日志
     * 一般还会写一些成功，失败的
     * 此级别数据量不要太大，但是要很精炼
     */
    public static void info(Throwable t) {
        LOGGER.info(t);
    }


    public static void warn(Object message) {
        LOGGER.warn(message);
    }

    public static void warn(Throwable t, Object... params) {
        LOGGER.warn(t, params);
    }

    public static void warn(Object message, Throwable t) {
        LOGGER.warn(message, t);
    }

    public static void warn(Throwable t) {
        LOGGER.warn(t);
    }

    /**
     * DEBUG级别一般来记录帮助调试的信息，输入的参数，变量，返回值，或者不太重要的异常
     * 主要用来清晰的还原运行时的场景
     */
    public static void debug(Object message) {
        LOGGER.debug(message);
    }

    /**
     * DEBUG级别一般来记录帮助调试的信息，输入的参数，变量，返回值，或者不太重要的异常
     * 主要用来清晰的还原运行时的场景
     */
    public static void debug(Throwable t) {
        LOGGER.debug(t);
    }

    /**
     * DEBUG级别一般来记录帮助调试的信息，输入的参数，变量，返回值，或者不太重要的异常
     * 主要用来清晰的还原运行时的场景
     */
    public static void debug(Object... params) {
        LOGGER.debug(params);
    }

    public static void debug(Throwable t, Object... params) {
        LOGGER.debug(t, params);
    }

    /**
     * DEBUG级别一般来记录帮助调试的信息，输入的参数，变量，返回值，或者不太重要的异常
     * 主要用来清晰的还原运行时的场景
     */
    public static void debug(Object message, Throwable t) {
        LOGGER.debug(message, t);
    }
}
