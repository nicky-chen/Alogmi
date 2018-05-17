package com.nicky.sorts.insert;

/**
 * @author Nicky_chin  --Created on 2017/5/23
 */
public class ArrayIns {
    private long[]a;

    private  int nElemes; //number of data items

    public ArrayIns(int max) {
        a = new long[max];
        nElemes = 0 ;
    }

    public void insert(long value) {
        a[nElemes] = value;
        nElemes ++ ;
    }

    public void display() {
        for (int i = 0; i <nElemes; i++) {
            System.out.println(a[i]);

        }
    }

    /**
     * 插入排序以第一个为标记，第二个与第一个比较，将小的插入左边，大的右移
     * 1|2345
     * 12|345
     * 123|45
     * 1234|5
     * 将竖杠右边的数据与左边数据从右往左比较，大的右移，小的，则插入到小的右边
     */
    public void insertionSort() {
        int in, out ;
        for (out =1; out<nElemes; out++) {
            long temp = a[out];
            in = out;
            while (in>0 && a[in-1] >=temp){
                a[in] = a[in-1];
                --in;
            }
            a[in] = temp;
        }
    }
}
