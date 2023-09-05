package com.example.tidsbanken.configs;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("api/v1/resources")
public class LoginController {

   @GetMapping("public")
    public ResponseEntity<String> publicEndpoint() {
        return new ResponseEntity<>(
                "ANY one can access it.",
                HttpStatus.OK);
    }

    @GetMapping("restricted")
    public ResponseEntity<String> restrictedEndpoint() {
        return new ResponseEntity<>(
                "A restricted endpoint, accessible only when logged in. Both user and Admin can reach it",
                HttpStatus.OK);
    }

    @GetMapping("admin")
    public ResponseEntity<String> adminEndpoint() {
        return new ResponseEntity<>(
                "Admin endpoint, accessible only for admins.",
                HttpStatus.OK);
    }

}
