package com.dev.user_services_api_1.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RequestUserDto {

    private String email;
    private String password;
    private String firstName;
    private String lastName;

}
