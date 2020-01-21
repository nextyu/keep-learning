package com.example.demo.list;

import com.example.demo.ListNode;

public class DetectLoopLinkedList {
    public static void main(String[] args) {
        ListNode one = new ListNode(1);
        ListNode two = new ListNode(2);
        ListNode three = new ListNode(3);
        ListNode four = new ListNode(4);
        ListNode five = new ListNode(5);

        one.next = two;
        two.next = three;
        three.next = four;
        four.next = five;
//        five.next = two;

        ListNode fast = one;
        ListNode slow = one;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast) {
                System.out.println(true);
                return;
            }
        }

        System.out.println(false);

    }
}
