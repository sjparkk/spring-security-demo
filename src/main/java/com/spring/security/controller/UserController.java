package com.spring.security.controller;

import com.spring.security.dto.UserDTO;
import com.spring.security.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class UserController {
    private final UserService userService;

    @PostMapping("/user")
    public String signup(UserDTO userDTO) {
        userService.save(userDTO);
        return "redirect:/login";
    }
}
