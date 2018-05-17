package com.nicky.queue;

/**
 * @author Nicky_chin  --Created on 2017/6/6
 */
public class Queue {

    private int maxSize;

    private long[] queArray;

    private int front;//队头

    private int rear; //队尾

    private int nItems; //队列中有值的个数

    public Queue(int s) {
        maxSize = s;
        queArray = new long[maxSize];
        front = 0;
        rear = -1;
        nItems = 0;
    }

    // 插入方法，队尾是数组的最后一项
    public void insert(long j) {
        if (rear == maxSize - 1) {
            rear = -1;
        }
        queArray[++rear] = j;
        nItems++;
    }

    // 先进先出
    public long remove() {
        long temp = queArray[front++];
        if (front == maxSize) {
            front = 0;
        }
        nItems--;
        return temp;
    }

    public long peekFront() {
        return queArray[front];
    }

    public boolean isEmpty() {
        return (nItems == 0);
    }

    public boolean isFull() {
        return (nItems == maxSize);
    }

    public int size() {
        return nItems;
    }

}
