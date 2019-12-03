package ru.academit.shashkov.arraylisthome;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class ArrayListHome {
    public static void main(String[] args) throws FileNotFoundException {
        ArrayList<Integer> list = new ArrayList<>();

        try (Scanner scanner = new Scanner(new FileInputStream("file.txt"))) {
            while (scanner.hasNextLine()) {
                list.add(scanner.nextInt());
            }

            System.out.println(list);

            deleteEvenNumbers(list);

            System.out.println(list);

            System.out.println(removeReplays(list));
        }
    }

    private static void deleteEvenNumbers(ArrayList<Integer> list) {
        list.removeIf(i -> i % 2 == 0);
    }

    private static ArrayList<Integer> removeReplays(ArrayList<Integer> list1) {
        ArrayList<Integer> list2 = new ArrayList<>();

        for (var e : list1) {
            if (list2.contains(e)) {
                continue;
            }

            list2.add(e);
        }

        return list2;
    }
}
