package com.server.java.entity.provider;

import java.util.List;

import com.db.gamedata.csv.failmsg.entity.FailMsgData;

public class FailMsgDataProvider extends DataProvider<FailMsgData>{
	private static FailMsgDataProvider instance;
	
	private FailMsgDataProvider(){}
	
	public static FailMsgDataProvider getInstance(){
		if(instance == null){
			instance = new FailMsgDataProvider();
		}
		return instance;
	}

	@Override
	protected Class<FailMsgData> getDataClass() {
		// TODO Auto-generated method stub
		return FailMsgData.class;
	}
	
	@Override
	protected void loadDataOperation(List<FailMsgData> datas) {
		for(FailMsgData data : datas){
			dataMap.put(data.getId(), data);
		}
	}

	@Override
	protected boolean checkError(FailMsgData data) {
		// TODO Auto-generated method stub
		return false;
	}

}
