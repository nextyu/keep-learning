package com.example.demo.stack;

import com.example.demo.ListNode;

// https://java2blog.com/implement-stack-using-linked-list-in-java/
public class ListStack {
    private ListNode head;

    public void push(int element) {
        ListNode oldHead = head;
        ListNode node = new ListNode(element);
        head = node;
        node.next = oldHead;
        System.out.println("push element: " + element);
    }

    public int pop() {
        if (isEmpty()) {
            System.out.println("stack is empty");
            return -1;
        } else {
            ListNode returnTop = head;
            head = head.next;
            System.out.println("pop element: " + returnTop.val);
            return returnTop.val;
        }
    }

    public int peek() {
        if (isEmpty()) {
            System.out.println("stack is empty");
            return -1;
        } else {
            ListNode returnTop = head;
            System.out.println("peek element: " + returnTop.val);
            return returnTop.val;
        }
    }

    private boolean isEmpty() {
        return head == null;
    }

    public static void main(String[] args) {
        ListStack listStack = new ListStack();
        listStack.pop();
        System.out.println("=================");
        listStack.push(10);
        listStack.push(30);
        listStack.push(50);
        listStack.push(40);
        System.out.println("=================");
        listStack.pop();
        listStack.pop();
        listStack.peek();
        listStack.peek();
        listStack.pop();
        listStack.pop();
        listStack.pop();
        System.out.println("=================");
    }
}
