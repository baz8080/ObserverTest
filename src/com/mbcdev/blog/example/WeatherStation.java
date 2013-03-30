package com.mbcdev.blog.example;

/**
 * Created with IntelliJ IDEA.
 * User: barry
 * Date: 30/03/2013
 * Time: 00:02
 * To change this template use File | Settings | File Templates.
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
