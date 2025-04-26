package com.carebuyingapp.core.service;

import com.carebuyingapp.core.dto.CustomerRequestDTO;
import com.carebuyingapp.core.enums.RequestStatus;
import com.carebuyingapp.core.exception.ResourceNotFoundException;
import com.carebuyingapp.core.model.CustomerRequest;
import com.carebuyingapp.core.repository.CustomerRequestRepository;
import com.carebuyingapp.core.repository.SupplierOfferRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomerRequestService {

    private final CustomerRequestRepository requestRepository;
    private final SupplierOfferRepository offerRepository;

    public CustomerRequestService(CustomerRequestRepository requestRepository,
                                  SupplierOfferRepository offerRepository) {
        this.requestRepository = requestRepository;
        this.offerRepository = offerRepository;
    }

    @Transactional
    public CustomerRequest createRequest(CustomerRequestDTO dto) {
        CustomerRequest request = new CustomerRequest();
        request.setCustomerId(dto.getCustomerId());
        request.setDescription(dto.getDescription());
        request.setCheckedByCompany(dto.getCheckedByCompany());
        request.setStatus(RequestStatus.ACTIVE); // Default status ACTIVE
        return requestRepository.save(request);
    }

    public Page<CustomerRequest> listRequests(RequestStatus status, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        if (status != null) {
            return requestRepository.findByStatus(status, pageRequest);
        } else {
            return requestRepository.findAll(pageRequest);
        }
    }

    public CustomerRequest getRequest(Long requestId) {
        return requestRepository.findById(requestId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer Request not found"));
    }

    @Transactional
    public CustomerRequest updateRequestStatus(Long requestId, RequestStatus newStatus) {
        CustomerRequest request = getRequest(requestId);
        request.setStatus(newStatus);
        return requestRepository.save(request);
    }

    public long countOffersForRequest(Long requestId) {
        return offerRepository.countByRequestId(requestId);
    }
}
