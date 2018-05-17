package com.nicky.sorts.bubble;

/**
 * @author Nicky_chin  --Created on 2017/5/16
 */
public class ArrayBubble {

    private long[] a; //ref to Array a

    private int nElems; //number of data items

    public ArrayBubble(int max){

        a = new long[max];
        nElems = 0;
    }

    public void insert(long value){
        a[nElems] = value;
        nElems++ ;
    }

    public void display(){
        for (int i = 0; i< nElems; i++){
            System.out.println(a[i]);
        }
    }

    /**
     * 冒泡排序，比较从左往右比较相邻两个元素的大小，将较大的元素交换之右侧，比较之后相邻的两元素，。。。 直到求出最大值
     */
    public void bubbleSort() {
        int out, in;
        for(out = nElems-1;out>1; out--){
            for(in =0; in<out; in++){
                if (a[in] > a[in+1]){
                    swap(in, in+1);
                }


            }

        }
    }

    private  void swap(int left, int right) {
        long temp = a[left];
        a[left] = a[right];
        a[right] = temp;
    }

}
