package com.murro.myba;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Graph<T>{
    public class Node<T>{
        private T val;
        private HashSet<Node<T>> neighbors;

        public Node(T val){
            this.val = val;
            this.neighbors = new HashSet<>();
            return;
        }

        public T getValue(){
            return this.val;
        }

        public HashSet<Node<T>> getNeighbors(){
            return neighbors;
        }

        @Override
        public String toString(){
            String out = new String();
            out = this.val.toString();
            return out;
        }

        public String toStringNeighbors(){
            return neighbors.toString();
        }

        public String toStringWithNeighbors(){
            return toString() + "\t" + toStringNeighbors();
        }
    }

    private HashMap<T, Node<T>> nodes;

    public Graph(){
        this.nodes = new HashMap<>();
        return;
    }

    public Graph(ArrayList<T> values){
        this.nodes = new HashMap<>();
        for(int i = 0; i < values.size(); ++i)
            nodes.put(values.get(i), new Node<T>(values.get(i)));
        return;
    }

    public Node<T> getByKey(T key){
        return nodes.get(key);
    }

    public void connect(T key1, T key2){
        if(key1 == key2) throw new IllegalArgumentException("Cannot connect the node to itself");
        if(!nodes.containsKey(key1)) throw new IllegalArgumentException("No available node containing key " + key1.toString());
        if(!nodes.containsKey(key2)) throw new IllegalArgumentException("No available node containing key " + key2.toString());
        Node<T> node1 = nodes.get(key1), node2 = nodes.get(key2);
        node1.neighbors.add(node2);
        node2.neighbors.add(node1);
        return;
    }

    public void connectAll(){
        for(var x : nodes.keySet())
            for(var y : nodes.keySet()){
                try{
                    connect(x, y);
                }catch(IllegalArgumentException ex){}
            }
        return;
    }


    @Override
    public String toString(){
        String out = new String();
        out = "/\tG r a p h\n";
        for(var x : nodes.entrySet()) out += x.getValue().toStringWithNeighbors() + "\n";
        out +="G r a p h\t/\n";
        return out;
    }


}













