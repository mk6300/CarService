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
   @NotNull (message = "{addCar.registration.notnull}")
   @UniqueRegistration (message = "{addCar.registration.unique}")
   @Size (min =2, max = 10, message = "{addCar.registration.size}")
   private String registration;
   @NotNull (message = "{addCar.make.notnull}")
    private String make;

    @NotNull (message = "{addCar.model.notnull}")
    private String model;
    @NotNull (message = "{addCar.year.notnull}")
    @Min(1941)
    @Max(2022)
    private int year;

    @NotNull (message = "{addCar.engine.notnull}")
    private EngineTypeEnum engine;

    @NotNull (message = "{addCar.vin.notnull}")
    @ValidVinNumber (message = "{addCar.vin.valid}")
    @UniqueVinNumber (message = "{addCar.vin.unique}")
    private String vinNumber;

    public AddCarDTO(String registration, String make, String model, int year, EngineTypeEnum engine, String vinNumber) {
        this.registration = registration;
        this.make = make;
        this.model = model;
        this.year = year;
        this.engine = engine;
        this.vinNumber = vinNumber;
    }

    public AddCarDTO() {

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
