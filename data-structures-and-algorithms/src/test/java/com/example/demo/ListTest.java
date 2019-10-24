package com.example.demo;

import org.junit.jupiter.api.Test;

public class ListTest {


    @Test
    public void testReverse() {
        /*
            1->2->3->4->5
            5->4->3->2->1
     */
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

    @Test
    public void testIsPalindrome() {
        ListNode one = new ListNode(1);
        ListNode two = new ListNode(2);
        ListNode three = new ListNode(3);
        ListNode four = new ListNode(2);
        ListNode five = new ListNode(1);
//        ListNode oo = new ListNode(1);

        one.next = two;
        two.next = three;
        three.next = four;
        four.next = five;
//        five.next = oo;

        System.out.println(isPalindrome(one));
    }

    @Test
    public void testRemoveNthFromEnd() {
        /*
            1->2->3->4->5
            1->2->3->5
     */
        ListNode one = new ListNode(1);
        ListNode two = new ListNode(2);
        ListNode three = new ListNode(3);
        ListNode four = new ListNode(4);
        ListNode five = new ListNode(5);

        one.next = two;
        /*two.next = three;
        three.next = four;
        four.next = five;*/

        print(one);
        print(removeNthFromEnd(one, 2));
    }

    // 删除链表的倒数第N个节点
    // https://blog.csdn.net/biezhihua/article/details/79889987
    private ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode frontNode = head;
        ListNode backNode = head;

        for (int i = 0; i < n; i++) {
            frontNode = frontNode.next;
        }

        if (frontNode == null) {
            return backNode.next;
        }

        while (frontNode.next != null) {
            frontNode = frontNode.next;
            backNode = backNode.next;
        }
        backNode.next = backNode.next.next;

        return head;
    }


    // 单链表反转
    // https://www.jianshu.com/p/bd6a64d36916
    private static ListNode reverse(ListNode head) {
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

    private static void print(ListNode head) {
        if (head == null) {
            return;
        }

        ListNode next = head;
        while (next != null) {
            System.out.println(next.val);
            next = next.next;
        }

    }

    // 回文链表
    // https://github.com/andavid/leetcode-java/blob/master/note/234/README.md
    private static boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }

        ListNode prev = null;
        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            ListNode next = slow.next;
            slow.next = prev;
            prev = slow;
            slow = next;

            /*
            1->2->3->2->1

            fast = 1.next.next = 3
            next = 1.next = 2
            1.next = null
            prev = 1
            slow = 2


            fast = 3.next.next = 1
            next = 2.next = 3
            2.next = 1
            prev = 2
            slow = 3

             */
        }

        if (fast != null) {
            slow = slow.next;
            /*
             slow = 2;
             */
        }

        while (slow != null) {
            /*
            slow = 2
            prev = 2
             */
            if (slow.val != prev.val) {
                return false;
            }
            slow = slow.next;
            prev = prev.next;
        }

        return true;

    }



}