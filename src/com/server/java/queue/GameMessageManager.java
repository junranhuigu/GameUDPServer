package com.server.java.queue;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.HashSet;

import org.slf4j.Logger;

import com.server.java.CmdHandler;
import com.server.java.constants.CmdConstant;
import com.server.java.log.LoggerProvider;

/**
 * 协议id对应类
 * */
public class GameMessageManager {
	private static Logger logger = LoggerProvider.getLogger(GameMessageManager.class);
	private static GameMessageManager instance;
	private HashMap<String, Short> matchs;//处理方法与协议id的对应
	private HashMap<Short, String> handlerKeys;//处理方法与协议id的对应
	
	private GameMessageManager() {
		matchs = new HashMap<>();
		handlerKeys = new HashMap<>();
	}

	public static GameMessageManager getInstance() {
		if(instance == null){
			instance = new GameMessageManager();
		}
		return instance;
	}
	
	public void init() {
		CmdConstant cmd = new CmdConstant();
		for(Field field : CmdConstant.class.getFields()){
			GameMsgConfig config = field.getAnnotation(GameMsgConfig.class);
			if(config != null){
				String key = config.handleClass().getName() + "." + config.handleMethod();
				try {
					short code = field.getShort(cmd);
					matchs.put(key, code);
					handlerKeys.put(code, config.handleClass().getName());
				} catch (Exception e) {
					logger.error("与客户端交互数据 " + field.getName() + "初始化失败", e);
				}
			}
		}
		logger.info("与客户端交互数据 初始化完毕， 共计 ： " + matchs.size());
	}
	
	private short getCode(String className, String methodName){
		Short result = matchs.get(className + "." + methodName);
		if(result == null){
			return -1;
		}
		return result;
	}
	
	/**
	 * 获取当前处理逻辑对应的消息协议号
	 * */
	public short getCode(){
		short code = -1;
		for(StackTraceElement element : Thread.currentThread().getStackTrace()){
			code = GameMessageManager.getInstance().getCode(element.getClassName(), element.getMethodName());
			if(code > 0){
				break;
			}
		}
		return code;
	}
	
	/**
	 * 获取消息协议号对应的处理类代号
	 * */
	public String getHandlerKey(short code){
		return handlerKeys.get(code);
	}
	
	/**
	 * 获取需要加载的协议类
	 * */
	public HashSet<Class<? extends CmdHandler>> getUsedHandlersClass(){
		HashSet<Class<? extends CmdHandler>> set = new HashSet<>();
		for(Field field : CmdConstant.class.getFields()){
			GameMsgConfig config = field.getAnnotation(GameMsgConfig.class);
			if(config != null){
				Class<?> cls = config.handleClass();
				if(cls != null && CmdHandler.class.isAssignableFrom(cls)){
					set.add(config.handleClass());
				}
			}
		}
		return set;
	}
}
