package com.luozi.datastructure.tree;

import java.util.ArrayList;
import java.util.List;

public class Tree {

    private List<Node> nodes;
    private List<Node> targets;
    public Tree(List<Node> nodes){
        this.nodes = nodes;
        this.targets = new ArrayList<>();
    }
    public List<Node> getNodes() {
        return nodes;
    }

    public void setNodes(List<Node> nodes) {
        this.nodes = nodes;
    }

    public List<Node> getTargets() {
        return targets;
    }

    public void setTargets(List<Node> targets) {
        this.targets = targets;
    }

    public List<Node> getChildren(Node node){
        List<Node> children = new ArrayList<>();
        for(Node n:nodes){
            if(n.getPid() == node.getId()){
                List<Node> mychildren =  this.getChildren(n);
                n.setChildren(mychildren);
                children.add(n);
            }
        }
        return children;
    }
    public void buildTree(){
        for(Node n:nodes){
            if(n.getPid()==0){
                List<Node> children = this.getChildren(n);
                n.setChildren(children);
                targets.add(n);
            }
        }
    }

}
