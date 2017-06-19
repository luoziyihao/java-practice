package com.luozi.datastructure.tree;

import java.util.ArrayList;
import java.util.List;

public class Node {

    private int id;
    private String name;
    private int pid;
    private List<Node> children;
    public Node(){

    }
    public Node(int id,String name,int pid){
        this.id = id;
        this.name = name;
        this.pid = pid;
        this.children = new ArrayList<Node>();
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getPid() {
        return pid;
    }
    public void setPid(int pid) {
        this.pid = pid;
    }
    public List<Node> getChildren() {
        return children;
    }
    public void setChildren(List<Node> children) {
        this.children = children;
    }
    @Override
    public String toString() {
        return "Node [id=" + id + ", name=" + name + ", pid=" + pid;
    }
}
