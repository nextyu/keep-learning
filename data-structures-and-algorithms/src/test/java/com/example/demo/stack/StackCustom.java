package com.example.demo.stack;

public class StackCustom {
    private int size;
    private int[] arr;
    private int top;

    public StackCustom(int size) {
        this.size = size;
        this.arr = new int[size];
        this.top = -1;
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public boolean isFull() {
        return top == size - 1;
    }

    public void push(int element) {
        if (isFull()) {
            System.out.println("stack is full");
        } else {
            top++;
            arr[top] = element;
            System.out.println("push element: " + element);
        }
    }

    public int pop() {
        if (isEmpty()) {
            System.out.println("stack is empty");
            return -1;
        } else {
            int returnTop = top;
            top--;
            System.out.println("pop element: " + arr[returnTop]);
            return arr[returnTop];
        }
    }

    public int peek() {
        if (isEmpty()) {
            System.out.println("stack is empty");
            return -1;
        } else {
            int returnTop = top;
            System.out.println("peek element: " + arr[returnTop]);
            return arr[returnTop];
        }
    }

    public void print() {
        while (!isEmpty()) {
            System.out.println(pop());
        }
    }

    public static void main(String[] args) {
        StackCustom StackCustom = new StackCustom(10);
        StackCustom.pop();
        System.out.println("=================");
        StackCustom.push(10);
        StackCustom.push(30);
        StackCustom.push(50);
        StackCustom.push(40);
        System.out.println("=================");
        StackCustom.pop();
        StackCustom.pop();
        StackCustom.peek();
        StackCustom.peek();
        StackCustom.pop();
        StackCustom.pop();
        System.out.println("=================");
    }

}
