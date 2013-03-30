package com.mbcdev.blog.example;

import com.mbcdev.blog.patterns.ChangeAwareObservable;

/**
 * WeatherData class from Head First Design Patterns reworked to use the new
 * Observable classes.
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
