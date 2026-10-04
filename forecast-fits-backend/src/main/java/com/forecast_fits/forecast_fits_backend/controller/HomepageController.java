package com.forecast_fits.forecast_fits_backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.forecast_fits.forecast_fits_backend.model.entity.User;
import com.forecast_fits.forecast_fits_backend.model.vm.HomepageRecommendationVM;
import com.forecast_fits.forecast_fits_backend.service.HomepageService;
import com.forecast_fits.forecast_fits_backend.model.vm.WeatherForecastVM;

import java.util.List;

import lombok.RequiredArgsConstructor;

@RestController 
@RequiredArgsConstructor 
@RequestMapping("/")
public class HomepageController {
    
    private final HomepageService homepageService;

    private record BrightSkyRequest(
        Double lat,
        Double lon,
        String location
    ){}

    @GetMapping ("")
    public ResponseEntity<?> getRecomendation(BrightSkyRequest brightSkyRequest, @AuthenticationPrincipal User user) {
        HomepageRecommendationVM recommendation = homepageService.getRecommendation(brightSkyRequest.lat(), brightSkyRequest.lon(), brightSkyRequest.location(), user.getId());
        return ResponseEntity.ok(recommendation);
    }

    @GetMapping("/week")
    public ResponseEntity<?> getForecastWeek(BrightSkyRequest brightSkyRequest) {
        List<WeatherForecastVM> forecast = homepageService.getForecastWeek(brightSkyRequest.lat(), brightSkyRequest.lon());
        return ResponseEntity.ok(forecast);
    }
}
