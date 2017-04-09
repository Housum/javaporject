package com.hotusm.algorithm.insertvaluesort;

/**
 * 插值排序
 * 
 * @author Hotusm <br/>
 * @date 2017年4月4日 <br/>
 * @description
 */
public class InsertValueSort {

	public static void insertValueSort(int[] arr) {

		int i, j;

		for (i = 1; i < arr.length; i++) {

			if (arr[i] < arr[i - 1]) {
				int temp = arr[i];
				for (j = i - 1; j >= 0 && arr[j] > temp; j--) {
					arr[j + 1] = arr[j];
				}
				arr[j + 1] = temp;
			}
		}

	}

	public static void main(String[] args) {

		int[] arr = new int[] { 2, 1, 89, 34, 288, 4 };

		insertValueSort(arr);

		for (int i : arr) {
			System.out.print(i + " ");
		}
	}
}
