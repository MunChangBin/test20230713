package com.example.sihum.User;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequiredArgsConstructor
@Controller
public class UserController {
    private UserService userService;

    @GetMapping("/user/signup")
    public String signup(UserCreateForm userCreateForm) {
        return "signup_form";
    }

    @PostMapping("/user/signup")
    public String create(@Valid UserCreateForm userCreateForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "signup_form";
        }
        if(! userCreateForm.getPassword1().equals(userCreateForm.getPassword1())){
            bindingResult.rejectValue("password1","passwordInCorrect","비밀번호가 일치하지않습니다");
            return "signup_form";
        }
        userService.create(userCreateForm.getName(),userCreateForm.getPassword1(), userCreateForm.getEmail() );
        return "redirect:/article/list";
    }
}
