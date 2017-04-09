package com.hotusm.algorithm.quicksort;

import javax.swing.text.html.HTMLDocument.HTMLReader.HiddenAction;

/**
 * 快速排序
 * 
 * @author Hotusm <br/>
 * @date 2017年4月4日 <br/>
 * @description
 */
public class QuickSort {

	public static void quickSort(int[] arr, int low, int height) {

		int pivot;
		if (low < height) {
			pivot = partition(arr, low, height);
			quickSort(arr, low, pivot - 1);
			quickSort(arr, pivot + 1, height);
		}
	}

	private static int partition(int[] arr, int low, int height) {
		int pivotKey = arr[low];
		while (low < height) {
			while (low < height && arr[height] > pivotKey) {
				height--;
			}
			arr[low] = arr[height];
			while (low < height && arr[low] < pivotKey) {
				low++;
			}
			arr[height] = arr[low];
		}
		arr[low]=pivotKey;
		return low;
	}

	public static void main(String[] args) {

		int[] arr = new int[] { 4, 3, 2, 1 };

		quickSort(arr, 0, arr.length - 1);

		for (int i : arr) {
			System.out.print(i + " ");
		}
	}
}
