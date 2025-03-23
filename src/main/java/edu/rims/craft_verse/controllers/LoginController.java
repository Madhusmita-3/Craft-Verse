package edu.rims.craft_verse.controllers;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.rims.craft_verse.entity.User;
import edu.rims.craft_verse.repository.UserRepository;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/customerLogin")
public class LoginController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/login")
    public String login() {
        return "customer/login";
    }

    @PostMapping("/sign-up")
    public String signUp(@ModelAttribute User user) {
        System.out.println(user.getUserEmail());
        user.setCreatedDate(LocalDateTime.now());
        user.setUpdatedDate(LocalDateTime.now());
        user.setCreatedBy("user");
        user.setUpdatedBy("user");
        user.setUserPassword(passwordEncoder.encode(user.getUserPassword()));
        System.out.println(user.getUserPassword());
        userRepository.save(user);
        return "redirect:/customer/login";
    }

}
