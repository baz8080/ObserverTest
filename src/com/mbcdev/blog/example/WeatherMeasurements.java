package com.mbcdev.blog.example;

/**
 * Simple bean class for basic weather measurements
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
