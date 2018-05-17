package com.nicky.tree.btree;

/**
 * @author nicky_chin [shuilianpiying@163.com]
 * @since --created on 2018/5/16 at 17:06
 */
public class BTree <K extends Comparable<K>> {

    //比如度为T的节点，根为1个节点，第二层至少为2个节点，第三层至少为2t个节点，第四层至少为2t*t个节点
    private final int degree;
    private AbstractBTreeNode<K> root; //根节点

    public BTree(int degree){
        if (degree < 2){
            throw new IllegalArgumentException("degree mustn't < 2");
        }
        this.degree = degree;
        //默认为叶子节点
        root = new BTreeLeaf<>(degree);
    }

    /**
     * 获取根节点
     */
    public AbstractBTreeNode<K> getRoot(){
        return root;
    }
    /**
     * Insert a key into B-Tree.
     *
     * @param key key to insert.
     */
    public void insert(K key){
        AbstractBTreeNode<K> n = root;
        if (root.isFull()){ //当前节点关键字个数是否到达最大值
            AbstractBTreeNode<K> newRoot = new BTreeInternalNode<>(degree);
            newRoot.insertChild(n,0);
            newRoot.splitChild(0); //分裂子节点逻辑
            n = newRoot;
            root = newRoot;
        }
        //当前节点关键字个数未达到最大
        n.insertNotFull(key);
    }

    /**
     * Delete a key from B-Tree,if key doesn't exist in current tree,will effect nothing.
     *删除key
     * @param key key to delete.
     */
    public void delete(K key){
        AbstractBTreeNode<K> node = root;
        node.deleteNotEmpty(key);
        if (node.nkey() == 0){
            //shrink
            root = node.getChild(0);
            if (root == null){
                root = new BTreeLeaf<>(degree);
            }
        }
    }

    @Override
    public String toString() {
        return AbstractBTreeNode.BTreeToString(this.root);
    }
}
