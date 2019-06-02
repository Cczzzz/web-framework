package com.cc.learn.redblacktree;

import lombok.Data;

/**
 * Created by chenchang on 2018/11/22.
 */
abstract class Node<T> implements Comparable<T> {

    private boolean isRed; //isRed
    T key; //关键字(键值)

    Node<T> left; //左子节点

    Node<T> right; //右子节点

    Node<T> parent; //父节点

    public boolean isRed() {
        return isRed;
    }

    public void setRed(boolean red) {
        isRed = red;
    }

    public T getKey() {
        return key;
    }

    public void setKey(T key) {
        this.key = key;
    }

    public Node<T> getLeft() {
        return left;
    }

    public void setLeft(Node<T> left) {
        this.left = left;
    }

    public Node<T> getRight() {
        return right;
    }

    public void setRight(Node<T> right) {
        this.right = right;
    }

    public Node<T> getParent() {
        return parent;
    }

    public void setParent(Node<T> parent) {
        this.parent = parent;
    }

    static class Tree<T> {

        Node<T> root;

        public Tree() {
        }

        public Tree(Node<T> root) {
            this.root = root;
        }

        public void put(Node<T> node) {
            node.setRed(true);
            if (this.root == null) {
                root = node;
            }

        }


        public void changeColor(Node<T> node) {
            if (node.getParent().isRed()) {

            }
        }

    }


}
