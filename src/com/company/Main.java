package com.company;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

public class Main {

    static void showArray(MyArrayList myArrayList) {
        for (Object value : myArrayList) {
            System.out.print(value + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        MyArrayList<Integer> arList = new MyArrayList<>();
        Random random = new Random(41);
        System.out.println("Is empty? : " + arList.isEmpty());
        //add
        for (int i = 0; i < 20; i++) {
            arList.add(random.nextInt(100));
        }
        //isEmpty
        System.out.println("Is empty? : " + arList.isEmpty());
        for (int i = 0; i < arList.size(); i++) {
            System.out.print(arList.get(i) + " ");
        }
        System.out.println();
        //remove last item by value
        arList.remove(new Integer(68));
        showArray(arList);
        //remove first item by value
        arList.remove(new Integer(7));
        showArray(arList);
        //insert sort whith comparator
        arList.sort(new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                if (o1.hashCode() > o2.hashCode()) return 1;
                if (o1.hashCode() == o2.hashCode()) return 0;
                if (o1.hashCode() < o2.hashCode()) return -1;
                return 0;
            }
        });
        showArray(arList);
        //add(index, Object)
        arList.add(18, new Integer(1));
        //add(index, Object)
        arList.add(0, new Integer(2));
        showArray(arList);
        //clear
        arList.clear();
        System.out.println("Is empty? : " + arList.isEmpty());
        for (int i = 0; i < 20; i++) {
            arList.add(random.nextInt(100));
        }
        showArray(arList);
        int val = 11;
        //contains
        System.out.printf("Contain %d ? : " + arList.contains(val), val);
        System.out.println();
        //indexOf
        System.out.printf("IndexOf %d : %d", 5, arList.indexOf(5));
        System.out.println();
        arList.add(17, 38);
        showArray(arList);
        //lastIndexOf
        System.out.printf("LastIndexOf %d : %d", 38, arList.lastIndexOf(38));
        System.out.println();
        //
        for (int i = 0; i < 10; i++) {
            arList.remove(1);
        }
        //trimToSize
        arList.trimToSize();
        showArray(arList);
        //removeAll
        ArrayList<Integer> list = new ArrayList<>();
        list.add(51);
        list.add(50);
        list.add(21);
        arList.removeAll(list);
        showArray(arList);
        //removeIf
        arList.removeIf((Object value) -> (Integer) value < 50);
        showArray(arList);
        //retainAll
        ArrayList<Integer> c = new ArrayList<>();
        c.add(72);
        arList.retainAll(c);
        showArray(arList);
        //addAll(index, Collection c)
        arList.addAll(1, list);
        showArray(arList);
    }
}
