package com.nicky.queue;

/**
 * @author Nicky_chin  --Created on 2017/6/6
 *         队列： 先进先出(FIFO)。和栈一样，队列插入和移除数据的复杂度均为O(1)
 *         优先队列插入O()和移除复杂度为O(1)
 *         优先级队列： 在优先级队列中，数据项按照关键字的值有序，关键字最小的数据项总在对头，
 *         数据项插入的时候会按照顺序插入到合适的位置以确保队列的顺序，
 *         从后往前将小于插入项的数据项后移。在图的最小生成树算法中应用优先级队列。
 *
 *
 */
public class QueueApp {

    public static void main(String[] args) {
        Queue theQueue = new Queue(5);

        theQueue.insert(10);
        theQueue.insert(20);
        theQueue.insert(30);
        theQueue.insert(40);

        theQueue.remove();
        theQueue.remove();
        theQueue.remove();

        theQueue.insert(50);
        theQueue.insert(60);
        theQueue.insert(70);
        theQueue.insert(80);

        while (!theQueue.isEmpty()) {
            long n = theQueue.remove();
            System.out.print(n); // 40, 50, 60, 70, 80
            System.out.print(" ");
        }
        System.out.println("");


        //优先队列，无论怎么插入，按顺序移除
        PriorityQ thePQ = new PriorityQ(5);
        thePQ.insert(30);
        thePQ.insert(50);
        thePQ.insert(10);
        thePQ.insert(40);
        thePQ.insert(20);

        while (!thePQ.isEmpty()) {
            long item = thePQ.remove();
            System.out.print(item + " "); // 10, 20, 30, 40, 50
        }
        System.out.println("");
    }

}
