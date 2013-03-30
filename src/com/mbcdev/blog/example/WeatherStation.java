package com.mbcdev.blog.example;

/**
 * A driver class to demonstrate both the push and pull methods of the Observer pattern.
 */
public class WeatherStation {

    public static void main(String[] args) {
        WeatherData weatherData = new WeatherData();

        CurrentConditionsDisplayPull currentConditionsDisplay = new CurrentConditionsDisplayPull(weatherData);
        CurrentConditionsDisplayPush currentConditionsDisplayPush = new CurrentConditionsDisplayPush(weatherData);

        WeatherMeasurements weatherMeasurements = new WeatherMeasurements(20.3f, 80.9f, 1000.2f);

        weatherData.setMeasurements(weatherMeasurements);
    }
}
