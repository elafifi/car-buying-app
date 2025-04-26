package com.carebuyingapp.core.repository;

import com.carebuyingapp.core.model.SupplierOffer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SupplierOfferRepository extends JpaRepository<SupplierOffer, Long> {
    List<SupplierOffer> findByRequestId(Long requestId);
    List<SupplierOffer> findBySupplierId(Long supplierId);
    boolean existsBySupplierIdAndRequestId(Long supplierId, Long requestId);

    List<SupplierOffer> findAllBySupplierId(Long supplierId);

    List<SupplierOffer> findAllByRequestId(Long customerRequestId);

    long countByRequestId(Long requestId);
}
