package com.example.project1;

import java.util.Arrays;
import java.util.Comparator;

public class MyList <E extends Comparable<E>>{
    E[] list;
    int index = 0;

    public MyList(int capacity) {
        list =  (E[]) new Comparable[capacity];
    }

    public MyList(MyList<E> list) {
        this.list =  (E[]) new Comparable[list.size()];
        for (int i = 0; i < list.size(); i++){
            add(list.get(i));
        }
    }

    public E get(int index) {
        if (index < this.index)
            return list[index];
        else
            System.out.println("This element is out of bounds");
        return null;
    }

    public void print() {
        for (int i = 0; i < index ; i++) {
            System.out.println(list[i]);
        }
    }

    public void add(E element) {
        if (index < list.length)
            list[index++] = element;
        else {
            E[] temp = (E[]) new Comparable[list.length];
            for (int i = 0; i < list.length; i++) {
                temp[i] = list[i];
            }
            list = (E[]) new Comparable[list.length + 1];
            for (int i = 0; i < list.length - 1; i++) {
                list[i] = temp[i];
            }
            list[list.length - 1] = element;
            index++;
        }
    }

    public void remove(int index) {
        if (index < this.index) {
            for (int i = index; i < this.index-1; i++) {
                list[i] = list[i+1];
            }
            this.index--;
        }
        else
            System.out.println("Error: there is no "+ index +" element");
    }

    public void remove(E element) {
        int index = 0;
        boolean found = false;
        for (int i = 0; i < this.index; i++) {
            if (list[i].equals(element)) {
                index = i;
                found = true;
                break;
            }
        }
        if (index < this.index && found) {
            for (int i = index; i < this.index-1; i++) {
                list[i] = list[i+1];
            }
            this.index--;
        }
        else
            System.out.println("Error: there is no "+ index +" element");
    }

    public void clear() {
        this.index = 0;
    }

    public int size() {
        return index;
    }

    public void sort() {
        Arrays.sort(list, 0, index);
    }

    public void sort(Comparator<E> comparator) {
        Arrays.sort(list, 0, index, comparator);
    }

}
