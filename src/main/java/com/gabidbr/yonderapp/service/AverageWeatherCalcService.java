package com.gabidbr.yonderapp.service;

import com.gabidbr.yonderapp.model.CityForecast;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class AverageWeatherCalcService {

    private final WeatherService weatherService;

    public Mono<CityForecast> calculateAverage(String cityName) {
        return weatherService.getCityForecast(cityName)
                .flatMap(weatherResponse -> {
                    double averageTemp = weatherResponse.getForecast()
                            .stream()
                            .mapToDouble(forecastDto -> Double.parseDouble(forecastDto.getTemperature()))
                            .average().orElse(0.0);
                    double averageWind = weatherResponse.getForecast().stream()
                            .mapToDouble(forecastDTO -> Double.parseDouble(forecastDTO.getWind()))
                            .average().orElse(0.0);

                    averageTemp = Math.round(averageTemp * 10.0) / 10.0;
                    averageWind = Math.round(averageWind * 10.0) / 10.0;

                    return Mono.just(CityForecast.builder()
                            .name(cityName)
                            .temperature(averageTemp)
                            .wind(averageWind)
                            .build());
                })
                .onErrorResume(ResponseStatusException.class, e -> {
                    if (e.getStatus() == HttpStatus.NOT_FOUND) {
                        return Mono.just(CityForecast.builder()
                                .name(cityName)
                                .build());
                    } else {
                        return Mono.error(e);
                    }
                });
    }
}
