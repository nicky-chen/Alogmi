package com.nicky.stack;

/**
 * @author Nicky_chin  --Created on 2017/5/30
 */
public class StackApp {

    public static void main(String[] args) {
        StackX theStack = new StackX(10);
        theStack.push(20);
        theStack.push(40);
        theStack.push(60);
        theStack.push(80);

        while (!theStack.isEmpty()) {
            long value = theStack.pop();
            System.out.println(value);

        }
    }

}
