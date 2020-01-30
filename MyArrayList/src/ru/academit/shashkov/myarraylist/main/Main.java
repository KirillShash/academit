package ru.academit.shashkov.myarraylist.main;

import ru.academit.shashkov.myarraylist.MyArrayList;

public class Main {
    public static void main(String[] args) {
        MyArrayList<Integer> list1 = new MyArrayList<>();

        list1.add(1);
        list1.add(2);
        list1.add(3);

        MyArrayList<Integer> list2 = new MyArrayList<>();

        list2.add(6);
        list2.add(7);
        list2.add(9);

        System.out.println(list1);

        list1.add(list1.size(), 4);
        System.out.println(list1);

        list1.add(2, 5);
        System.out.println(list1);

        list1.addAll(list2);
        System.out.println(list1);

        list1.addAll(3, list2);
        System.out.println(list1);

        list1.retainAll(list2);
        System.out.println(list1);
    }
}
