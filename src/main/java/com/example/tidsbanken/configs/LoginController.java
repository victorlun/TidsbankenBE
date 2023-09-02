package com.example.tidsbanken.configs;


import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/tidsbanken")
@RestController
public class LoginController {
    @GetMapping("/public")
    public String hello1(){
        return "Anyone can access";
    }
    @GetMapping("/public1")
    @PreAuthorize("hasRole('client_admin')")
    public String hello2(){
        return "ADMIN can access";
    }
    @GetMapping("/public2")
    @PreAuthorize("hasRole('client_user')")
    public String hello3(){
        return "USER can access";
    }
    @GetMapping("/public3")
    public String hello4(){
        return "ADMIN/ USER can access";
    }


}
