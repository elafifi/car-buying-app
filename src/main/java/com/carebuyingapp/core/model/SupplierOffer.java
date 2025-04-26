package com.carebuyingapp.core.model;

import com.carebuyingapp.core.enums.OfferStatus;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Data
public class SupplierOffer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long supplierId;

    @ManyToOne
    @JoinColumn(name = "request_id")
    @JsonBackReference
    private CustomerRequest request;

    @Enumerated(EnumType.STRING)
    private OfferStatus status;

    private Integer inspectionScore;

    private String carDetails;

    private BigDecimal price;

    private Boolean isImported;

    // getters and setters
}
