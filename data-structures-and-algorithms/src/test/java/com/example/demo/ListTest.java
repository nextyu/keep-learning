package com.example.demo;

public class ListTest {

    /*
    1->2->3->4->5
    5->4->3->2->1
     */

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

        print(one);
        print(reverse(one));
    }

    // 单链表反转
    // https://www.jianshu.com/p/bd6a64d36916
    public static ListNode reverse(ListNode head) {
        if (head == null) {
            return null;
        }

        ListNode next = null;
        ListNode pre = null;

        while (head != null) {
            next = head.next; // 保存当前头结点的下一个节点
            head.next = pre; // 当前头结点的下一个结点是上一次的头结点
            pre = head; // 上一个结点为当前头结点
            head = next; // 头结点变成当前头结点的下一个节点
        }

        /*

        next = 1.next = 2
        1.next = null
        pre = 1
        head = 2

        next = 2.next = 3
        2.next = 1
        pre = 2
        head = 3

        next = 3.next = 4
        3.next = 2
        pre = 3
        head = 4

        next = 4.next = 5
        4.next = 3
        pre = 4
        head = 5

        */

        return pre;
    }

    public static void print(ListNode head) {
        if (head == null) {
            return;
        }

        ListNode next = head;
        while (next != null) {
            System.out.println(next.val);
            next = next.next;
        }

    }

}