package com.forecast_fits.forecast_fits_backend.model.dto;

import lombok.Data;

@Data
public class UserDTO {
    private String userName;
    private String password;
    private String email;
}
