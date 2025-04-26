package com.carebuyingapp.core.model;

import com.carebuyingapp.core.enums.InspectionCompany;
import com.carebuyingapp.core.enums.RequestStatus;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class CustomerRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long customerId;

    @Enumerated(EnumType.STRING)
    private RequestStatus status;

    private String description;

    @Enumerated(EnumType.STRING)
    private InspectionCompany checkedByCompany;

    @OneToMany(mappedBy = "request", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<SupplierOffer> offers = new ArrayList<>();

    // getters and setters
}

