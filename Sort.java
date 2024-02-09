package com.murro.myba;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Sort
{

    private static class Util{

        public static 
        <T extends Comparable<? super T>>
        boolean isGreater(List<T> container, int ia, int ib){
            return container.get(ia).compareTo(container.get(ib)) > 0;
        }

        public static 
        <T extends Comparable<? super T>>
        void remerge(ArrayList<T> container, int isub_begin, int isub_end, int islice){

            ArrayList<T> sub_array = new ArrayList<T>(container.subList(isub_begin, isub_end));
            int len = sub_array.size();
            int l = 0, r = islice, i = isub_begin;

            while(l < islice && r < len){
                if(isGreater(sub_array, r, l)) {container.set(i, sub_array.get(l)); ++i; ++l;}
                else                                {container.set(i, sub_array.get(r)); ++i; ++r;}
            }

            while(l < islice) {container.set(i, sub_array.get(l)); ++i; ++l;}
            while(r < len)    {container.set(i, sub_array.get(r)); ++i; ++r;}

            return;
        }

    };



    public static
    <T extends Comparable<? super T>> 
    void bubble(ArrayList<T> container){

        boolean swap = true;

        while(swap){
            swap = false;
            for(int i = 0; i < container.size() - 1; ++i)
                if(container.get(i).compareTo(container.get(i + 1)) > 0){
                    Collections.swap(container, i, i + 1);
                    swap = true;
                }
        }

        return;
    } 

    public static
    <T extends Comparable<? super T>>
    void selection(ArrayList<T> container){

        int len = container.size() - 1;
        int iselected;

        for(int ifirst = 0; ifirst < len; ++ifirst){
            iselected = ifirst;
            for(int isearch = 0; isearch < len; ++isearch)
                if(Util.isGreater(container, iselected, isearch)) iselected = isearch;
            Collections.swap(container, ifirst, iselected);
        }
    
        return;
    }

    public static 
    <T extends Comparable<? super T>>
    void insertion(ArrayList<T> container){

        int len = container.size() - 1;

        for(int ifirst = 1; ifirst <= len; ++ifirst){
            for(int itaken = ifirst - 1; itaken >= 0 && Util.isGreater(container, itaken, itaken + 1); --itaken){
                Collections.swap(container, itaken, itaken + 1);
            }
        }

        return;
    }

    public static 
    <T extends Comparable<? super T>>
    void merge(ArrayList<T> container){
        
        int len = container.size();
        
        for(int isub_size = 2; isub_size < len; isub_size *= 2)
            for(int isub_begin = 0; isub_begin < len; isub_begin += isub_size)
                Util.remerge(container, isub_begin,
                           isub_begin + isub_size < len ?
                           isub_begin + isub_size : len,
                        isub_size / 2);
        Util.remerge(container, 0, len, len / 2);

        return;
    }


}









