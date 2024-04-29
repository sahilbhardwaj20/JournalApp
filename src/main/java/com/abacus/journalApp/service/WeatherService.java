package com.abacus.journalApp.service;

import com.abacus.journalApp.api.response.WeatherResponse;
import com.abacus.journalApp.cache.AppCache;
import com.abacus.journalApp.constants.PlaceHolders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {

    @Value("${weather.api.key}")
    private String apiKey;

    //private static final String API = "http://api.weatherapi.com/v1/current.json?key=API_KEY&q=CITY&aqi=no";
    @Autowired
    AppCache appCache;

    @Autowired
    private RestTemplate restTemplate;

    public WeatherResponse getWeather(String city){
        String finalAPI = appCache.getCacheMap().get(AppCache.keys.WEATHER_API.toString()).replace(PlaceHolders.CITY,city).replace(PlaceHolders.API_KEY,apiKey);
        ResponseEntity<WeatherResponse> response = restTemplate.exchange(finalAPI, HttpMethod.GET, null, WeatherResponse.class);

        System.out.println(response.getStatusCode());

        return response.getBody();
    }
}
