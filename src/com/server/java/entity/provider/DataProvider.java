package com.server.java.entity.provider;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;

import com.hp.csv.GameDataReader;
import com.server.java.log.LoggerProvider;

/**
 * @author jiawei csv加载器基类 建议：使用单例模式实例化子类
 * */
public abstract class DataProvider<T> {
	public static final Logger LOGGER = LoggerProvider
			.getLogger(DataProvider.class);
	protected Map<Integer, T> dataMap;

	protected DataProvider() {
		dataMap = new HashMap<>();
	}

	/**
	 * 加载csv中的数据
	 * 
	 * @throws SecurityException
	 * */
	private void loadData() throws NotReleaseMethodException {
		Class<?> dataClass = getDataClass();
		if (dataClass == null) {
			throw new NotReleaseMethodException(this.getClass()
					+ "没有实现getDataClass方法");
		}
		List datas = GameDataReader.genBean(dataClass);
		dataMap.clear();
		loadDataOperation(datas);
	}

	/**
	 * 加载csv数据的方法 --可以通过重写该方法 实现新的处理逻辑
	 * */
	protected void loadDataOperation(List<T> datas) {
		for (T data : datas) {
			try {
				Method method = data.getClass().getMethod("getId", null);
				int id = (int) method.invoke(data, null);
				dataMap.put(id, data);
			} catch (Exception e) {
				LOGGER.error("", e);
			}
		}
	}

	public void init() {
		String dataName = getDataClass().getName();
		dataName = dataName.substring(dataName.lastIndexOf(".") + 1);
		LOGGER.info("init  " + dataName + " to game server start...");
		try {
			loadData();
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("init " + dataName + " error", e);
		}
		LOGGER.info("init " + dataName + " to game server end...total num = "
				+ size());
	}

	public T find(int id) {
		return dataMap.get(id);
	}

	public List<T> allData() {
		return new ArrayList<>(dataMap.values());
	}

	public Set<Integer> keys() {
		return dataMap.keySet();
	}

	/**
	 * 加载数据的数量
	 * */
	public int size() {
		return dataMap.size();
	}

	/**
	 * 提供数据的封装类 该类必须实现getId方法 作为key值提供
	 * */
	protected abstract Class<T> getDataClass();

	public void check() {
		for (int id : keys()) {
			T data = find(id);
			boolean error = checkError(data);
			if (error) {
				System.err.println(data.getClass() + " " + id);
			}
		}
	}

	/**
	 * 检测数据实现方法
	 * */
	protected abstract boolean checkError(T data);
}
