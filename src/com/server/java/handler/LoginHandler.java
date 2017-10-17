package com.server.java.handler;

import java.util.List;

import com.google.protobuf.GeneratedMessage;
import com.google.protobuf.InvalidProtocolBufferException;
import com.server.java.CmdHandler;
import com.server.java.constants.CmdConstant;
import com.server.java.entity.MsgEntity;

public class LoginHandler extends CmdHandler{
	
	@Override
	public void handleMsg(MsgEntity msgEntity, List<MsgEntity> commandList) {
		GeneratedMessage msg = null;
		try {
			switch (msgEntity.getCmdCode()) {
			case CmdConstant.LOGIN:
				msg = login(msgEntity);
				break;
			}
			if(msg != null){
				msgEntity.setData(msg.toByteArray());
				commandList.add(msgEntity);
			}
		} catch (Exception e) {
			logger.error("登陆失败", e);
		}
	}

	private GeneratedMessage login(MsgEntity msgEntity) throws InvalidProtocolBufferException {
		return null;
	}

}
