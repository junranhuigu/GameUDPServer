package com.server.java.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.alibaba.fastjson.JSON;

public class StringUtil {
	private static StringUtil su = new StringUtil();

	public static StringUtil getInstance() {
		return su;
	}

	public boolean isEmpty(String s) {
		boolean result = true;
		if (s != null && !"".equals(s)) {
			result = false;
		}
		return result;
	}

	public String getDoubleNumber(int number) {
		String result = null;
		if (number < 0 || number >= 100) {
			result = "00";
		} else if (number < 10) {
			result = "0" + number;
		} else {
			result = "" + number;
		}
		return result;
	}

	public String getDoubleNumber(String numberString) {
		int number = Integer.parseInt(numberString);
		String result = null;
		if (number < 0 || number >= 100) {
			result = "00";
		} else if (number < 10) {
			result = "0" + number;
		} else {
			result = "" + number;
		}
		return result;
	}

	public String nullFilter(String result) {
		if ("".equals(result)) {
			result = null;
		}
		return result;
	}

	public String getRandomString(int n) {
		StringBuilder builder = new StringBuilder();
		Random random = new Random();
		for (int i = 0; i < n; ++i) {
			int randomNum = random.nextInt(62);// 随机�?
												// 0-9为数�?0-9,10-35为字母a-z,36-61为字母A-Z
			if (randomNum <= 9) {
				builder.append((char) ('0' + randomNum));
			} else if (randomNum <= 35) {
				builder.append((char) (randomNum - 10 + 'a'));
			} else {
				builder.append((char) (randomNum - 36 + 'A'));
			}
		}
		return builder.toString();
	}

	public String parseLong2TimeStr(long time) {
		if (time >= 0) {
			StringBuilder builder = new StringBuilder();
			long secondUnit = 1000L;
			long minuteUnit = 60 * secondUnit;
			long hourUnit = 60 * minuteUnit;
			long dayUnit = 24 * hourUnit;
			if (time >= dayUnit) {// �?
				builder.append(time / dayUnit).append("�?");
				time %= dayUnit;
			}
			if (time >= hourUnit) {
				builder.append(time / hourUnit).append("�?");
				time %= hourUnit;
			}
			if (time >= minuteUnit) {
				builder.append(time / minuteUnit).append("�?");
				time %= minuteUnit;
			}
			if (time >= secondUnit) {
				builder.append(time / secondUnit).append("�?");
				time %= secondUnit;
			}
			if (time > 0) {
				builder.append(time).append("毫秒");
			}
			return builder.toString();
		}
		return null;
	}

	public List<List<String>> getListFromX(String str) {
		List<List<String>> allList = new ArrayList<List<String>>();
		if (str == null || "".equals(str)) {
			return allList;
		}
		String[] arr = str.split("x");
		for (int i = 0; i < arr.length; i++) {
			List<String> list = new ArrayList<String>();
			String[] arr2 = arr[i].split(";");
			list.add(arr2[0]);
			list.add(arr2[1]);
			allList.add(list);
		}
		return allList;
	}

	public List<String> getListFromFen(String str) {
		List<String> list = new ArrayList<String>();
		if (str == null || "".equals(str)) {
			return list;
		}
		String[] arr = str.split(";");
		for (int i = 0; i < list.size(); i++) {
			list.add(arr[0]);
		}
		return list;
	}

	public List<String> getListFromGang(String str) {
		List<String> list = new ArrayList<String>();
		if (str == null || "".equals(str)) {
			return list;
		}
		String[] arr = str.split("\\|");
		for (int i = 0; i < arr.length; i++) {
			list.add(arr[i]);
		}
		return list;
	}

	public List<String> getList(String str, String splitStr) {
		List<String> list = new ArrayList<String>();
		if (str == null || "".equals(str)) {
			return list;
		}
		String[] arr = str.split(splitStr);
		for (int i = 0; i < arr.length; i++) {
			list.add(arr[i]);
		}
		return list;
	}

