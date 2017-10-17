package com.server.java;

import java.util.List;

import com.google.protobuf.GeneratedMessage;
import com.server.java.entity.MsgEntity;

public abstract class SimpleCmdHandler extends CmdHandler{

	@Override
	public void handleMsg(MsgEntity msgEntity, List<MsgEntity> commandList) {
		try {
			GeneratedMessage msg = handleMsg(msgEntity.getCmdCode(), msgEntity.getData());
			if(msg != null){
				msgEntity.setData(msg.toByteArray());
				commandList.add(msgEntity);
			}
		} catch (Throwable e) {
			logger.error(msgEntity.getCmdCode() + "处理报错", e);
		}
	}

	public abstract GeneratedMessage handleMsg(short code, byte[] msgDatas) throws Throwable;
}
