package com.example.demo.list;

import com.example.demo.ListNode;

public class NthFromEndLinkedList {
    public static void main(String[] args) {
        SinglyLinkedList list = new SinglyLinkedList();
        list.insertLast(11);/*
        list.insertLast(22);
        list.insertLast(33);
        list.insertLast(44);
        list.insertLast(55);
        list.insertLast(66);
        list.insertLast(77);
        list.insertLast(88);*/

        ListNode head = list.head;

        int n = 1;

        ListNode fast = head;
        ListNode slow = head;

        // 快指针先走 n 步
        for (int i = 0; i < n; i++) {
            if (fast != null) {
                fast = fast.next;
            }
        }

        // 快指针、慢指针一起走，当快指针达到list结尾时，慢指针就指向list倒数n位
        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }

        System.out.println(slow.val);
    }
}
