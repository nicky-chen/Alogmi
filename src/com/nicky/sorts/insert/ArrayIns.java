package com.nicky.sorts.insert;

/**
 * @author Nicky_chin  --Created on 2017/5/23
 * 常规插入排序
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
        int in, out;
        for (out = 1; out < nElemes; out++) {
            long temp = a[out];
            in = out;
            //寻找temp应该插入的位置
            while (in > 0 && a[in - 1] >= temp) {
                a[in] = a[in - 1];
                --in;
            }
            a[in] = temp;
        }
    }

    /**
     * 折半插入排序
     *
     * 直接插入排序算法中，当第i-1趟需要将第i个元素插入到0~i-1个元素之间时，
     * 总是需要从第i-个元素开始，依次与前面的每一个元素进行比较，直至找到合适的位置进行插入。
     * 这样比较的过程显然没有利用到0~i-1个元素已经有序这一有效信息，折半插入排序则对此进行了改进。
     * 当需要将第i个元素插入到先前有序列表中去时，折半排序的做法是：
     *
     * 首先选取0~i-1个有序列表元素的中间元素，即位置为(i-1)/2的元素，与需要插入的第i个元素进行比较，
     * 如果位置为(i-1)/2处的元素大于第i个元素，则在位置区间为0~(i-1)/2-1的范围内进行搜索，
     * 反之则在(i-1)/2+1~i-1的范围内进行搜索。以上过程即是折半的思想；
     * 在以上半个范围内进行搜索时，可以重复1中的搜索方法，这样即把搜索区间逐渐缩小为1/4，1/8等，
     * 从而较快的定位合适的插入位置。
     */
    public void binaryInsertionSort() {

        for (int i = 1; i < nElemes; i++) {
            long temp = a[i];
            int low = 0;
            int high = i - 1;

            while (low <= high) {
                //折半查找
                int mid = (low + high) >>> 1;
                if (temp > a[mid]) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }

            }

            //找到插入位置后已排序元素向右偏移
            for (int j = i; j > low; j--) {
                a[j] = a[j - 1];
            }
            a[low] = temp;

        }


    }


}
