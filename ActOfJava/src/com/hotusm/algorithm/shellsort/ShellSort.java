package com.hotusm.algorithm.shellsort;

/**
 * Ï£¶ûÅÅĞò
 * 
 * @author Hotusm <br/>
 * @date 2017Äê4ÔÂ4ÈÕ <br/>
 * @description
 */
public class ShellSort {

	public static void shellSort(int[] arr) {

		int i, j, k, gap;
		for (gap = arr.length / 2; gap > 0; gap /= 2) {
			for (i = 0; i < gap; i++) {
				for (j = i + gap; j < arr.length; j += gap) {
					if (arr[j] < arr[j - gap]) {
						int temp = arr[j];
						for (k = j - gap; k >= 0 && arr[k] > temp; k -= gap) {
							arr[k + gap] = arr[k];
						}
						arr[k + gap] = temp;
					}
				}
			}
		}
	}

	public static void shellSort1(int[] arr) {
		int j, k, gap;
		for (gap = arr.length / 2; gap > 0; gap /= 2) {
			for (j = gap; j < arr.length; j++) {
				if (arr[j] < arr[j - gap]) {
					int temp = arr[j];
					for (k = j - gap; k >= 0 && arr[k] > temp; k -= gap) {
						arr[k + gap] = arr[k];
					}
					arr[k + gap] = temp;
				}
			}
		}
	}

	public static void main(String[] args) {

		int[] arr = new int[] { 13, 1, 25, 22, 89, 3 };

		shellSort1(arr);
		for (int i : arr) {
			System.out.print(i + " ");
		}
		shellSort(arr);
		for (int i : arr) {
			System.out.print(i + " ");
		}
	}
}
