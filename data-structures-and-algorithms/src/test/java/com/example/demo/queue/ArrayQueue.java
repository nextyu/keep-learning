package com.example.demo.queue;

// https://java2blog.com/implement-queue-using-array-in-java/
public class ArrayQueue {
    int size;
    int writeIndex;
    int readIndex;
    int[] arr;

    public ArrayQueue(int size) {
        this.size = size;
        this.writeIndex = 0;
        this.readIndex = 0;
        this.arr = new int[size];
    }

    public void enqueue(int element) {
        if (readLast()) {
            writeIndex = 0;
            readIndex = 0;
        }

        if (isFull()) {
            System.out.println("queue is full");
        } else {
            arr[writeIndex++] = element;
        }
    }

    public int dequeue() {
        if (isEmpty()) {
            System.out.println("queue is empty");
            return -1;
        }

        if (readLast()) {
            writeIndex = 0;
            readIndex = 0;
            System.out.println("queue is last");
            return -1;
        }

        return arr[readIndex++];

    }

    private boolean isEmpty() {
        return writeIndex == 0;
    }

    private boolean isFull() {
        return writeIndex == size;
    }

    private boolean readLast() {
        return readIndex == writeIndex;
    }

    public static void main(String[] args) {
        ArrayQueue queue = new ArrayQueue(4);
        System.out.println(queue.dequeue());
        queue.enqueue(99);
        queue.enqueue(66);
        queue.enqueue(55);

        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        queue.enqueue(88888);
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        queue.enqueue(9999999);
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());


        queue.enqueue(1646);
        queue.enqueue(58);
        queue.enqueue(77);
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());

    }

}
