package com.gabidbr.yonderapp.dto;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WeatherResponseDto {

    private List<ForecastDto> forecast;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class ForecastDto {

        private String day;
        private String temperature;
        private String wind;
    }
}
