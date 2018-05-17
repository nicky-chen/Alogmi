package com.nicky.sorts.bubble;

/**
 * 冒泡排序  比较的次数和交换的次数都是 大o表示法 O(N^2)
 * @author Nicky_chin  --Created on 2017/5/16
 */
public class BubbleSortApp {

    public static void main(String[] args) {
        int maxSize = 100 ; // array size
        ArrayBubble arr = new ArrayBubble(maxSize);
        arr.insert(77);
        arr.insert(99);
        arr.insert(44);
        arr.insert(55);
        arr.insert(22);
        arr.insert(88);
        arr.insert(11);
        arr.insert(66);
        arr.insert(33);
        //打印数组
        arr.display();
        //冒泡排序
        arr.bubbleSort();
        arr.display();
        System.out.println(maxSize);
    }

}
