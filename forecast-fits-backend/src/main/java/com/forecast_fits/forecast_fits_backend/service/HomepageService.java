package com.forecast_fits.forecast_fits_backend.service;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.forecast_fits.forecast_fits_backend.enums.ClothesCategory;
import com.forecast_fits.forecast_fits_backend.enums.Weather;
import com.forecast_fits.forecast_fits_backend.model.dto.brightsky.BrightSkyCurrentResponse;
import com.forecast_fits.forecast_fits_backend.model.dto.brightsky.BrightSkyForecastResponse;
import com.forecast_fits.forecast_fits_backend.model.dto.weather.WeatherCurrentDTO;
import com.forecast_fits.forecast_fits_backend.model.dto.weather.WeatherForecastDTO;
import com.forecast_fits.forecast_fits_backend.model.entity.Clothes;
import com.forecast_fits.forecast_fits_backend.model.vm.ClothingItemVM;
import com.forecast_fits.forecast_fits_backend.model.vm.FitReccomendationVM;
import com.forecast_fits.forecast_fits_backend.model.vm.HomepageRecommendationVM;
import com.forecast_fits.forecast_fits_backend.model.vm.WeatherCurrentVM;
import com.forecast_fits.forecast_fits_backend.model.vm.WeatherForecastVM;
import com.forecast_fits.forecast_fits_backend.repository.ClothesRepository;

import lombok.AllArgsConstructor;

@Service 
@AllArgsConstructor 
public class HomepageService {

    private final WeatherService weatherService;
    private final ClothesRepository clothesRepository;

    public HomepageRecommendationVM getRecommendation(Double lat, Double lon, String location, int userId) {

        String currentDate = LocalDate.now().toString();

        BrightSkyCurrentResponse weatherResponse = weatherService.getCurrentWeather(lat, lon);
        BrightSkyForecastResponse forecastResponse = weatherService.getForecast(lat, lon, currentDate, null);

        WeatherCurrentDTO weatherDTO = weatherResponse.getWeather();
        List<WeatherForecastDTO> weatherForecast = forecastResponse.getWeather();
        List<WeatherForecastVM> forecastVMs = weatherForecast.stream().map(WeatherForecastVM::fromModel).toList();
        String finalLocation = (location != null && !location.isBlank()) 
                           ? location 
                           : null;
        FitReccomendationVM fitRecommendation = createFitReccomendation(weatherDTO, userId);

        return HomepageRecommendationVM.builder()
            .weather(
                WeatherCurrentVM.builder()
                    .location(finalLocation)
                    .cloudCover(weatherDTO.getCloudCover())
                    .condition(weatherDTO.getCondition())
                    .relativeHumidity(weatherDTO.getRelativeHumidity())
                    .temperature(weatherDTO.getTemperature())
                    .windSpeed(weatherDTO.getWindSpeed10())
                    .build()
            )
            .recomendation(fitRecommendation)
            .forecast(forecastVMs)
            .build();
    }

    public List<WeatherForecastVM> getForecastWeek(Double lat, Double lon) {

        String currentDate = LocalDate.now().toString();
        String endDate = LocalDate.now().plusWeeks(1).toString();
        System.out.println("currentDate: " + currentDate + ", endDate: " + endDate);
        BrightSkyForecastResponse forecastResponse = weatherService.getForecast(lat, lon, currentDate, endDate);
        List<WeatherForecastDTO> weatherForecast = forecastResponse.getWeather();
        
        return weatherForecast.stream().map(WeatherForecastVM::fromModel).toList();
    }

