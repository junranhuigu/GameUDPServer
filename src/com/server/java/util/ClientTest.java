package com.server.java.util;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.MessageToByteEncoder;

import java.util.List;

import com.google.protobuf.GeneratedMessage;
import com.server.java.entity.MsgEntity;

public class ClientTest {
	private EventLoopGroup workerGroup;
	
	public static void main(String[] args) throws Exception{
//		MitamaComposeReq11200.Builder req = MitamaComposeReq11200.newBuilder();
//		req.setId("10086");
//		short code = 11200;
//		
//		new ClientTest().connect("192.168.1.250", ServerConfig.getInstance().getClientServerPort(), req.build(), code);
		
//		ClientTest test = new ClientTest();
//		for(int i = 0; i <= 100000; ++ i){
//			test.connect("localhost", 9092, req.build(), code);
//		}
//		test.close();
	}
	
	public void connect(String host, int port, GeneratedMessage req, short code) throws Exception {  
		close();
        workerGroup = new NioEventLoopGroup();  
        try {  
            Bootstrap b = new Bootstrap();  
            b.group(workerGroup);  
            b.channel(NioSocketChannel.class);  
            b.option(ChannelOption.SO_KEEPALIVE, true);  
            b.handler(new ClientInit(req, code));  
  
            // Start the client.  
            ChannelFuture f = b.connect(host, port).sync();  
  
            // Wait until the connection is closed.  
            f.channel().closeFuture().sync();  
        } finally {  
            workerGroup.shutdownGracefully();  
        }  
    }  
	
	public void close(){
		if(workerGroup != null && !workerGroup.isShutdown()){
			try {
				workerGroup.close();
			} catch (Exception e) {}
		}
	}
	
	private class ClientInit extends ChannelInitializer<SocketChannel>{
		private GeneratedMessage req;
		private short code;

		public ClientInit(GeneratedMessage req, short code) {
			this.req = req;
			this.code = code;
		}

		@Override
		protected void initChannel(SocketChannel channel) throws Exception {
			channel.pipeline().addLast("decoder", new ClientDecoder())// 解码器
			.addLast("encoder", new ClientEnconder())// 编码器
			.addLast("handler", new ClientHandler(req, code));
		}
		
		@Override
		public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
				throws Exception {
			System.out.println("断开连接");
		}
	}
	
	private class ClientEnconder extends MessageToByteEncoder<MsgEntity>{

		@Override
		protected void encode(ChannelHandlerContext ctx, MsgEntity entity,
				ByteBuf buf) throws Exception {
			buf.clear();
			byte[] bs = entity.getData();
			buf.writeInt(bs.length);
			buf.writeShort(entity.getCmdCode());
			buf.writeBytes(bs);
			ChannelFuture future = ctx.writeAndFlush(buf);
			//一次性 发完就断连
			while(future.isSuccess() && ctx.channel() != null && ctx.channel().isOpen()){
				ctx.channel().close();
				System.out.println(entity.getCmdCode() + "已发送至" + ctx.channel().remoteAddress());
				buf.retain();//不知道为啥 引用计数会自动-1 导致计数错误 手动+1弥补错误
			}
		}
	}
	
	private class ClientDecoder extends ByteToMessageDecoder{

		@Override
		protected void decode(ChannelHandlerContext ctx, ByteBuf buf,
				List<Object> list) throws Exception {
			buf.discardReadBytes();
		}
		
	}
}
