package com.db.gamedata.structure;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

/**
 * 分离器
 * 根据输入的值 进行某种程度的分离
 * @param K 表示区分对象的类
 * @param V 表示存储对象的类
 * */
public abstract class Separator<K, V> {
	private HashMap<K, List<V>> result = new HashMap<>();
	
	@SuppressWarnings("hiding")
	public <K, V> Separator() {
		// TODO Auto-generated constructor stub
	}
	
	public boolean add(V v){
		K k = separate(v);
		if(k != null){
			List<V> list = result.get(k);
			if(list == null){
				list = new ArrayList<>();
				result.put(k, list);
			}
			list.add(v);
			return true;
		} else {
			return false;
		}
	}
	
	public void addAll(Collection<V> collection){
		if(collection != null && !collection.isEmpty()){
			for(V v : collection){
				add(v);
			}
		}
	}
	
	public void clear(){
		result = new HashMap<>();//这样处理 是为了Separator的重用
	}
	
	public HashMap<K, List<V>> getResult() {
		return result;
	}

	/**
	 * 分离判断的方法
	 * */
	public abstract K separate(V v);
	
	/**
	 * 对分离的对象进行合并的方法
	 * @param total 汇总的对象
	 * */
	protected void merge(V v, V total){}
	
	/**
	 * 对分离的结果进行合并，需重写merge()方法
	 * */
	public HashMap<K, V> getMergeResult(){
		HashMap<K, V> result = new HashMap<>();
		Iterator<Entry<K, List<V>>> ite = getResult().entrySet().iterator();
		while(ite.hasNext()){
			Entry<K, List<V>> entry = ite.next();
			List<V> list = entry.getValue();
			V total = list.get(0);
			for(int i = 1; i < list.size(); ++ i){
				V v = list.get(i);
				merge(v, total);
			}
			result.put(entry.getKey(), total);
		}
		return result;
	}
	
	public List<V> getMergeResultList(){
		return new ArrayList<>(getMergeResult().values());
	}
}
