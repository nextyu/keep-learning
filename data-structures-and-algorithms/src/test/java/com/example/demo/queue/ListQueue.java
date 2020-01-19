package com.example.demo.queue;


import com.example.demo.ListNode;

// https://java2blog.com/implement-queue-using-linked-list-in-java/
public class ListQueue {
    private ListNode front;
    private ListNode rear;

    public void enqueue(int element) {
        ListNode node = new ListNode(element);
        if (front == null) {
            front = node;
        } else {
            rear.next = node;
        }
        rear = node;
    }

    public int dequeue() {
        if (front == null) {
            System.out.println("queue is empty");
            return -1;
        }
        ListNode oldFront = front;
        front = front.next;
        return oldFront.val;
    }

    public static void main(String[] args) {
        ListQueue queue = new ListQueue();
        queue.enqueue(6);
        queue.enqueue(4);
        queue.enqueue(9);
        queue.enqueue(1);

        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        queue.enqueue(5555);
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());

        queue.enqueue(888);
        System.out.println(queue.dequeue());
    }
}
