package com.luozi.datastructure.tree;

import java.util.ArrayList;
import java.util.List;

public class TestTree {


    public static void main(String[] args) {
        List<Node> nodes = new ArrayList<Node>();
        nodes.add(new Node(1,"language",0));
        nodes.add(new Node(2,"java",1));
        nodes.add(new Node(3,"javascript",1));
        nodes.add(new Node(4,"css",1));
        nodes.add(new Node(5,"book",0));
        nodes.add(new Node(6,"js",5));
        nodes.add(new Node(7,"jquery",5));
        nodes.add(new Node(8,"jquery",3));
        nodes.add(new Node(9,"c#",1));
        nodes.add(new Node(10,"android",2));
        nodes.add(new Node(11,"listview",10));
        nodes.add(new Node(12,"arrayadapter",11));
        Tree tree = new Tree(nodes);
        tree.buildTree();
        printTree(tree.getTargets());
    }

    public static void printTree(List<Node> tree){
        for(Node n:tree){
            System.out.println(n.toString());
            List<Node> cs = n.getChildren();
            if(!cs.isEmpty()){
                printTree(cs);
            }
        }
    }
}
