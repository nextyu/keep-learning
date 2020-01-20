package com.example.demo.list;

import com.example.demo.ListNode;

// https://java2blog.com/implement-singly-linked-list-in-java/
public class SinglyLinkedList {
    private ListNode head;

    private boolean isEmpty() {
        return head == null;
    }

    public void insertFirst(int element) {
        ListNode newNode = new ListNode(element);
        newNode.next = head;
        head = newNode;
    }

    public void insertLast(int element) {
        if (isEmpty()) {
            insertFirst(element);
            return;
        }

        ListNode last = head;
        while (last.next != null) {
            last = last.next;
        }
        last.next = new ListNode(element);;
    }

    public void deleteFirst() {
        if (isEmpty()) {
            return;
        }

        head = head.next;
    }

    public void deleteLast() {
        if (isEmpty()) {
            return;
        }

        ListNode secondLast = head;
        ListNode last = head;
        while (last.next != null) {
            secondLast = last;
            last = last.next;
        }

        if (secondLast == last) {
            // 只有一个元素
            head = null;
        } else {
            secondLast.next = null;
        }
    }

    public void deleteAfter(ListNode after) {
        if (after == null || isEmpty()) {
            return;
        }

        ListNode temp = head;
        while (temp.next != null && temp.val != after.val) {
            temp = temp.next;
        }

        if (temp.next != null) {
            temp.next = temp.next.next;
        }

    }

    public void reverse() {
        if (isEmpty()) {
            return;
        }
        ListNode pre = null;
        ListNode next = null;

   /*     while (head != null) {
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }*/

        ListNode cur = head;
        while (cur != null) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        head = pre;

    }

    public static void main(String[] args) {
        SinglyLinkedList list = new SinglyLinkedList();

        list.deleteAfter(new ListNode(77));

        list.insertFirst(11);
        list.insertFirst(88);
        list.insertLast(44);
        list.insertLast(33);
        list.insertFirst(77);
        list.insertFirst(11);

        System.out.println(list);

        list.deleteAfter(new ListNode(77));
        list.deleteAfter(new ListNode(77));

        list.deleteFirst();
        list.deleteFirst();
        list.deleteLast();
        list.deleteFirst();
        list.deleteLast();
        list.deleteLast();
    }


}
