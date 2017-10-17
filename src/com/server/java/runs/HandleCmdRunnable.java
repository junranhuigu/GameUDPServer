package com.server.java.runs;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;

import com.server.java.CmdHandler;
import com.server.java.cache.ServerCache;
import com.server.java.entity.MsgEntity;
import com.server.java.log.LoggerProvider;
import com.server.java.queue.BaseQueue;
import com.server.java.queue.GameMessageManager;

public class HandleCmdRunnable extends AbstractCmdRunnable {
	private static Logger logger = LoggerProvider.getLogger(HandleCmdRunnable.class);
	public static BaseQueue<MsgEntity> AutoMsgQueue = new BaseQueue<MsgEntity>();
	private ConcurrentHashMap<String, CmdHandler> CmdHandlerMap = new ConcurrentHashMap<String, CmdHandler>();

	public HandleCmdRunnable(BaseQueue<MsgEntity> queue) {
		super(queue);
		for(Class<? extends CmdHandler> cls : GameMessageManager.getInstance().getUsedHandlersClass()){
			try {
				CmdHandlerMap.put(cls.getName(), cls.newInstance());
			} catch (Exception e) {
				logger.error("初始化消息处理类" + cls.getName() + "失败", e);
			}
		}
	}

	@Override
	public void handleMsg(MsgEntity msgEntity) {
		List<MsgEntity> commandList = new ArrayList<MsgEntity>();// 用于存放处理后同时发送的几条消息
		CmdHandler handler = CmdHandlerMap.get(GameMessageManager.getInstance().getHandlerKey(msgEntity.getCmdCode()));
		if(handler != null){
			try {
				handler.handleMsg(msgEntity, commandList);// 处理消息
				if (commandList != null && commandList.size() > 0) {
					for (MsgEntity tempMessage : commandList) {
						StringBuilder builder = new StringBuilder(
								"handle send msg code = ");
						builder.append(tempMessage.getCmdCode())
						.append(" to ").append(ServerCache.getInstance().getId(msgEntity.getChannel()))
						.append(", use time : ")
						.append(System.currentTimeMillis()
								- msgEntity.getReceiveTime())
								.append(" ms");
						logger.info(builder.toString());
						tempMessage.getChannel().writeAndFlush(tempMessage);// 发送消息
					}
				}
				commandList.clear();
			} catch (Exception e) {
				logger.error("处理来自" + msgEntity.getChannel().remoteAddress() + "的协议" + msgEntity.getCmdCode() + "出错", e);
			}
		} else {
			logger.error("收到" + msgEntity.getChannel().remoteAddress() + "发送协议" + msgEntity.getCmdCode() + "异常");
		}

		try {
			while (AutoMsgQueue.getQueueSize() > 0) {
				msgEntity = AutoMsgQueue.take();
				if (msgEntity != null) {
					if (msgEntity.getChannel() == null
							|| !msgEntity.getChannel().isWritable()) {
						logger.info("auto send msg code miss----------miss-----------miss----= "
								+ msgEntity.getCmdCode());
					} else {
						logger.info("auto send msg code = "
								+ msgEntity.getCmdCode() + " to " + ServerCache.getInstance().getId(msgEntity.getChannel()));
						msgEntity.getChannel().writeAndFlush(msgEntity);// 发送消息
					}
				}
			}
		} catch (Exception e) {
			logger.error("向" + msgEntity.getChannel().remoteAddress() + "发送Auto协议" + msgEntity.getCmdCode() + "出错", e);
		}
	}
}
