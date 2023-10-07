package com.gabidbr.yonderapp.service;

import com.gabidbr.yonderapp.dto.WeatherResponseDto;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class WeatherService {

    private final WebClient webClient;

    public Mono<WeatherResponseDto> getCityForecast(String cityName){
        return webClient.get()
                .uri("http://localhost:8080/{cityName}", cityName)
                .retrieve()
                .bodyToMono(WeatherResponseDto.class)
                .onErrorResume(e -> Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Forecast data not found for city: " + cityName)));
    }
}
