package week1;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.Comparator;

// Информация получена из https://javarush.com/groups/posts/2262-comparator-v-java
// https://translated.turbopages.org/proxy_u/en-ru.ru.a890a7cc-63bcf6da-1ab97a13-74722d776562/https/www.geeksforgeeks.org/how-to-pass-arraylist-object-as-function-argument-in-java/


public class Main implements Comparable<Main> {
    String name;
    String region;
    String district;
    int population;
    String foundation;

    Main(String name, String region, String district, int population, String foundation ) {
        this.name = name;
        this.region = region;
        this.district = district;
        this.population = population;
        this.foundation = foundation;
    }

    Main() {}

    @Override
    public int compareTo(Main ob) {
        return this.name.charAt(0) - ob.name.charAt(0);
    }


    public String toString() {
        return "City{name='" + name + "', region='" + region + "', district='" + district + "', population=" + population + ", foundation='" + foundation + "'}";
    }

    public static void sort1 (List<Main> list) {
        Collections.sort(list);
    }

    public static void sort2(List<Main> list) {
        Comparator districtComparator = new sortDistrict();
        Collections.sort(list, districtComparator);
    }

    public static void printConsole(List<Main> list) {
        for(int i = 0; i < list.size(); i ++)
            System.out.println(list.get(i));
    }

    public int getPopulation() {
        return this.population;
    }

    public String getRegion() {
        return this.region;
    }

    public String getName() {
        return this.name;
    }


    public static void getMaxPopulation(List<Main> list) {
        int[] countPeopleCity = new int[list.size()];
        for(int i = 0; i < list.size(); i ++)
            countPeopleCity[i] = list.get(i).getPopulation();
        int max = 0;
        int maxIndex = 0;
        for (int i = 0; i < countPeopleCity.length; i++) {
            if (countPeopleCity[i] > max) {
                max = countPeopleCity[i];
                maxIndex = i;
            }
        }
        System.out.println("[" + maxIndex + "] = " + max);
    }

    public static void getCountCityInRegion(List<Main> list) {
        List<String> listRegion = new ArrayList<>();
        for(int i = 0; i < list.size(); i ++) {
            if (!listRegion.contains(list.get(i).getRegion()))
                listRegion.add(list.get(i).getRegion());
        }
        int[] countCityInRegion = new int[listRegion.size()];
        for(int i = 0; i < list.size(); i ++) {
            if (listRegion.indexOf(list.get(i).getRegion()) != -1)
                countCityInRegion[listRegion.indexOf(list.get(i).getRegion())]++;
        }
        for(int i = 0; i < listRegion.size(); i ++) {
            System.out.println(listRegion.get(i) + " - " + countCityInRegion[i]);
        }
    }

    public static void main(String[] args) {
        File file = new File("C:\\Users\\KA49\\IdeaProjects\\untitled1\\src\\week1\\Задача ВС Java Сбер.csv");
        List<Main> listArray = new ArrayList<>();
        Main list = new Main();
        try (Scanner sc = new Scanner(file, StandardCharsets.UTF_8))
        {
            while (sc.hasNextLine()) {
                String str = sc.nextLine();
                String[] element = str.split(";");
               // for (String word : element)
               //     System.out.println(word);
                if (element.length == 5)
                    list = new Main(element[1], element[2], element[3], Integer.valueOf(element[4]), "");
                else
                    list = new Main(element[1], element[2], element[3], Integer.valueOf(element[4]), element[5]);
                listArray.add(list);
            }

            getMaxPopulation(listArray); // Поиск максимального количества жителей

            getCountCityInRegion(listArray); // определение количество городов в каждом регионе.

            sort1(listArray); // 1 способ сортировки
            printConsole(listArray);

            System.out.println("\n\n\n\n");
            sort2(listArray); // 2 способ сортировки
            printConsole(listArray);

        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}

