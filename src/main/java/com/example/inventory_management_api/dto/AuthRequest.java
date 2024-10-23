package com.example.inventory_management_api.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class AuthRequest {
    private String username;
    private String password;

}
