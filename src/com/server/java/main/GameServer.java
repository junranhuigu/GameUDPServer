package com.server.java.main;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import serverConfig.ServerConfig;

import com.hp.csv.GameDataReader;
import com.server.java.entity.provider.CsvDataLoadModule;
import com.server.java.log.LoggerProvider;
import com.server.java.netty.NettyServer;
import com.server.java.queue.GameMessageManager;
import com.server.java.queue.LoginQueue;
import com.server.java.queue.PlayerBaseQueue;
import com.server.java.runs.HandleCmdRunnable;

public class GameServer {
	private final static int port = ServerConfig.getInstance()
			.getClientServerPort();

	public static void main(String[] args) {
		// BasicConfigurator.configure();
		GameMessageManager.getInstance().init();
		// 初始化本地处理逻辑线程
		ExecutorService exec = Executors.newCachedThreadPool();
		exec.execute(new HandleCmdRunnable(LoginQueue.getInstance()));
		for(int i = Runtime.getRuntime().availableProcessors(); i > 0; -- i){
			exec.execute(new HandleCmdRunnable(PlayerBaseQueue.getInstance()));
		}
		LoggerProvider.getLogger(GameServer.class).info("当前处理逻辑线程数量：" + (Runtime.getRuntime().availableProcessors() + 1));

		// 加载csv数据
		String osInfo = System.getProperties().getProperty("os.name");
		if (osInfo.contains("Windows")) {
			GameDataReader.init(System.getProperty("user.dir") + "\\");
		}
		if (osInfo.contains("Linux")) {
			GameDataReader.init(System.getProperty("user.dir") + "/");
		}
		CsvDataLoadModule.loadAllCSVData();
		
		// 初始化客户端连接Netty UDP的数据长度控制在548字节以内
		exec.execute(new NettyServer(port));
		
		// 关服操作
		Runtime.getRuntime().addShutdownHook(new Thread() {
			@Override
			public void run() {
				LoggerProvider.getLogger(GameServer.class).info(
						"[关服]-JVM退出钩子程序执行结束");
			}
		});
	}
}
