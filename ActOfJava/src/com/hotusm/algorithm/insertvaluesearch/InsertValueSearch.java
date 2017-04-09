package com.hotusm.algorithm.insertvaluesearch;

import java.util.Arrays;

/**
 * 插值查找
 * 
 * @author Hotusm <br/>
 * @date 2017年4月3日 <br/>
 * @description
 */
public class InsertValueSearch {

	public static boolean search(int[] arr, int key, int left, int right) {
		while (left < right) {
			int middle = left + (right - left) * ((key - arr[left]) / (arr[right] - arr[left]));
			if (arr[middle] == key) {
				return true;
			}
			if (key < arr[middle]) {
				right = middle - 1;
			} else {
				left = middle + 1;
			}
		}
		return false;
	}

	public static void main(String[] args) {
		int[] arr = new int[] { 2, 3, 45, 1234 };
		Arrays.sort(arr);
		System.out.println(search(arr, 45, 0, arr.length - 1));
	}

}
