package com.carebuyingapp.core.repository;

import com.carebuyingapp.core.enums.RequestStatus;
import com.carebuyingapp.core.model.CustomerRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRequestRepository extends JpaRepository<CustomerRequest, Long> {
    Page<CustomerRequest> findByStatus(RequestStatus status, Pageable pageable);
}

