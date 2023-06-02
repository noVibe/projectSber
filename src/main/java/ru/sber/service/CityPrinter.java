package ru.sber.service;

import ru.sber.model.City;

import java.io.IOException;
import java.nio.file.Path;
import java.util.*;

public class CityPrinter {

    public static void printMaxPopulationAndIndex(Path path) {
        List<City> cities = extractCities(path);

        int maxPopulation = 0;
        int maxPopulationIndex = 0;

        for (int i = 0, cityPopulation; i < cities.size(); i++) {

            cityPopulation = cities.get(i).population();

            if (cityPopulation > maxPopulation) {
                maxPopulationIndex = i;
                maxPopulation = cityPopulation;
            }
        }
        System.out.printf("[%d] = %d", maxPopulationIndex, maxPopulation);
    }

    private static List<City> extractCities(Path path) {
        List<City> cityList = new ArrayList<>();
        try (Scanner scanner = new Scanner(path)) {
            while (scanner.hasNextLine()) {
                String[] dataBits = scanner.nextLine().split(";");
                String name = dataBits[1];
                String region = dataBits[2];
                String district = dataBits[3];
                int population = Integer.parseInt(dataBits[4]);
                String foundation = dataBits.length == 6 ? dataBits[5] : "неизвестно";
                cityList.add(new City(name, region, district, population, foundation));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return cityList;
    }

    public static void printCitiesFromFile(Path path) {
        extractCities(path).forEach(System.out::println);
    }
    public static void printCitiesFromFile(Path path, Comparator<City> comparator) {
        extractCities(path).stream()
                .sorted(comparator)
                .forEach(System.out::println);
    }
}
