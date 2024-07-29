package project.carservice.model.dto.addDTO;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import project.carservice.model.entity.enums.EngineTypeEnum;
import project.carservice.validation.annotations.UniqueRegistration;
import project.carservice.validation.annotations.UniqueVinNumber;
import project.carservice.validation.annotations.ValidVinNumber;

public class AddCarDTO {
   @NotNull
   @UniqueRegistration
   @Size (min =2, max = 9, message = "Registration number must be between 2 and 9 characters!)")
   private String registration;
   @NotNull
    private String make;

    @NotNull
    private String model;
    @NotNull
    @Min(1941)
    @Max(2022)
    private int year;

    @NotNull (message = "Please choose engine type")
    private EngineTypeEnum engine;

    @ValidVinNumber (message = "Need add valid VIN number")
    @UniqueVinNumber
    private String vinNumber;

    public AddCarDTO(String registration, String make, String model, int year, EngineTypeEnum engine, String vinNumber) {
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

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
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
        return new AddCarDTO(null, null, null, 0, null, null);
    }
}
