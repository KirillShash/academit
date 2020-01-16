package ru.academit.shashkov.mylist.main;

import ru.academit.shashkov.mylist.MyList;

public class Main {
    public static void main(String[] args) {
        MyList<String> line = new MyList<>();

        line.addHead("1");
        line.addHead("2");
        line.addHead("3");

        System.out.println("Список: " + line);
        System.out.println("Длина = " + line.getSize());
        System.out.println(line.getData(2));
        System.out.println("Значение первого элемента = " + line.getHeadData());
        System.out.println(line.setData("3", 2));

        System.out.println();

        line.add("5", 2);
        line.add("4", 2);

        System.out.println("Новый список = " + line);
        System.out.println("Длина = " + line.getSize());

        System.out.println();

        System.out.println(line.remove("6"));
        System.out.println(line.remove("5"));
        System.out.println("Удаленное значение = " + line.remove(2));
        System.out.println("Длина = " + line.getSize());

        System.out.println();

        System.out.println(line);

        System.out.println();

        MyList<String> line2 = new MyList<>();

        line2.addHead("1");
        line2.addHead("2");
        line2.addHead("3");

        System.out.println("Новый список: " + line2);
        line2.invert();
        System.out.println("Новый список после разворота: " + line2);

        System.out.println();

        MyList<String> line3 = new MyList<>();

        line3.addHead("5");
        line3.addHead("8");
        line3.addHead("10");
        line3.addHead("11");
        line3.addHead("22");
        line3.addHead("31");

        System.out.println("Список для копирования: " + line3);
        System.out.println("Результат копирования: " + line3.copy());
    }
}
