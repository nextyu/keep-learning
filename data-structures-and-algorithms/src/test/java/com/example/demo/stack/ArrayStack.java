package com.example.demo.stack;

// https://java2blog.com/implement-stack-using-array-in-java/
// 后进先出
public class ArrayStack {

    private int[] items = new int[4];
    private int index = -1;

    public void push(int i) {
        if (index == items.length - 1) {
            throw new RuntimeException("stack is full");
        }
        items[++index] = i;
    }

    public int pop() {
        if (index == -1) {
            throw new RuntimeException("stack is empty");
        }
        return items[index--];
    }

    public static void main(String[] args) {
        ArrayStack arrayStack = new ArrayStack();
        arrayStack.push(12);
        arrayStack.push(19);
        arrayStack.push(99);
        arrayStack.push(88);

        System.out.println(arrayStack.pop());
        System.out.println(arrayStack.pop());
        System.out.println(arrayStack.pop());
        System.out.println(arrayStack.pop());
    }

}
