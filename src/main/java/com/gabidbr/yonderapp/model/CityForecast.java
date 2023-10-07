package com.gabidbr.yonderapp.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CityForecast {

    private String name;

    private Double temperature;

    private Double wind;
}
