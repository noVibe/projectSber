package ru.sber;


import ru.sber.service.CityPrinter;

import java.nio.file.Path;
import java.nio.file.Paths;

public class App
{
    public static void main( String[] args )
    {
        Path path = Paths.get("src/main/java/ru/sber/file.csv");
        CityPrinter.printCitiesFromFile(path);
    }
}
