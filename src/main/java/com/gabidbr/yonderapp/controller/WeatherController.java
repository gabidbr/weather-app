package com.gabidbr.yonderapp.controller;

import com.gabidbr.yonderapp.model.CityForecast;
import com.gabidbr.yonderapp.service.AverageWeatherCalcService;
import com.gabidbr.yonderapp.service.WriteToCsvService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping("api/v1/weather")
@AllArgsConstructor
public class WeatherController {

    private final AverageWeatherCalcService weatherService;
    private final WriteToCsvService writeToCsvService;

    @GetMapping
    public Flux<CityForecast> getAverageWeather(@RequestParam List<String> city) {
        Flux<CityForecast> cityForecastFlux = Flux.fromIterable(city)
                .flatMap(weatherService::calculateAverage)
                .sort(Comparator.comparing(CityForecast::getName));

        writeToCsvService.writeToCsv(cityForecastFlux).subscribe();

        return cityForecastFlux;
    }
}
