package com.company;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Tree<T extends Comparable<T>> implements Comparable<Tree<T>> {
    private T data;
    private Tree left;
    private Tree right;
    private int height;
    Tree() {

    }
    public T getData() {
        return data;
    }
    public Tree getLeft() {return left;}
    public Tree getRight() {return right;}
    Tree(T data) {
        this.data = data;
        left = null;
        right = null;
        height=1;
    }

    int getHeight(Tree ntree){
        return ntree!=null? ntree.height:0;
    }

    int balanceFactor(Tree ntree) {
        return getHeight(ntree.right)-getHeight(ntree.left);
    }


    void fixHeight(Tree ntree) {
        int hl = getHeight(ntree.left);
        int hr = getHeight(ntree.right);
        ntree.height = (hl>hr?hl:hr)+1;
    }

    Tree getTree(T data, Tree ntree){
        if(ntree==null)
            return null;
        Tree<T> item = new Tree<>(data);
        if(ntree.compareTo(item)==0)
            return ntree;
        else {
            if(ntree.compareTo(item)<0)
                ntree = ntree.getTree(data, ntree.right);
            else
                ntree = ntree.getTree(data, ntree.left);
        }
        return ntree;
    }

    Tree getTreeByIndex(Tree ntree, int id) {
        return traverseLevelOrder(ntree).get(id);
    }

    Tree add(T data, Tree ntree) {
        if(ntree==null){
            return new Tree<T>(data);
        }
        else{
            Tree<T> item = new Tree(data);
            if(ntree.compareTo(item)<0)
                ntree.right = add(data, ntree.right);
            else
                ntree.left = add(data, ntree.left);

            ntree.fixHeight(ntree);
        }

        return ntree;
    }

    Tree delete(Tree ntree, T data) {
        if(ntree==null)
            return ntree;
        Tree<T> item = new Tree<>(data);
        if(ntree.compareTo(item)==0) {
            Tree temp;
            if(ntree.right==null)
                temp=ntree.left;
            else{
                Tree ptr = ntree.right;
                if(ptr.left==null) {
                    ptr.left=ntree.left;
                    temp=ptr;
                }
                else {
                    Tree pmin = ptr.left;
                    while(pmin.left!=null) {
                        ptr=pmin;
                        pmin=ptr.left;
                    }
                    ptr.left=pmin.right;
                    pmin.left=ntree.left;
                    pmin.right = ntree.right;
                    temp = pmin;
                }
                fixHeight(ntree);
//                ntree=balance(ntree);
                return temp;
            }

            }
        else if(ntree.compareTo(item)>0) {
            ntree.left = delete(ntree.left, data);
            fixHeight(ntree.left);
        }
        else {
            ntree.right = delete(ntree.right, data);
            fixHeight(ntree.right);
        }

        fixHeight(ntree);
//        ntree=balance(ntree);
        return ntree;
    }

    void deleteByIndex(Tree ntree, int id) {
        ntree.delete(ntree, traverseLevelOrder(ntree).get(id).getData());
    }

    void traversePreOrder(Tree ntree) {
        if (ntree != null) {
            System.out.print(" " + ntree.data);
            traversePreOrder(ntree.left);
            traversePreOrder(ntree.right);
        }
    }

     ArrayList<Tree> traverseLevelOrder(Tree ntree) {
        if (ntree == null) {
            return null;
        }
        ArrayList<Tree> listTree = new ArrayList<>();
        Queue<Tree> nodes = new LinkedList<>();
        nodes.add(ntree);
        while (!nodes.isEmpty()) {
            Tree node = nodes.remove();
            listTree.add(node);
            System.out.print(" " + node.data);
            if (node.left != null) {
                nodes.add(node.left);
//                System.out.println("left: " + node.left.data);
            }
            if (node.right!= null) {
                nodes.add(node.right);
//                System.out.println("right: " + node.right.data);
            }
        }
        return  listTree;
    }

    Tree rotateLeft(Tree q) {
        Tree p = q.right;
        q.right = p.left;
        p.left = q;
        fixHeight(q);
        fixHeight(p);
        return p;
    }

    Tree rotateRight(Tree p) {
        Tree q = p.left;
        p.left = q.right;
        q.right = p;
        fixHeight(p);
        fixHeight(q);
        return q;
    }

    Tree balance(Tree p) {
        if(p==null)
            return null;
        fixHeight(p);


        if(balanceFactor(p)>=2){
            while(balanceFactor(p)>=2){
                while (balanceFactor(p.right)<0)
                    p.right=rotateRight(p.right);

                p = rotateLeft(p);
            }
        }

        if(balanceFactor(p)<=-2) {
            while(balanceFactor(p)<=-2) {
                while(balanceFactor(p.left)>0)
                    p.left=rotateLeft(p.left);

                p = rotateRight(p);
            }
        }

        p.left = balance(p.left);
        p.right = balance(p.right);
        return p;
    }

    @Override
    public String toString(){
        String res="";
        res+=data + " ";
        if (left!=null) {
            res += left.toString()+" ";
        }
        if (right!=null) {
            res += right.toString()+" ";
        }
        return res;
    }

    @Override
    public int compareTo(Tree<T> o) {
        return getData().compareTo(o.getData());
    }
}
