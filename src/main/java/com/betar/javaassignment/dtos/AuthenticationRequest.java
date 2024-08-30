package com.betar.javaassignment.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthenticationRequest {
    @NotNull(message = "username cannot be null")
    @Min(3)
    private String username;
    @NotNull(message = "password cannot be null")
    @Min(3)
    private String password;
}
