package com.gabidbr.yonderapp.service;

import com.gabidbr.yonderapp.model.CityForecast;
import com.opencsv.CSVWriter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.FileWriter;
import java.io.IOException;

@Service
@Slf4j
public class WriteToCsvService {

    public final static String FILE_PATH = "src/main/resources/forecast.csv";
    public Mono<Void> writeToCsv(Flux<CityForecast> cities) {
        return cities
                .collectList()
                .flatMap(cityForecasts -> {
                    try (CSVWriter writer = new CSVWriter(new FileWriter(FILE_PATH))) {
                        String[] header = {"Name", "Temperature", "Wind"};
                        writer.writeNext(header);
                        for (CityForecast cityForecast : cityForecasts) {
                            String[] line = {
                                    cityForecast.getName(),
                                    String.valueOf(cityForecast.getTemperature()),
                                    String.valueOf(cityForecast.getWind())
                            };
                            writer.writeNext(line);
                        }
                    } catch (IOException e) {
                        log.error("Error writing to CSV file: {}", e.getMessage());
                    }
                    return Mono.empty();
                });
    }
}
