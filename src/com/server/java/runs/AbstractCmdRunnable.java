package com.server.java.runs;

import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;

import com.server.java.cache.ServerCache;
import com.server.java.entity.MsgEntity;
import com.server.java.log.LoggerProvider;
import com.server.java.queue.BaseQueue;

public abstract class AbstractCmdRunnable implements Runnable {
	private BaseQueue<MsgEntity> INSTANCE;
	private static Logger logger = LoggerProvider.getLogger(AbstractCmdRunnable.class);

	public AbstractCmdRunnable(BaseQueue<MsgEntity> INSTANCE) {
		this.INSTANCE = INSTANCE;
	}

	public void run() {
		for (;;) {
			try {
				MsgEntity msgEntity = null;
				while (INSTANCE.getQueueSize() > 0) {
					msgEntity = INSTANCE.take();
					if (msgEntity != null) {
						logger.info(ServerCache.getInstance().getId(msgEntity
								.getChannel())
								+ " recieve msg code = "
								+ msgEntity.getCmdCode()
								+ "-----ip------"
								+ msgEntity.getChannel().remoteAddress());
						handleMsg(msgEntity);
					}
				}
				TimeUnit.MILLISECONDS.sleep(50);// 如果已经取完则让给其他线程一些时间片
			} catch (InterruptedException e) {
				LoggerProvider.getLogger(AbstractCmdRunnable.class).error("", e);
			}
		}
	}

	public abstract void handleMsg(MsgEntity msgEntity);

}
