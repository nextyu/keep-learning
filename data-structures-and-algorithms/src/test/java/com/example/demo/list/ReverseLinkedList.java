package com.example.demo.list;

import com.example.demo.ListNode;

public class ReverseLinkedList {
    public static void main(String[] args) {
        SinglyLinkedList list = new SinglyLinkedList();
        list.insertLast(11);
        list.insertLast(22);
        list.insertLast(33);
        list.insertLast(44);

        list.reverse();
    }
}
