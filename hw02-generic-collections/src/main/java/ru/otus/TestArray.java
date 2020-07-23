package ru.otus;

import java.util.*;

public class TestArray {

    public static void main(String[] args) {

        Random random = new Random();
        List<Integer> sourceList = new DIYArrayList<>();
        List<Integer> sourceList2 = new DIYArrayList<>();
        for (int i = 0; i < 100; i++) {
            sourceList.add(random.nextInt());
            sourceList2.add(random.nextInt());
        }
        Integer[] array = sourceList.toArray(new Integer[0]);

        List<Integer> test = new DIYArrayList<>();
        Collections.addAll(test, array);
        System.out.println("test \"Collections.addAll\": ");
        test.forEach(System.out::println);

        List<Integer> test2 = new DIYArrayList<>(sourceList2);
        Collections.copy(test2, test);
        System.out.println("test \"Collections.copy\": ");
        for (Integer num : test2) {
            System.out.println(num);
        }


        Collections.sort(test2, Integer::compare);
        System.out.println("test \"Collections.sort\": ");
        for (Integer num : test2) {
            System.out.println(num);
        }
    }

}
