package com.example.sihum.User;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserCreateForm {

    @Email
    @NotEmpty
    private String email;

    @NotEmpty
    private String username;

    @NotEmpty
    private String password1;
    @NotEmpty
    private String password2;
}

