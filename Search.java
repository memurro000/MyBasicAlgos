package com.murro.myba;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;
import java.util.ArrayDeque;

//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.List;

public class Search{

    private static class Util{

        public static 
        <T extends Comparable<? super T>>
        boolean isGreater(ArrayList<T> container,int ia, T b){
            return container.get(ia).compareTo(b) > 0;
        }

    };


    //For sorted containers only
    public static
    <T extends Comparable<? super T>>
    Optional<T> binary(final ArrayList<T> container, final T v){

        int l = 0, r = container.size();

        for(int m = 0; l < r; m = l + (r - l) / 2){
            if(Util.isGreater(container, m, v)) r = m - 1;
            else if(container.get(m) == v) return Optional.of(container.get(m));
            else l = m + 1;
        }

        return Optional.empty();
    }

    public static
    <T>
    Optional<Graph<T>.Node<T>> breadth(final Graph<T>.Node<T> begin, final T val){
        ArrayDeque<Graph<T>.Node<T>> q = new ArrayDeque<>();
        HashSet<Graph<T>.Node<T>> passed = new HashSet<>();
        q.add(begin);

        Graph<T>.Node<T> current;

        while(!q.isEmpty()) {
            current = q.remove();
            if(current.getValue().equals(val)){
                return Optional.of(current);
            } else {
                passed.add(current);
                q.addAll(current.getNeighbors());
                q.removeAll(passed);

            }
        }

        return Optional.empty();
    }

    public static
    <T>
    Optional<Graph<T>.Node<T>> depth(final Graph<T>.Node<T> begin, T val){
        ArrayDeque<Graph<T>.Node<T>> q = new ArrayDeque<>();
        HashSet<Graph<T>.Node<T>> passed = new HashSet<>();
        q.add(begin);
        Graph<T>.Node<T> current;

        while(!q.isEmpty()){
            current = q.remove();
            if(current.getValue().equals(val)){
                return Optional.of(current);
            } else {
                passed.add(current);
                for(var x : current.getNeighbors())
                    if(!passed.contains(x)){q.add(x); break;}
                q.removeAll(passed);
            }
        }

        return Optional.empty();
    }
};












