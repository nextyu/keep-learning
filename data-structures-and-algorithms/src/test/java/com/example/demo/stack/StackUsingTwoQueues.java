package com.example.demo.stack;

import java.util.LinkedList;
import java.util.Queue;

// https://java2blog.com/implement-stack-using-two-queues-in-java/
public class StackUsingTwoQueues {
    private Queue<Integer> queue1;
    private Queue<Integer> queue2;

    public StackUsingTwoQueues() {
        queue1 = new LinkedList<>();
        queue2 = new LinkedList<>();
    }

    public void push(int element) {
        if (queue1.size() == 0) {
            queue1.add(element);
        } else {
            /*
            先把queue1的元素移到queue2
            再添加新的元素到queue1
            再把queue2的元素移回到queue1

            这样就能保证每次新添加的元素排在queue1首位，queue1的旧元素先移到queue2，再移回来
             */

            int size = queue1.size();

            // queue1 -> queue2
            for (int i = 0; i < size; i++) {
                queue2.add(queue1.remove());
            }

            queue1.add(element);

            // queue2 -> queue1
            for (int i = 0; i < size; i++) {
                queue1.add(queue2.remove());
            }
        }
    }


    public int pop() {
        if (queue1.size() == 0) {
            throw new RuntimeException("stack is empty");
        }
        return queue1.remove();
    }

    public static void main(String[] args) {
        StackUsingTwoQueues stack = new StackUsingTwoQueues();
        stack.push(20);
        stack.push(40);
        stack.push(70);
        stack.push(50);
        stack.push(90);
        stack.push(110);
        stack.push(30);
        System.out.println("Removed element : "+ stack.pop());
        stack.push(170);
        System.out.println("Removed element : "+ stack.pop());
    }
}
