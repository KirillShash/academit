package ru.academit.shashkov.myhashtable.main;

import ru.academit.shashkov.myhashtable.MyHashTable;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        MyHashTable<Integer> hashTable = new MyHashTable<>();

        hashTable.add(1);
        hashTable.add(2);
        hashTable.add(3);

        System.out.println(hashTable);
        System.out.println(hashTable.contains(3));

        ArrayList<Integer> list = new ArrayList<>();

        list.add(6);
        list.add(11);
        list.add(3);
        list.add(15);
        list.add(22);

        hashTable.addAll(list);
        System.out.println(hashTable);

        hashTable.retainAll(list);
        System.out.println(hashTable);
    }
}
