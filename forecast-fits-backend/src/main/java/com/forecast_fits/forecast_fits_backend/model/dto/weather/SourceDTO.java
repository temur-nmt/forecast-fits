package com.forecast_fits.forecast_fits_backend.model.dto.weather;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class) 
public class SourceDTO {
    Integer id;
    String dwdStationId;
    String wmoStationId;
    String stationName;
    String observationType;
    String firstRecord;
    String lastRecord;
    Double lat;
    Double lon;
    Double height;
    Integer distance;
}