    /**
     * Creates a fit reccomendation based on weather and temperature:
     *  - if temp >= 25, baselayer
     *  - if temp >= 15, baselayer + midlayer
     *  - if temp < 15, baselayer + midlayer + outerlayer
     * @param weather
     * @return reccomendation based on current weather
     */
    private FitReccomendationVM createFitReccomendation(WeatherCurrentDTO weather, int userId) {

        int temp = weather.getTemperature().intValue();
        
        Set<Weather> weatherStatusSet = getWeatherstatus(weather);
        ObjectMapper objectMapper = new ObjectMapper();
        String weatherStatus = "[]";
        try {
            weatherStatus = objectMapper.writeValueAsString(weatherStatusSet); 
        } catch ( Exception e) {
            e.printStackTrace();
        }

        Random random = new Random();
        
        // find by temperature
        List<Clothes> baseLayer = clothesRepository.findFit(userId, temp, weatherStatus, List.of(ClothesCategory.TSHIRTS, ClothesCategory.TOPS));
        List<Clothes> midLayer = clothesRepository.findFit(userId, temp, weatherStatus, List.of(ClothesCategory.DRESSES, ClothesCategory.ONEPIECES, ClothesCategory.SWEATERS));
        List<Clothes> outerLayer = clothesRepository.findFit(userId, temp, weatherStatus, List.of(ClothesCategory.OUTERWEAR));

        List<Clothes> headwear = clothesRepository.findFit(userId, temp, weatherStatus, List.of(ClothesCategory.HEADWEAR));
        List<Clothes> bottoms = clothesRepository.findFit(userId, temp, weatherStatus, List.of(ClothesCategory.BOTTOMS, ClothesCategory.SHORTS));
        List<Clothes> footwear = clothesRepository.findFit(userId, temp, weatherStatus, List.of(ClothesCategory.FOOTWEAR));
        List<Clothes> accessoires = clothesRepository.findFit(userId, temp, weatherStatus, List.of(ClothesCategory.ACCESSORIES, ClothesCategory.MISC));
        
        // convert to VM
        ClothingItemVM baseItemVM = getRandomItemVM(baseLayer, random);
        ClothingItemVM midItemVM = getRandomItemVM(midLayer, random);
        ClothingItemVM outerItemVM = getRandomItemVM(outerLayer, random);

        ClothingItemVM headwearVM = getRandomItemVM(headwear, random);
        ClothingItemVM footwearVM = getRandomItemVM(footwear, random);
        ClothingItemVM bottomVM = getRandomItemVM(bottoms, random);
        List<ClothingItemVM> accessoiresVM = accessoires.stream().map(ClothingItemVM::fromModel).toList();
        
        return FitReccomendationVM.builder()
                .baseLayer(baseItemVM)
                .midLayer(midItemVM)
                .outerLayer(outerItemVM)
                .headwear(headwearVM)
                .bottom(bottomVM)
                .footwear(footwearVM)
                .accessoires(accessoiresVM)
                .build();
    }

    private ClothingItemVM getRandomItemVM(List<Clothes> items, Random random) {
        if (items == null | items.isEmpty()) {
            return  null;
        }

        Clothes randomItem = items.get(random.nextInt(items.size()));
        return ClothingItemVM.fromModel(randomItem);
    }

    /**
     * Get the weather status based on the current weather.
     * @param weather
     * @return Set defining the weather status.
     */
    private Set<Weather> getWeatherstatus(WeatherCurrentDTO weather) {
        
        Set<Weather> tags = new HashSet<>();

        if (weather.getTemperature() != null) {
            double temp = weather.getTemperature();
            if (temp <= 0)       tags.add(Weather.FREEZING);
            else if (temp < 15)  tags.add(Weather.COLD);
            else if (temp < 25)  tags.add(Weather.WARM);
            else                 tags.add(Weather.HOT);
        }

        if (weather.getCondition() != null) {
            String condition = weather.getCondition().toLowerCase();
            if (condition.contains("rain") || condition.contains("sleet")) {
                tags.add(Weather.RAINY);
            } else if (condition.contains("snow") || condition.contains("hail")) {
                tags.add(Weather.FREEZING); 
            } else if (condition.contains("thunderstorm")) {
                tags.add(Weather.STORMY);
                tags.add(Weather.RAINY);
            }
        }

        if (weather.getCloudCover() != null) {
            double clouds = weather.getCloudCover();
            if (clouds < 30 && !tags.contains(Weather.RAINY)) {
                tags.add(Weather.SUNNY);
            } 
            else if (clouds >= 60) {
                tags.add(Weather.CLOUDY);
            }
        }

        if (weather.getWindSpeed10() != null) {
            double wind = weather.getWindSpeed10();
            if (wind > 60)       tags.add(Weather.STORMY);
            else if (wind > 30)  tags.add(Weather.WINDY);
        }

        if (weather.getTimestamp() != null) {
            try {
                OffsetDateTime dateTime = OffsetDateTime.parse(weather.getTimestamp());
                int month = dateTime.getMonthValue();

                if (month == 12 || month == 1 || month == 2) tags.add(Weather.WINTER);
                else if (month >= 3 && month <= 5)           tags.add(Weather.SPRING);
                else if (month >= 6 && month <= 8)           tags.add(Weather.SUMMER);
                else                                         tags.add(Weather.AUTUMN);
            } catch (Exception e) {
            
            }
        }

        if (weather.getRelativeHumidity() != null) {
            double humidity = weather.getRelativeHumidity();
            if (humidity > 70)       tags.add(Weather.HUMID);
            else if (humidity < 30)  tags.add(Weather.ARID);
        }

        return tags;
    }

}
