package com.mbcdev.blog.example;

import com.mbcdev.blog.patterns.ChangeAwareObservable;

/**
 * Created with IntelliJ IDEA.
 * User: barry
 * Date: 29/03/2013
 * Time: 23:32
 *
 * WeatherData class from Head First Design Patterns reworked to use the new
 * Observable classes.
 *
 * Uses the pull method to notify observers
 *
 */
public class WeatherData extends ChangeAwareObservable {

    private WeatherMeasurements weatherMeasurements;

    public void measurementsChanged() {
        setChanged();
        notifyObservers(this.weatherMeasurements);
    }

    public void setMeasurements(WeatherMeasurements weatherMeasurements) {
        this.weatherMeasurements = weatherMeasurements;
        measurementsChanged();
    }

    public WeatherMeasurements getWeatherMeasurements() {
        return this.weatherMeasurements;
    }
}
