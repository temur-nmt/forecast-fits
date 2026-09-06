package com.forecast_fits.forecast_fits_backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.forecast_fits.forecast_fits_backend.model.dto.HomepageRecommendation;
import com.forecast_fits.forecast_fits_backend.service.HomepageService;

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

    @PostMapping ("")
    public ResponseEntity<?> getRecomendation(@RequestBody BrightSkyRequest brightSkyRequest) {
        HomepageRecommendation recommendation = homepageService.getRecommendation(brightSkyRequest.lat, brightSkyRequest.lon, brightSkyRequest.location);
        return ResponseEntity.ok(recommendation);
    }
}
