package com.example.employee_api.auth.dto;

public class LoginResponse {

    private String token;
    private String type = "Bearer";

    public LoginResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public String getType() {
        return type;
    }
}


//package com.example.employee_api.auth.dto;
//
//public class LoginResponse {
//
//    private String token;
//
//    public LoginResponse(String token) {
//        this.token = token;
//    }
//
//    public String getToken() {
//        return token;
//    }
//}