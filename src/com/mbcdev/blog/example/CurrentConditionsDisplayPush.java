package com.mbcdev.blog.example;

import com.mbcdev.blog.patterns.Observable;
import com.mbcdev.blog.patterns.Observer;

/**
 * Created with IntelliJ IDEA.
 * User: barry
 * Date: 30/03/2013
 * Time: 00:06
 * To change this template use File | Settings | File Templates.
 */
public class CurrentConditionsDisplayPush implements Observer<WeatherData, WeatherMeasurements>, DisplayElement {
    WeatherMeasurements weatherMeasurements;

    public CurrentConditionsDisplayPush(Observable observable) {
        observable.addObserver(this);
    }

    @Override
    public void display() {
        System.out.println("Temperature (Push): " + weatherMeasurements.getTemperature());
    }

    @Override
    public void update(WeatherData observer, WeatherMeasurements data) {
        this.weatherMeasurements = data;
        display();
    }
}
