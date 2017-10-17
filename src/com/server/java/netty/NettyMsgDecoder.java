package com.server.java.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

import java.nio.ByteOrder;

import com.server.java.entity.MsgEntity;

public class NettyMsgDecoder extends LengthFieldBasedFrameDecoder {

	public NettyMsgDecoder(ByteOrder byteOrder, int maxFrameLength,
			int lengthFieldOffset, int lengthFieldLength, int lengthAdjustment,
			int initialBytesToStrip, boolean failFast) {
		super(byteOrder, maxFrameLength, lengthFieldOffset, lengthFieldLength,
				lengthAdjustment, initialBytesToStrip, failFast);
	}

	public NettyMsgDecoder() {
		this(ByteOrder.BIG_ENDIAN, 100000, 0, 4, 2, 4, true);
	}

	@Override
	protected Object decode(ChannelHandlerContext ctx, ByteBuf byteBuf)
			throws Exception {
		// byte[] data2 = new byte[byteBuf.readableBytes()];// 其它数据为实际数据
		// System.out.println(data2.length);
		// for (int i = 0; i < byteBuf.capacity(); i++) {
		// System.out.print(byteBuf.getByte(i) + "-");
		// }
		ByteBuf frame = (ByteBuf) super.decode(ctx, byteBuf);
		if (frame == null) {
			return null;
		}

		short cmd = frame.readShort();// 先读取两个字节命令码

		byte[] data = new byte[frame.readableBytes()];// 其它数据为实际数据
		frame.readBytes(data);

		MsgEntity msgVO = new MsgEntity();
		msgVO.setCmdCode(cmd);
		msgVO.setData(data);
		return msgVO;
	}
}
