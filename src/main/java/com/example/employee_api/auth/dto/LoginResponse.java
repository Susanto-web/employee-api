package com.example.employee_api.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponse {
    private String token;
}

//package com.example.employee_api.auth.dto;
//
//public class LoginResponse {
//
//    private String token;
//
//    public LoginResponse(String token){
//        this.token = token;
//    }
//
//    public String getToken(){
//        return token;
//    }
//}

