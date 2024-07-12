package project.carservice.model.dto;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import project.carservice.model.entity.enums.EngineTypeEnum;
import project.carservice.validation.ValidVinNumber;

public class AddCarDTO {
   @NotNull
   @Size (min =2, max = 9, message = "Registration number must be between 2 and 9 characters!)")
   private String registration;
   @NotNull
    private String make;

    @NotNull
    private String model;

    @NotNull
    @Size (min = 1941, max = 2022)
    private int year;

    @NotNull
    private EngineTypeEnum engine;

    @NotNull
    @ValidVinNumber (message = "Need add valid phone number")
    private String vinNumber;
}
