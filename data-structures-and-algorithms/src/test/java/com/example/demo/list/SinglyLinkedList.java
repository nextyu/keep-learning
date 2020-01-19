package com.example.demo.list;

import com.example.demo.ListNode;

public class SinglyLinkedList {
    private ListNode head;

    private boolean isEmpty() {
        return head == null;
    }

    public void insert(int element) {
        ListNode newNode = new ListNode(element);
        if (head == null) {
            head = newNode;
        } else {
            head.next = newNode;
        }
    }

    public void insertFirst(int element) {
        ListNode newNode = new ListNode(element);
        newNode.next = head;
        head = newNode;
    }


}
