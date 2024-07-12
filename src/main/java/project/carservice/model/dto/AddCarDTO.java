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
    private String year;

    @NotNull
    private EngineTypeEnum engine;

    @NotNull
    @ValidVinNumber (message = "Need add valid phone number")
    private String vinNumber;

    public AddCarDTO(String registration, String make, String model, String year, EngineTypeEnum engine, String vinNumber) {
        this.registration = registration;
        this.make = make;
        this.model = model;
        this.year = year;
        this.engine = engine;
        this.vinNumber = vinNumber;
    }

    public String getRegistration() {
        return registration;
    }

    public void setRegistration(String registration) {
        this.registration = registration;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public EngineTypeEnum getEngine() {
        return engine;
    }

    public void setEngine(EngineTypeEnum engine) {
        this.engine = engine;
    }

    public String getVinNumber() {
        return vinNumber;
    }

    public void setVinNumber(String vinNumber) {
        this.vinNumber = vinNumber;
    }
    public static AddCarDTO empty() {
        return new AddCarDTO(null, null, null, null, null, null);
    }
}
