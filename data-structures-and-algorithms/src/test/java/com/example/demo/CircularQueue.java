package com.example.demo;

/**
 * 循环链表
 */
public class CircularQueue {
    private String[] items;
    private int n = 0; // 数组大小
    private int head = 0; // 队头下标
    private int tail = 0; // 队尾下标

    public CircularQueue(int capacity) {
        items = new String[capacity];
        n = capacity;
    }

    public boolean enqueue(String item) {
        // 队列满了
        if ((tail + 1) % n == head) {
            return false;
        }
        items[tail] = item;
        /*
        n = 5
        tail = 0
        tail = (0+1)%5 = 1;
        tail = (1+1)%5 = 2;
        tail = (2+1)%5 = 3;
        tail = (3+1)%5 = 4; return false

         */
        tail = (tail + 1) % n;
        return true;
    }

    public String dequeue() {
        // 队列为空
        if (head == tail) {
            return null;
        }
        /*
        n = 5
        tail = 4

        head = 0
        head = (0+1)%5 = 1;
        head = (1+1)%5 = 2;
        head = (2+1)%5 = 3;
        head = (3+1)%5 = 4;

         */
        String item = items[head];
        head = (head + 1) % n;
        return item;
    }
}
