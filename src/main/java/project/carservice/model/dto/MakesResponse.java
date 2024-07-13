package project.carservice.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class MakesResponse {
    @JsonProperty("Makes")
    private List<CarQueryMakeDTO> makes;

    public List<CarQueryMakeDTO> getMakes() {
        return makes;
    }

    public void setMakes(List<CarQueryMakeDTO> makes) {
        this.makes = makes;
    }
}
