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
        return "Anyone can access (cont. deploy)";
    }
    @GetMapping("/public-1")
    public String getPublic1(){
        return "USER can access";
    }
    @GetMapping("/public-2")
    public String getPublic2(){
        return "ADMIN can access";
    }


}
