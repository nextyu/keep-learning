package com.example.demo.list;

import com.example.demo.ListNode;

/**
 *
 * Floyd's Cycle-Finding Algorithm
 *
 * https://codingfreak.blogspot.com/2012/09/detecting-loop-in-singly-linked-list_22.html
 * https://xbuba.com/questions/2663115
 *
 */
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

        five.next = two; // 循环

        ListNode hare = one;
        ListNode tortoise = one;

        /*
        乌龟走一步，野兔走两步，如果存在循环链表，乌龟和野兔就回相遇，野兔跑的快些，多跑了n圈
         */


        while (hare != null && hare.next != null) {
            tortoise = tortoise.next;
            hare = hare.next.next;

            if (tortoise == hare) {
                System.out.println(tortoise.val);
                System.out.println(true);
                return;
            }
        }

        System.out.println(false);

    }
}
