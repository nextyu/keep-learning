package com.example.demo.list;

import com.example.demo.ListNode;
// https://java2blog.com/find-middle-element-of-linkedlist-in/
public class MiddleLinkedList {
    public static void main(String[] args) {
        SinglyLinkedList list = new SinglyLinkedList();
        list.insertLast(11);
        list.insertLast(22);
        list.insertLast(33);/*
        list.insertLast(44);
        list.insertLast(55);
        list.insertLast(66);
        list.insertLast(77);
        list.insertLast(88);*/

        ListNode head = list.head;

        // 快慢指针，快指针是慢指针的两倍，当快指针达到list结尾时，慢指针就指向list中间
        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        System.out.println(slow.val);

    }
}
