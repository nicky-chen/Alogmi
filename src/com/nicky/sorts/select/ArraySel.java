package com.nicky.sorts.select;

/**
 * @author Nicky_chin  --Created on 2017/5/17
 */
public class ArraySel {

    private long[] a; //ref to array a

    private  int nElems; //number of data items

    public ArraySel(int max) {
        a = new long[max];
        nElems = 0;
    }

    public void insert(long value) {
        a[nElems] = value;
        nElems++;
    }

    public void display() {
        for (int i = 0; i <nElems ; i++) {
            System.out.println(a[i]);
        }
    }

    /**
     * 选择排序，从左往右，第一次将第一个元素标记为最小值，并做记录，然后，让之后的元素与第一个比，将较小的元素
     * 下标存入到一个记录中，当第一次循环结束的时候，交换位置，将最小的元素放入左边第一位
     */
    public void selectinSort() {
        int out, in, min;
        for (out = 0; out <nElems ; out++) {
            min = out;
            for (in = out+1; in <nElems ; in++) {
                if(a[in]< a[min]) {
                    min = in;
                }
            }
            swap(out,min);
        }
    }

    public void swap(int out, int min) {
        long temp = a[out];
        a[out] = a[min];
        a[min] = temp;
    }

}
