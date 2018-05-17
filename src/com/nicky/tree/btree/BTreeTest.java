package com.nicky.tree.btree;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * @author nicky_chin [shuilianpiying@163.com]
 * @since --created on 2018/5/16 at 17:07
 */
public class BTreeTest {

    private static final int INITIAL_CAPACITY = 12;

    private final List<Integer> keys = new ArrayList<>(INITIAL_CAPACITY);

    private final List<List<Integer[]>> insertResults = new ArrayList<>(INITIAL_CAPACITY);

    private final List<List<Integer[]>> deleteResults = new ArrayList<>(INITIAL_CAPACITY);

    public void before() {
        int[] ks = new int[]{6, 18, 16, 22, 3, 12, 8, 10, 20, 21, 13, 17};
        for (int i = 0; i < ks.length; i++) {
            keys.add(i, ks[i]);
        }
    }


    public void after() {
        keys.clear();
        insertResults.clear();
    }

    public static void main(String[] args) throws Exception {
        BTreeTest test = new BTreeTest();
        test.before();

        //插入操作
        test.testInsert();
        System.out.println("=====================");
        //删除操作
        test.testDelete();

        test.after();


    }


    /**
     * Method: insert(K key)
     */
    public void testInsert() throws Exception {
        BTree<Integer> tree = new BTree<>(2);
        fillInsertResult();
        for (int i = 0; i < keys.size(); i++) {
            tree.insert(keys.get(i));
            if (!checkBTree(tree, insertResults.get(i))) {
                throw new IllegalArgumentException("插入预期error");
            }
        }
        System.err.println("root :" + tree.getRoot());
        System.out.println(tree.toString());
    }

     /**
     * Method: delete(K key)
     */
    public void testDelete() throws Exception {
        BTree<Integer> bTree = new BTree<>(2);
        for (int key : keys) {
            bTree.insert(key);
        }
        fillDeleteResult();

        for (int i = 0; i < keys.size(); i++) {
            bTree.delete(keys.get(i));
            checkBTree(bTree, deleteResults.get(i));
        }
    }

    /**
     * 预期结果比较
     */
    private <K extends Comparable<K>> boolean checkBTree(BTree<K> tree, List<K[]> result)
            throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        AbstractBTreeNode<K> node = tree.getRoot();
        Queue<AbstractBTreeNode<K>> nodes = new LinkedList<>();

        Class<?> clazz = node.getClass();
        Method nChild = clazz.getDeclaredMethod("nchild", null);//int com.nicky.tree.btree.BTreeLeaf.nchild()
        Method getChild = clazz.getDeclaredMethod("getChild", int.class);//getChild(int)
        Method nKey = clazz.getDeclaredMethod("nkey", null);
        Method getKey = clazz.getDeclaredMethod("getKey", int.class);
        nChild.setAccessible(true);
        getChild.setAccessible(true);
        nKey.setAccessible(true);
        getKey.setAccessible(true);

        int nodeIndex = 0;
        while (node != null) {
            //add children
            int nchild = (Integer) nChild.invoke(node, null);
            for (int i = 0; i < nchild; i++) {
                Object n = getChild.invoke(node, nchild);
                if (n != null) {
                    nodes.offer((AbstractBTreeNode<K>) n);
                }
            }
            K[] nodeKeys = result.get(nodeIndex++);
            //compare keys
            for (int i = 0; i < nodeKeys.length; i++) {
                if (!getKey.invoke(node, i).equals(nodeKeys[i])) {
                    return false;
                }
            }
            node = nodes.poll();
        }
        return true;
    }

    private void fillInsertResult() {

        //b树按keys的值插入，正常流程下的节点情况
        Integer[][][] res = {
                {{6}},                                                            // 插入6
                {{6, 18}},                                                        // 插入18
                {{6, 16, 18}},                                                    // 插入16
                {{16}, {6}, {18, 22}},                                            // 插入22 分裂
                {{16}, {3, 6}, {18, 22}},                                         // 3
                {{16}, {3, 6, 12}, {18, 22}},                                     // 12
                {{6, 16}, {3}, {8, 12}, {18, 22}},                                // 8
                {{6, 16}, {3}, {8, 10, 12}, {18, 22}},                            // 10
                {{6, 16}, {3}, {8, 10, 12}, {18, 20, 22}},                        // 20
                {{6, 16, 20}, {3}, {8, 10, 12}, {18}, {21, 22}},                  // 21
                {{16}, {6, 10}, {20}, {3}, {8}, {12, 13}, {18}, {21, 22}},        // 13
                {{16}, {6, 10}, {20}, {3}, {8}, {12, 13}, {17, 18}, {21, 22}},    // 17
        };

        for (int i = 0; i < 12; i++) {
            insertResults.add(Arrays.asList(res[i]));
        }
    }

    private void fillDeleteResult() {
        //删除操作预期效果
        //tree values after insert keys
        Integer[][][] res = {
                {{16}, {10}, {20}, {3, 8}, {12, 13}, {17, 18}, {21, 22}},                  //del 6
                {{10, 16, 20}, {3, 8}, {12, 13}, {17}, {21, 22}},                          //del 18
                {{10, 13, 20}, {3, 8}, {12}, {17}, {21, 22}},                              //del 16
                {{10, 13, 20}, {3, 8}, {12}, {17}, {21}},                                  //del 22
                {{10, 13, 20}, {8}, {12}, {17}, {21}},                                     // 3
                {{13, 20}, {8, 10}, {17}, {21}},                                           // 12
                {{13, 20}, {10}, {17}, {21}},                                              // 8
                {{20}, {13, 17}, {21}},                                                    // 10
                {{17}, {13}, {21}},                                                        // 20
                {{13, 17}},                                                                // 21
                {{17}},                                                                    // 13
                {{}},                                                                      // 17
        };

        for (int i = 0; i < 12; i++) {
            deleteResults.add(Arrays.asList(res[i]));
        }
    }




}
