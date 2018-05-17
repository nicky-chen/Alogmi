package com.nicky.hash;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

/**
 * @author Nicky_chin  --Created on 2017/7/24
 *         根据以上hash函数计算数组下标时，当遇到数据存放的冲突时就需要重新找到数组的其他位置。
 *         关于开放地址法通常需要有三种方法：线性探测、二次探测、再哈希法。
 */
public class HashTableLiner {

    //创建一个ItemData数组
    ItemData[] hashArray;

    int arraySize;

    ItemData nonItem;

    //构造函数确定hashTable的数组大小
    public HashTableLiner(int size) {
        hashArray = new ItemData[size];
        arraySize = size;
        nonItem = new ItemData(-1);//初始化
    }

    //显示该hashTable
    public void displaHashTable() {
        System.out.println("hashTable");
        for (int i = 0; i < arraySize; i++) {
            if (hashArray[i] != null)//hashTable中有数据的位置输出数据，空位置则输出***标志
                System.out.println(hashArray[i].getKey() + " ");
            else
                System.out.print("*" + "");
        }

    }

    //hash函数，采用取余将大范围的数字转化为小范围的数字
    public int hashFunction(int key) {
        return key % arraySize;
    }

    //向hashTable中插入数据
    public void insert(ItemData data) {
        int key = data.getKey();
        int hashVal = hashFunction(key);
        while (hashArray[hashVal] != null && hashArray[hashVal].getKey() != -1) {
            ++hashVal;//往后搜索
            hashVal %= arraySize;
        }
        hashArray[hashVal] = data;
    }

    //从hashTable中删除一个dataItem
    public ItemData delete(int key) {
        int hashVal = hashFunction(key);
        while (hashArray[hashVal] != null) {
            if (hashArray[hashVal].getKey() == key) {
                ItemData temp = hashArray[hashVal];
                //删除采用做删除标志的方法进行标示，之前在二叉树复杂删除中也应用到了
                hashArray[hashVal] = nonItem;
                return nonItem;
            }
            ++hashVal;
            hashVal %= arraySize;//执行下一次删除
        }
        return null;//没找到就是直接返回null
    }

    //在hashTable中查找数据
    public ItemData find(int key)
    {
        int hashVal=hashFunction(key);
        while(hashArray[hashVal]!=null)
        {
            if(hashArray[hashVal].getKey()==key)
            {
                return hashArray[hashVal];
            }
            ++hashVal;
            hashVal%=arraySize;
        }
        return null;//没有找到直接返回null
    }

    public static void main(String[] args) {
        HashTableLiner hash = new HashTableLiner(30);
        ItemData a1 = new ItemData(10);
        ItemData a2 = new ItemData(20);
        ItemData a3 = new ItemData(30);
        ItemData a4 = new ItemData(40);
        ItemData a5 = new ItemData(50);
        ItemData a6 = new ItemData(60);
        ItemData a7 = new ItemData(70);
        ItemData a8 = new ItemData(80);
        ItemData a9 = new ItemData(90);
        hash.insert(a1);
        hash.insert(a2);
        hash.insert(a3);
        hash.insert(a4);
        hash.insert(a5);
        hash.insert(a6);
        hash.insert(a7);
        hash.insert(a8);
        hash.insert(a9);
        hash.displaHashTable();

        System.out.println("删除数据20");
        hash.delete(20);
        System.out.println("删除20之后的hashTable：");
        hash.displaHashTable();

        System.out.println("在hashTable中查找是否有30这个关键字：");
        ItemData data=hash.find(30);
        if(data.getKey()==30)
            System.out.println("find it");
        else
            System.out.println("The hashTable does  not have the key.");

        Map<String, String> map = new HashMap<>(5);
    }

}

//定义数据属性以及返回当前数据
class ItemData {

    private int data;

    public ItemData(int data) {
        this.data = data;
    }

    public int getKey() {
        return data;
    }
}
