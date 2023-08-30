package com.example.tidsbanken.keycloak;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("api/v1/resources")
public class ResourceController {
    @GetMapping("public")
    public ResponseEntity getPublic(){
        ResponseMessage message = new ResponseMessage();
        message.setMessage("Public resources");
        return ResponseEntity.ok(message);
    }

}
