package com.mbcdev.blog.example;

import com.mbcdev.blog.patterns.Observable;
import com.mbcdev.blog.patterns.Observer;

/**
 * This class uses the push method of observing by using the data supplied by the Observable when it enters
 * its {@link com.mbcdev.blog.patterns.Observable#notifyObservers() notifyObservers()} method.
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
    public void update(WeatherData observable, WeatherMeasurements data) {
        this.weatherMeasurements = data;
        display();
    }
}
