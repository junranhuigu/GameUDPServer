package com.server.test;

import java.net.DatagramSocket;
import java.net.InetSocketAddress;


public class TestClinet {
	
	
	public static void main(String[] args) throws Exception{
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					// UDP本地监听端口（如果为0将表示由系统分配，否则使用指定端口）
					DatagramSocket socket = new DatagramSocket(0);
					socket.connect(new InetSocketAddress("localhost", 10086));
					socket.setReuseAddress(true);
					
					while(true){
						// 接收数据报的包
						String toServer = "Hi，我是客户端，我的时间戳"+System.currentTimeMillis();
						byte[] bs = toServer.getBytes("UTF-8");
						java.net.DatagramPacket packet = new java.net.DatagramPacket(bs, bs.length);
						socket.send(packet);
						
						bs = new byte[1024];
						packet = new java.net.DatagramPacket(bs, bs.length);
						// 阻塞直到收到数据
						socket.receive(packet);
						 // 解析服务端发过来的数据
						String pFromServer = new String(packet.getData(), 0 , packet.getLength(), "UTF-8");
						System.out.println("【NOTE】>>>>>> 收到服务端的消息：" + pFromServer);
						Thread.sleep(200);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}).start();
	}
}
