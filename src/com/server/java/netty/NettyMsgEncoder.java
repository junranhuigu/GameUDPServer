package com.server.java.netty;

import com.server.java.entity.MsgEntity;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;


public class NettyMsgEncoder extends MessageToByteEncoder<MsgEntity> {

	@Override
	protected void encode(ChannelHandlerContext ctx, MsgEntity msg,
			ByteBuf byteBuf) throws Exception {
		int dataLength = msg.getData() == null ? 0 : msg.getData().length;
		if(dataLength <= 0){//长度为0的数据不发送 前端会报错
			return;
		}
		byteBuf.ensureWritable(4 + dataLength);
		byteBuf.writeInt(dataLength);
		byteBuf.writeShort(msg.getCmdCode());
		if (dataLength > 0) {
			byteBuf.writeBytes(msg.getData());
		}
	}
}
