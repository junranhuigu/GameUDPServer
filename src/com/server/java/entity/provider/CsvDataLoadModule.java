package com.server.java.entity.provider;

import com.server.java.log.LoggerProvider;

/**
 * 加载所有csv表的数据
 * */
public class CsvDataLoadModule {

	public static void loadAllCSVData() {
		//错误号
		FailMsgDataProvider.getInstance().init();
		LoggerProvider.getLogger(CsvDataLoadModule.class).info("游戏数据加载完毕");
	}
}
