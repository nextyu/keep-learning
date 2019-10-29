package com.example.demo;

import org.junit.jupiter.api.Test;

public class BubbleSort {

    public int[] bubbleSort(int[] a) {
        if (a == null || a.length <= 1) {
            return a;
        }


        int n = a.length;
        for (int i = 0; i < n; i++) {
            // 提前退出冒泡循环的标志位
            boolean flag = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (a[j] > a[j + 1]) {
                    int temp = a[j + 1];
                    a[j + 1] = a[j];
                    a[j] = temp;
                    flag = true;  // 表示有数据交换
                }
            }
            if (!flag) {
                break;
            }
        }
        return a;
    }

    @Test
    public void test() {
        int[] a = new int[]{6, 3, 4, 5, 2, 1};
        a = bubbleSort(a);
        for (int i : a) {
            System.out.println(i);
        }
    }
}
