package com.carebuyingapp.core.controller;

import com.carebuyingapp.core.dto.SupplierOfferDTO;
import com.carebuyingapp.core.model.SupplierOffer;
import com.carebuyingapp.core.service.SupplierOfferService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/supplier-offers")
public class SupplierOfferController {
    private final SupplierOfferService service;

    public SupplierOfferController(SupplierOfferService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<SupplierOffer> submitOffer(@RequestBody @Valid SupplierOfferDTO dto) {
        return ResponseEntity.ok(service.submitOffer(dto));
    }

    @GetMapping
    public List<SupplierOffer> listOffers(@RequestParam Long requestId) {
        return service.getOffersByRequest(requestId);
    }

    @GetMapping("/supplier/{supplierId}")
    public List<SupplierOffer> getOffersBySupplierId(@PathVariable Long supplierId) {
        return service.getOffersBySupplierId(supplierId);
    }

    @GetMapping("/request/{requestId}")
    public List<SupplierOffer> getOffersByRequestId(@PathVariable Long requestId) {
        return service.getOffersByRequestId(requestId);
    }
}
