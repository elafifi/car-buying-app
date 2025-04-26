package com.carebuyingapp.core.dto;

import com.carebuyingapp.core.enums.InspectionCompany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CustomerRequestDTO {

    @NotNull
    private Long customerId;

    @NotBlank
    private String description;

    @NotNull
    private InspectionCompany checkedByCompany;
}
