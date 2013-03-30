package com.mbcdev.blog.example;

/**
 * Created with IntelliJ IDEA.
 * User: barry
 * Date: 29/03/2013
 * Time: 23:39
 * To change this template use File | Settings | File Templates.
 */
public class WeatherMeasurements {
    private float temperature;
    private float humidity;
    private float pressure;

    public WeatherMeasurements(float temperature, float humidity, float pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
    }

    public float getTemperature() {
        return temperature;
    }

    public float getHumidity() {
        return humidity;
    }

    public float getPressure() {
        return pressure;
    }
}
