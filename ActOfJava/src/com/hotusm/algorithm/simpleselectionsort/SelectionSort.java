package com.hotusm.algorithm.simpleselectionsort;

/**
 * 选择排序
 * 
 * @author Hotusm <br/>
 * @date 2017年4月4日 <br/>
 * @description
 */
public class SelectionSort {

	public static void selectionSort(int[] arr) {

		int[] min = new int[1];
		int i = 0;
		while (i < arr.length) {
			min[0] = i;
			for (int j = i; j < arr.length; j++) {
				if (arr[j] < arr[min[0]]) {
					min[0] = j;
				}
			}
			int temp = arr[i];
			arr[i] = arr[min[0]];
			arr[min[0]] = temp;
			i++;
		}
	}

	public static void main(String[] args) {

		int[] arr = new int[] { 2, 5, 12, 1, 43, 23 };
		selectionSort(arr);
		for (int i : arr) {
			System.out.print(i + " ");
		}
	}
}
