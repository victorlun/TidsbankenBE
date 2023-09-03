package com.example.tidsbanken.configs;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/tidsbanken")
@RestController
public class LoginController {
    @GetMapping("/public")
    public String getPublic(){
        return "Anyone can access";
    }
    @GetMapping("/private")
    public String getPrivate(){
        return "ADMIN can access";
    }


}
