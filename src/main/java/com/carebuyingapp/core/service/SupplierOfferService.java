package com.carebuyingapp.core.service;

import com.carebuyingapp.core.dto.SupplierOfferDTO;
import com.carebuyingapp.core.enums.OfferStatus;
import com.carebuyingapp.core.enums.RequestStatus;
import com.carebuyingapp.core.exception.InvalidOperationException;
import com.carebuyingapp.core.exception.ResourceNotFoundException;
import com.carebuyingapp.core.model.CustomerRequest;
import com.carebuyingapp.core.model.SupplierOffer;
import com.carebuyingapp.core.repository.CustomerRequestRepository;
import com.carebuyingapp.core.repository.SupplierOfferRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SupplierOfferService {

    private final SupplierOfferRepository offerRepository;
    private final CustomerRequestRepository requestRepository;

    public SupplierOfferService(SupplierOfferRepository offerRepository, CustomerRequestRepository requestRepository) {
        this.offerRepository = offerRepository;
        this.requestRepository = requestRepository;
    }

    @Transactional
    public SupplierOffer submitOffer(SupplierOfferDTO dto) {
        // Check if the request exists
        CustomerRequest request = requestRepository.findById(dto.getRequestId())
                .orElseThrow(() -> new ResourceNotFoundException("Customer Request not found"));

        // Check if the request is still ACTIVE
        if (request.getStatus() != RequestStatus.ACTIVE) {
            throw new InvalidOperationException("Cannot submit offer: Request is not active.");
        }

        // Check if supplier has already submitted an offer for this request
        boolean alreadySubmitted = offerRepository.existsBySupplierIdAndRequestId(dto.getSupplierId(), dto.getRequestId());
        if (alreadySubmitted) {
            throw new InvalidOperationException("Supplier already submitted an offer for this request.");
        }

        // Save the offer
        SupplierOffer offer = new SupplierOffer();
        offer.setSupplierId(dto.getSupplierId());
        offer.setRequest(request);
        offer.setCarDetails(dto.getCarDetails());
        offer.setPrice(dto.getPrice());
        offer.setIsImported(dto.getIsImported());
        offer.setStatus(OfferStatus.PENDING); // Initially pending

        return offerRepository.save(offer);
    }

    public List<SupplierOffer> getOffersByRequest(Long requestId) {
        return offerRepository.findByRequestId(requestId);
    }

    public List<SupplierOffer> getOffersBySupplier(Long supplierId) {
        return offerRepository.findBySupplierId(supplierId);
    }

    public List<SupplierOffer> getOffersBySupplierId(Long supplierId) {
        return offerRepository.findAllBySupplierId(supplierId);
    }

    public List<SupplierOffer> getOffersByRequestId(Long requestId) {
        return offerRepository.findAllByRequestId(requestId);
    }
}
