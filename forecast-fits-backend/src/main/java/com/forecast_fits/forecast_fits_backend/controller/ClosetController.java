package com.forecast_fits.forecast_fits_backend.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.forecast_fits.forecast_fits_backend.model.dto.ClothesDTO;
import com.forecast_fits.forecast_fits_backend.model.entity.Clothes;
import com.forecast_fits.forecast_fits_backend.model.entity.User;
import com.forecast_fits.forecast_fits_backend.service.ClosetService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/my-closet")
@AllArgsConstructor 
public class ClosetController {
    
    private final ClosetService closetService;

    @GetMapping("")
    public ResponseEntity<?> getMyCloset(@AuthenticationPrincipal User currentUser) {
        List<Clothes> myClothes = closetService.getMyClothes(currentUser.getId());
        return ResponseEntity.ok(myClothes);
    }

    @PostMapping("")
    public ResponseEntity<?> createClothingEntry(@RequestBody ClothesDTO clothesDTO) {
        closetService.createClothingEntry(clothesDTO);
        return ResponseEntity.ok("Added entry successfully!"); 
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteClothingEntry(@PathVariable int id) {
        closetService.deleteClothingEntry(id);
        return ResponseEntity.ok("Entry successfully deleted");
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateClothingEntity(@RequestBody ClothesDTO clothesDTO, @PathVariable int id) {
        closetService.updateClothingEntity(clothesDTO, id);
        return ResponseEntity.ok("Entry was successfully updated");
    }
}
