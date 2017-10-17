package com.server.java.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomUtil {
	
	public static int getRandom(int num) {
		if (num <= 0) {
			return 0;
		}
		Random r = new Random();
		return r.nextInt(num) + 1;
	}

	
	public static int getRandom(int min, int max) {
		int num = max - min + 1;
		if (num <= 0) {
			return 0;
		}
		Random r = new Random();
		return r.nextInt(num) + 1 + (min - 1);
	}

	
	public static int[] getRandomArea(int max, int num) {
		int[] r = new int[num];
		int[] ro = new int[max];
		for (int j = 0; j < ro.length; j++) {
			ro[j] = j + 1;
		}
		for (int i = 1; i <= num; i++) {
			int random = getRandom(max - (i - 1));
			random--;
			r[i - 1] = ro[random];
			int temp = ro[max - i];
			ro[max - i] = ro[random];
			ro[random] = temp;
		}
		return r;
	}

	
	public static int getValueByRate(int beginRange, int newRandomRate,
			int randomRate, int random) {
		double d = ((double) newRandomRate) * ((double) random)
				/ (double) (randomRate);
		int d2 = (int) d + 1;
		int i = beginRange - 1 + d2;
		int max = beginRange + newRandomRate - 1;
		i = i > max ? max : i;
		return i;

	}

	public static int test(int beginRange, int newRandomRate, int randomRate,
			int random) {
		double d = ((double) newRandomRate) * ((double) random)
				/ (double) (randomRate);
		int d2 = (int) d + 1;
		return beginRange - 1 + d2;

	}

	public static void main(String[] args) {
		int a[] = getRandomArea(3, 2);
		for (int i = 0; i < a.length; i++) {
			System.out.println(a[i]);
		}
	}

	public static int probabilitySize10(List<Integer> list) {
		int index = -1;
		if (list.size() == 10) {
			Random r = new Random();
			int sum = 0, sum2 = 0;
			int array[] = new int[10];
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i) > 0) {
					sum += list.get(i);
				}
			}
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i) > 0) {
					sum2 += list.get(i);
					array[i] = sum2;
				} else {
					sum2 += 0;
					array[i] = sum2;
				}
			}
			int randomNum = r.nextInt(sum);
			if (randomNum >= 0 && randomNum <= array[0]) {
				index = 0;
				return index;
			} else if (randomNum > array[0] && randomNum <= array[1]) {
				index = 1;
				return index;
			} else if (randomNum > array[1] && randomNum <= array[2]) {
				index = 2;
				return index;
			} else if (randomNum > array[2] && randomNum <= array[3]) {
				index = 3;
				return index;
			} else if (randomNum > array[3] && randomNum <= array[4]) {
				index = 4;
				return index;
			} else if (randomNum > array[4] && randomNum <= array[5]) {
				index = 5;
				return index;
			} else if (randomNum > array[5] && randomNum <= array[6]) {
				index = 6;
				return index;
			} else if (randomNum > array[6] && randomNum <= array[7]) {
				index = 7;
				return index;
			} else if (randomNum > array[7] && randomNum <= array[8]) {
				index = 8;
				return index;
			} else if (randomNum > array[8] && randomNum <= array[9]) {
				index = 9;
				return index;
			}
		} else {
			System.out.println("���󣺼��ϳ��ȱ���Ϊ10");
		}
		return index;
	}

	
	public static int getRandomFromList(List<Integer> list) {
		int index = 0;
		Random r = new Random();
		int sum = 0, sum2 = 0;
		// int array[]=new int[10];
		List<Integer> newList = new ArrayList<Integer>();
		newList.add(0);
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i) > 0) {
				sum += list.get(i);
			}
		}
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i) > 0) {
				sum2 += list.get(i);
				newList.add(sum2);
			} else {
				sum2 += 0;
				newList.add(sum2);
			}
		}
		int randomNum = 0;
		try {
			randomNum = r.nextInt(sum);
		} catch (Exception e) {
			return -1;
		}
		int x = 0;
		for (int i = 0; i < newList.size(); i++) {
			if (randomNum >= newList.get(index)
					&& randomNum <= newList.get(index + 1)) {
				x = 1;
				return index + 1;
			} else {
				index++;
			}
		}
		if (x == 1) {
			return index + 1;
		} else {
			return -1;
		}
	}

	
	public static boolean isTrue() {
		Random r = new Random();
		int i = r.nextInt(2);
		if (i == 1) {
			return true;
		} else {
			return false;
		}
	}

	
	public static boolean isRandomOk(int max, int rate) {
		int seed = getRandom(max);
		if (seed > 0 && seed <= rate) {
			return true;
		} else {
			return false;
		}
	}
	
	public static int min(int ... as){
		int min = as[0];
		for(int a : as){
			min = Math.min(a, min);
		}
		return min;
	}
	
	public static int max(int ... as){
		int max = as[0];
		for(int a : as){
			max = Math.max(a, max);
		}
		return max;
	}
}
