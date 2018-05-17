package com.nicky.sorts.select;

/**
 * 选择排序 比较次数 O(N^2) 交换次数为 O(N)
 * @author Nicky_chin  --Created on 2017/5/17
 */
class SelectSortApp {

    public static void main(String[] args) {

        int maxSize = 100; // array size
        ArraySel arr = new ArraySel(maxSize);
        arr.insert(77);
        arr.insert(99);
        arr.insert(44);
        arr.insert(55);
        arr.insert(22);
        arr.insert(88);
        arr.insert(11);
        arr.insert(66);
        arr.insert(33);
        arr.selectinSort();
        arr.display();

    }

}
