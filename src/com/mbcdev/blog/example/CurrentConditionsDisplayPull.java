package com.mbcdev.blog.example;

import com.mbcdev.blog.patterns.Observable;
import com.mbcdev.blog.patterns.Observer;

/**
 * Created with IntelliJ IDEA.
 * User: barry
 * Date: 29/03/2013
 * Time: 23:45
 * To change this template use File | Settings | File Templates.
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
    public void update(WeatherData observer, WeatherMeasurements data) {
        this.weatherMeasurements = observer.getWeatherMeasurements();
        display();
    }
}
