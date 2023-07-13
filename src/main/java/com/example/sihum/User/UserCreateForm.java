package com.example.sihum.User;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserCreateForm {

    @NotEmpty(message = "다시입력")
    private String name;

    @NotEmpty(message = "다시입력")
    private String password1;

    @NotEmpty(message = "다시입력")
    private String password2;

    @Email
    @NotEmpty(message = "다시입력")
    private String email;


}
