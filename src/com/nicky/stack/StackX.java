package com.nicky.stack;

/**
 * @author Nicky_chin  --Created on 2017/5/30
 */
public class StackX {

    private int maxSize; //size of stack array

    private long[] stackArray;

    private  int top; //top of stack

    public StackX(int s) {
        maxSize = s; //set array size
        stackArray = new long[maxSize]; // create array
        top = -1; //no items yet
    }

    //入栈
    public void push(long j) {
        stackArray[++top] = j;
    }

    //出栈
    public  long pop(){
        return stackArray[top--];
    }

    //查询栈顶
    public long peek() {
        return stackArray[top];
    }

    public boolean isEmpty() {
        return (-1 == top);
    }

    public boolean isFull() {
        return (maxSize - 1 == top);
    }

}
