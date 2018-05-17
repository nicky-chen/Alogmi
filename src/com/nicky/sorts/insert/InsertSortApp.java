package com.nicky.sorts.insert;

/**
 * @author Nicky_chin  --Created on 2017/5/23
 * 插入排序效率 》 选择派寻 》 冒泡排序
 * 插入排序在部分数据有序情况下快，如果是逆序排列，不比冒泡排序快
 * 插入排序 比较的次数 为 O(n^2)
 */
public class InsertSortApp {

    public static void main(String[] args) {
        int maxSize = 100;
        ArrayIns arr = new ArrayIns(maxSize);

        arr.insert(77);
        arr.insert(99);
        arr.insert(44);
        arr.insert(55);
        arr.insert(22);
        arr.insert(88);
        arr.insert(11);
        arr.insert(66);
        arr.insert(33);

        arr.display();

        arr.insertionSort();
        System.out.println("===================");
        arr.display();
    }

}
