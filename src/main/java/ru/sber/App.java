package ru.sber;


import ru.sber.model.City;
import ru.sber.service.CityPrinter;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;

public class App
{
    public static void main( String[] args )
    {
        Path path = Paths.get("src/main/java/ru/sber/file.csv");
        CityPrinter.printCitiesFromFile(path);

        Comparator<City> byNameIgnoreCaseReversedOrder = Comparator.comparing(City::name, String.CASE_INSENSITIVE_ORDER).reversed();
        Comparator<City> byDistrictThenNameReversedOrder = Comparator.comparing(City::district).reversed().thenComparing(City::name);

        CityPrinter.printCitiesFromFile(path, byNameIgnoreCaseReversedOrder);
        CityPrinter.printCitiesFromFile(path, byDistrictThenNameReversedOrder);

    }
}
