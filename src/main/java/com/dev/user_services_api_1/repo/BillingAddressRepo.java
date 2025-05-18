package com.dev.user_services_api_1.repo;

import com.dev.user_services_api_1.entity.BillingAddress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillingAddressRepo extends JpaRepository<BillingAddress, String> {
    // This interface extends JpaRepository, which provides CRUD operations for BillingAddress entities.
    // You can add custom query methods here if needed.


}
