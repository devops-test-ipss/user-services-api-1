package com.dev.user_services_api_1.service;


import com.dev.user_services_api_1.dto.request.RequestBillingAddressDto;

public interface BillingAddressService {

    public void manage(RequestBillingAddressDto requestBillingAddressDto, String token);

    public void delete(String userId, String billingAddressId);
}
