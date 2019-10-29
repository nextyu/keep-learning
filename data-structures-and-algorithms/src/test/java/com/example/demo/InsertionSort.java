package com.example.demo;

public class InsertionSort {

    public int[] insertionSort(int[] a) {
        if (a == null || a.length < 1) {
            return a;
        }

        int n = a.length;

        for (int i = 1; i < n; i++) {
            int value = a[i];
            int j = i - 1;
            // 查找插入的位置
            for (; j >= 0; j--) {
                if (a[j] > value) {
                    a[j + 1] = a[j]; // 数据移动
                } else {
                    break;
                }
            }
            a[j + 1] = value; // 插入数据
        }

        /*
        4,5,6,1,3,2
         */


        return a;
    }
}
