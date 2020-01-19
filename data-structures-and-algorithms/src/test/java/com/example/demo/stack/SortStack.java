package com.example.demo.stack;

// https://java2blog.com/sort-stack-using-another-stack/
public class SortStack {

    public static void main(String[] args) {
        StackCustom stack = new StackCustom(4);

        StackCustom tempStack = new StackCustom(4);

        stack.push(9);
        stack.push(77);
        stack.push(4);
        stack.push(10);

        while (!stack.isEmpty()) {
            int pop = stack.pop();

            while (!tempStack.isEmpty() && pop < tempStack.peek()) {
                stack.push(tempStack.pop());
            }

            tempStack.push(pop);
        }


        tempStack.print();

    }

}