	public List<Integer> getList2Int(String str, String splitStr) {
		List<String> list = getList(str, splitStr);
		if (list.isEmpty()) {
			return Collections.emptyList();
		} else {
			List<Integer> result = new ArrayList<>();
			for (String s : list) {
				try {
					int number = Integer.parseInt(s);
					result.add(number);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			return result;
		}
	}

	public String createId(String accountId, String serverId) {
		String str = accountId + "" + RandomUtil.getRandom(10, 99) + ""
				+ serverId;
		return str;
	}

	public Map<String, Integer> getGoodMapFromStr(String str) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		return map;
	}

	public <T> String list2String(List<T> list, String seperateChar) {
		StringBuilder builder = new StringBuilder();
		if (list != null) {
			for (T t : list) {
				builder.append(t.toString()).append(seperateChar);
			}
		}
		return builder.toString();
	}

	/**
	 * 获取指定位长的随机字符串
	 * */
	public String randomString(int len) {
		StringBuilder builder = new StringBuilder();
		Random random = new Random();
		for (int i = 0; i < len; ++i) {
			int randomNum = random.nextInt(62);// 随机数
												// 0-9为数字0-9,10-35为字母a-z,36-61为字母A-Z
			if (randomNum <= 9) {
				builder.append((char) ('0' + randomNum));
			} else if (randomNum <= 35) {
				builder.append((char) (randomNum - 10 + 'a'));
			} else {
				builder.append((char) (randomNum - 36 + 'A'));
			}
		}
		return builder.toString();
	}

	public List<List<Integer>> mergeValueList(List<List<Integer>> a) {
		List<List<Integer>> nlist = new ArrayList<List<Integer>>();
		if (a == null || a.size() == 0) {
			return nlist;
		}
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		for (List<Integer> l : a) {
			if (map.get(l.get(0)) == null) {
				map.put(l.get(0), 0);
			}
			map.put(l.get(0), map.get(l.get(0)) + l.get(1));
		}
		Iterator<Entry<Integer, Integer>> ite = map.entrySet().iterator();
		while (ite.hasNext()) {
			Entry<Integer, Integer> entry = ite.next();
			List<Integer> l = new ArrayList<Integer>();
			l.add(entry.getKey());
			l.add(entry.getValue());
			nlist.add(l);
		}
		return nlist;
	}

	/**
	 * 解析xxxx1;yyyy1$xxxx2;yyyy2的字符串
	 * */
	public Map<Integer, Integer> getMapByDollarAndFen(String content) {
		Map<Integer, Integer> result = new HashMap<>();
		List<String> dollars = getList(content, "\\$");
		for (String info : dollars) {
			List<Integer> list = getList2Int(info, ";");
			if (list.size() >= 2) {
				result.put(list.get(0), list.get(1));
			}
		}
		return result;
	}

	/**
	 * 矫正人物名称
	 * */
	public String correctName(String name) {
		name = name.replace(",", "，").replace("\n", "");
		return name;
	}

	/***
	 * ;属性toList
	 * **/
	@SuppressWarnings("unchecked")
	public List<List<Integer>> parseJson(String conte) {
		conte = conte.replace(";", ",");
		List<List<Integer>> l = (List<List<Integer>>) JSON.parse(conte);
		return l;
	}

	/** roll属性类型*/
	public int randomcalType(List<List<Integer>> l) {
		int value = 0;
		List<Integer> l0 = new ArrayList<>();
		List<Integer> l1 = new ArrayList<>();
		int sum = 0;
		for (List<Integer> i : l) {
			l0.add(i.get(0));
			l1.add(i.get(1));
			sum += i.get(1);
		}
		int rom = RandomUtil.getRandom(sum);
		int max = 0;
		for (int i = 0; i < l0.size(); i++) {
			max += l1.get(i);
			if (rom <= max) {
				max = 0;
				value = l0.get(i);
				break;
			}
		}
		return value;
	}
}
