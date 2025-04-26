package com.carebuyingapp.core.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class SupplierOfferDTO {

    @NotNull
    private Long supplierId;

    @NotNull
    private Long requestId;

    @NotBlank
    private String carDetails;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = false)
    private BigDecimal price;

    @NotNull
    private Boolean isImported;
}
