package com.dev.user_services_api_1.service;

import com.dev.user_services_api_1.dto.request.RequestUserDto;

public interface SystemUserService {

    public void signup(RequestUserDto requestSystemUserDto);

    public void login(String email, String password);

}
