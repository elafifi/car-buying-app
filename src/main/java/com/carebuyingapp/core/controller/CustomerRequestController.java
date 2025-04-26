package com.carebuyingapp.core.controller;


import com.carebuyingapp.core.dto.CustomerRequestDTO;
import com.carebuyingapp.core.enums.RequestStatus;
import com.carebuyingapp.core.model.CustomerRequest;
import com.carebuyingapp.core.service.CustomerRequestService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customer-requests")
public class CustomerRequestController {

    private final CustomerRequestService customerRequestService;

    public CustomerRequestController(CustomerRequestService customerRequestService) {
        this.customerRequestService = customerRequestService;
    }

    /**
     * Create a new Customer Request
     */
    @PostMapping
    public CustomerRequest createRequest(@RequestBody CustomerRequestDTO requestDTO) {
        return customerRequestService.createRequest(requestDTO);
    }

    /**
     * List Customer Requests (with optional status filter + pagination)
     */
    @GetMapping
    public Page<CustomerRequest> listRequests(
            @RequestParam(value = "status", required = false) RequestStatus status,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size) {
        return customerRequestService.listRequests(status, page, size);
    }

    /**
     * Get a specific Customer Request by ID
     */
    @GetMapping("/{id}")
    public CustomerRequest getRequest(@PathVariable Long id) {
        return customerRequestService.getRequest(id);
    }

    /**
     * Update the status of a Customer Request
     */
    @PatchMapping("/{id}/status")
    public CustomerRequest updateRequestStatus(@PathVariable Long id,
                                               @RequestParam RequestStatus status) {
        return customerRequestService.updateRequestStatus(id, status);
    }

    /**
     * Count offers for a specific request
     */
    @GetMapping("/{id}/offers/count")
    public long countOffers(@PathVariable Long id) {
        return customerRequestService.countOffersForRequest(id);
    }
}