package ru.sber.service;

import ru.sber.model.City;

import java.io.IOException;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

public class CityPrinter {

    public static void printCitiesAmountByRegion(Path path) {
        extractCities(path).stream()
                .collect(Collectors.groupingBy(City::region, Collectors.counting()))
                .forEach((key, value) -> System.out.printf("%s - %d\n", key, value));
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
}
