package com.nicky.link;

import javax.xml.bind.SchemaOutputResolver;

/**
 * @author Nicky_chin  --Created on 2017/7/22
 *         双向链表与实现
 */
public class LinkedDoubleList {

    /*第一个节点*/
    private Node first;

    /*最后一个节点*/
    private Node last;

    /*大小*/
    private int size;

    /**
     * 获取这个链表的大小（元素的个数）
     */
    public int size() {
        return size;
    }

    /**
     * 节点类
     */
    class Node {

        /* 表示上一个节点*/ Node prev;

        /*表示下一个节点*/ Node next;

        /*当前节点的元素*/ Object element;

        public Node() {
        }

        public Node(Node prev, Node next, Object element) {
            this.prev = prev;
            this.next = next;
            this.element = element;
        }

    }

    /**
     * 这个方法是从LinkedList.node(index)方法中复制过来的，稍加修改
     * 用于返回指点下标处的节点
     */
    private Node node(int index) {
        /*
         * 打个比方：
         * size = 6;
         * size >> 1 = 3
         * 如果index小于3的话，就从第一个找到最后一个
         * 如果index大于3的话，就从最后一个找到第一个
         * 下面代码亦是如此
         */
        if (index < (size >> 1)) {
            Node x = first;
            for (int i = 0; i < index; i++)
                x = x.next;
            return x;
        } else {
            Node x = last;
            for (int i = size - 1; i > index; i--)
                x = x.prev;
            return x;
        }
    }

    /**
     * 增加一个节点
     *
     * @param obj 要增加的元素
     */
    public void addFirst(Object obj) {
        Node temp = new Node();//新的节点
        /*新节点的元素赋值*/
        temp.element = obj;

        if (first == null) {//如果第一个节点是空的，那就是没有节点
            //这个节点既然是第一个节点，所以节点的prev点和next都是空的，所以，不用赋值
            //同理，这个新插入的节点是第一个，也是最后一个
            first = temp;
            last = temp;
        } else {//否则，那就意味着这个节点不是空的。
            temp.next = first;
            first.prev = temp;
            first = temp;
        }
        //插入成功size++;
        size++;
    }

    /**
     * 增加一个节点，指定位置
     *
     * @param index 下标
     * @param obj obj
     */
    public void add(int index, Object obj) {
        Node temp = node(index);//得到的节点
        Node newNode = new Node();//新的节点
        newNode.element = obj;

        if (temp != null) {//如果得到的指定节点不是空的话
            //得到temp的上一个节点
            Node tempPrev = temp.prev;

            //tempPrev的下一个节点赋值为newNode
            tempPrev.next = newNode;
            //同时，newNode的上一个节点赋值为tempPrev
            newNode.prev = tempPrev;

            //然后newNode的下一个节点便是这个一开始就指定的temp节点
            newNode.next = temp;
            //temp的上一个节点赋值为newNode
            //这样在指定元素之前插入了一个新的元素
            temp.prev = newNode;
        }
        size++;
    }

    /**
     * 删除
     * @param index
     */
    public void remove(int index) {
        /*
         * 删除...
         * 有 a b c三个元素
         * a的下一个节点是b b的下一个节点是c
         * c的上一个节点是b b的上一个节点是a
         * --
         * 比如删除了b
         * 那就要把a 和 c 连接起来。
         *
         * 连接好了后，就是：
         * a 下一个节点是 c
         * c 上一个节点是 a
         *
         */

        Node temp = node(index);//得到指定下标的元素
        if (temp != null) {
            /*

            //得到temp的上一个节点
            Node tempPrev = temp.prev;
            //得到temp的下一个节点
            Node tempNext = temp.next;
            //tempPrev的下一个节点是tempNext
            tempPrev.next = tempNext;
            //而tempNext的上一个节点就是tempPrev
            tempNext.prev = tempPrev;

            */

            //temp的上一个节点的下一个节点就是temp的下一个节点
            temp.prev.next = temp.next;
            //temp的下一个节点的上一个节点就是temp的上一个节点
            temp.next.prev = temp.prev;
        }
        size--;
    }

    public void display() {
        System.out.print("first -> last : ");
        Node data = first;
        while (data != null) {
            System.out.print(data.element.toString() + " -> ");
            data = data.next;
        }
        System.out.print("\n");
    }

    /**
     * 根据下标获取元素
     *
     * @param index 元素的索引
     */
    public Object get(int index) {
        return node(index).element;//得到指定节点的元素
    }

    /*------------------------------------------------------------*/
    public static void main(String[] args) {
        LinkedDoubleList list = new LinkedDoubleList();
        list.addFirst("a");
        list.addFirst("b");
        list.addFirst("c");
        list.display();
        list.add(1, "B");
        list.remove(1);
        System.out.println(list.get(1));
        System.out.println("当前链表的大小：" + list.size());
    }
}
