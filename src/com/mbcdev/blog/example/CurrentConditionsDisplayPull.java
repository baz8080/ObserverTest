package com.mbcdev.blog.example;

import com.mbcdev.blog.patterns.Observable;
import com.mbcdev.blog.patterns.Observer;

/**
 * This class uses the pull method of observing by calling the Observable class'
 * {@link com.mbcdev.blog.example.WeatherData#getWeatherMeasurements() getWeatherMeasurements()} method directly
 */
public class CurrentConditionsDisplayPull implements Observer<WeatherData, WeatherMeasurements>, DisplayElement {

    WeatherMeasurements weatherMeasurements;

    public CurrentConditionsDisplayPull(Observable observable) {
        observable.addObserver(this);
    }

    @Override
    public void display() {
        System.out.println("Temperature (Pull): " + weatherMeasurements.getTemperature());
    }

    @Override
    public void update(WeatherData observable, WeatherMeasurements data) {
        this.weatherMeasurements = observable.getWeatherMeasurements();
        display();
    }
}
