package com.forecast_fits.forecast_fits_backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.forecast_fits.forecast_fits_backend.model.dto.ClothesDTO;
import com.forecast_fits.forecast_fits_backend.model.entity.Clothes;
import com.forecast_fits.forecast_fits_backend.repository.ClothesRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor 
public class ClosetService {

    private final ClothesRepository clothesRepository;
    
    public Optional<List<Clothes>> getMyClothes(int userId) {
        return clothesRepository.findByUserId(userId);
    }

    public void createClothingEntry(ClothesDTO clothesEntry) {
        Clothes entry = Clothes.builder()
            .name(clothesEntry.getName())
            .userId(clothesEntry.getUserId())
            .category(clothesEntry.getCategory())
            .minTemp(clothesEntry.getMinTemp())
            .maxTemp(clothesEntry.getMaxTemp())
            .weather(clothesEntry.getWeather())
            .build();
        clothesRepository.save(entry);
    }

    public void deleteClothingEntry(int id) {
        clothesRepository.deleteById(id);
    }

    public void updateClothingEntity(ClothesDTO clothesDTO, int id) {
        Clothes newEntry = Clothes.builder()
            .id(id)
            .name(clothesDTO.getName())
            .userId(clothesDTO.getUserId())
            .category(clothesDTO.getCategory())
            .minTemp(clothesDTO.getMinTemp())
            .maxTemp(clothesDTO.getMaxTemp())
            .weather(clothesDTO.getWeather())
            .build();
        clothesRepository.save(newEntry);
    }
}
