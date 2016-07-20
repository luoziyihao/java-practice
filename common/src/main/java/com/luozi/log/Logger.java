package com.luozi.log;


import java.io.File;

import org.apache.log4j.PropertyConfigurator;


public class Logger {
	
	private org.apache.log4j.Logger logger;

	private Logger(Class<?> clazz) {
			logger = org.apache.log4j.Logger.getLogger(clazz);
	}
	
	private Logger(org.apache.log4j.Logger _logger)
	{
		logger = _logger;
	}
	
	public static void initialize()  {
		
	}

	public static Logger getLoggerInstance(Class<?> clazz) {
		return new Logger(clazz);
	}
	
	public static Logger getLoggerInstance(org.apache.log4j.Logger _logger){
		return new Logger(_logger);
	}
	
	
	/**
	 * 指定log4j加载配置文件的路径
	 * @param relativepath
	 * @return
	 */
	public static boolean SetPropertiesPath(String relativepath){
		String abpath =System.getProperty("user.dir")+File.separatorChar + relativepath;
		if(! new File(abpath).isFile()){
			return false;
		}
		
        PropertyConfigurator.configure(abpath); 
        //定期刷新
        PropertyConfigurator.configureAndWatch(abpath,5000);
        return true;
	}

	private static String ParamsToStr(Object... params){
		StringBuffer sb = new StringBuffer(128);
		for(Object param : params)
		{
			sb.append(null != param ?param:"null").append(" | ");
		}
		return sb.toString();
	}
	
	/**
	 * ERROR级别主要用来记录没有预料到的异常，和业务处理中出现错误的返回值，会影响程序运行的异常错误
	 * 需要开发人员注意的
	 * 这个级别会打印所有的异常栈，不要记录一些常见的无关紧要的异常，比如网络断开，字符串数字转换错误等
	 */
	public void error(Object message) {
		logger.error(message,null);
	}
	
	/**
	 * ERROR级别主要用来记录没有预料到的异常，和业务处理中出现错误的返回值，会影响程序运行的异常错误
	 * 需要开发人员注意的是
	 * 此级别会打印所有的异常栈，不要记录一些常见的无关紧要的异常，比如网络断开，字符串数字转换错误等
	 */
	public void error(Throwable t,Object... params) {
		this.error(ParamsToStr(params),t);
	}

	/**
	 * ERROR级别主要用来记录没有预料到的异常，和业务处理中出现错误的返回值，会影响程序运行的异常错误
	 * 需要开发人员注意的是
	 * 此级别会打印所有的异常栈，不要记录一些常见的无关紧要的异常，比如网络断开，字符串数字转换错误等
	 */
	public void error(Throwable t) {
		logger.error(t);
	}
	/**
	 * ERROR级别主要用来记录没有预料到的异常，和业务处理中出现错误的返回值，会影响程序运行的异常错误
	 * 需要开发人员注意的是
	 * 此级别会打印所有的异常栈，不要记录一些常见的无关紧要的异常，比如网络断开，字符串数字转换错误等
	 */
	public void error(Object message,Throwable t){
		logger.error(message, t);
	}
	
	/**
	 *  INFO一般来用记录跟踪业务流程，事务过程，关键参数或数据，提供story级别的描述，可以大致还原运行场景和流程
	 *  发布环境下也一般开启INFO日志
	 *  一般还会写一些成功，失败的
	 *  此级别数据量不要太大，但是要很精炼
	 */
	public void info(Object message) {
		this.info(message.toString(),null);
	}
	
	/**
	 *  INFO一般来用记录跟踪业务流程，事务过程，关键参数或数据，提供story级别的描述，可以大致还原运行场景和流程
	 *  发布环境下也一般开启INFO日志
	 *  一般还会写一些成功，失败的
	 *  此级别数据量不要太大，但是要很精炼
	 */
	public void info(Throwable t,Object... params) {
		this.info(ParamsToStr(params),t);
	}
	
	/**
	 *  INFO一般来用记录跟踪业务流程，事务过程，关键参数或数据，提供story级别的描述，可以大致还原运行场景和流程
	 *  发布环境下也一般开启INFO日志
	 *  一般还会写一些成功，失败的
	 *  此级别数据量不要太大，但是要很精炼
	 */
	public void info(Object... params) {
		this.info(ParamsToStr(params));
	}

	/**
	 *  INFO一般来用记录跟踪业务流程，事务过程，关键参数或数据，提供story级别的描述，可以大致还原运行场景和流程
	 *  发布环境下也一般开启INFO日志，所以期望的记录要在这个级别
	 *  一般还会写一些成功，失败的
	 *  此级别数据量不要太大，但是要很精炼，具有概要性
	 */
	public void info(Object message,Throwable t) {
		if(logger.isInfoEnabled())
			logger.info(message, t);
	}
	
	/**
	 *  INFO一般来用记录跟踪业务流程，事务过程，关键参数或数据，提供story级别的描述，可以大致还原运行场景和流程
	 *  发布环境下也一般开启INFO日志
	 *  一般还会写一些成功，失败的
	 *  此级别数据量不要太大，但是要很精炼
	 */
	public void info(Throwable t) {
			this.info("", t);
	}
	

	public void warn(Object message) {
		this.warn(message,null);
	}
	
	public void warn(Throwable t,Object... params) {
		this.warn(ParamsToStr(params),t);
	}
	
	public void warn(Object message,Throwable t) {
		logger.warn(message, t);
	}
	
	public void warn(Throwable t) {
		this.warn("", t);
	} 
	
	final org.apache.log4j.Logger getLog(){
		return logger;
	}
	
	/**
	 * DEBUG级别一般来记录帮助调试的信息，输入的参数，变量，返回值，或者不太重要的异常
	 * 主要用来清晰的还原运行时的场景
	 */
	public void debug(Object message) {
		this.debug(message.toString(),null);
	}
	
	/**
	 * DEBUG级别一般来记录帮助调试的信息，输入的参数，变量，返回值，或者不太重要的异常
	 * 主要用来清晰的还原运行时的场景
	 */
	public void debug(Throwable t,Object... params) {
		if(logger.isDebugEnabled()){
			this.debug(ParamsToStr(params),t);
		}
	}
	
	/**
	 * DEBUG级别一般来记录帮助调试的信息，输入的参数，变量，返回值，或者不太重要的异常
	 * 主要用来清晰的还原运行时的场景
	 */
	public void debug(Object message,Throwable t) {
		if(logger.isDebugEnabled())
			logger.debug(message, t);
	}
	
	/**
	 * DEBUG级别一般来记录帮助调试的信息，输入的参数，变量，返回值，或者不太重要的异常
	 * 主要用来清晰的还原运行时的场景
	 */
	public void debug(Throwable t) {
			this.debug("", t);
	}
	
	/**
	 * DEBUG级别一般来记录帮助调试的信息，输入的参数，变量，返回值，或者不太重要的异常
	 * 主要用来清晰的还原运行时的场景
	 */
	public void debug(Object... params) {
		this.debug(null,params);
	}

}
