package com.server.java.queue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import com.server.java.log.LoggerProvider;


public class BaseQueue<T> {

	
	private final BlockingQueue<T> queue = new LinkedBlockingQueue<T>();

	
	public T take() {
		return queue.poll();
	}

	
	public void put(T t) {
		try {
			queue.put(t);
		} catch (InterruptedException e) {
			LoggerProvider.getLogger(BaseQueue.class).error("添加新消息失败", e);
		}
	}

	public int getQueueSize() {
		return queue.size();
	}

}
