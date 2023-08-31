package com.example.tidsbanken.configs;

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
        message.setMessage("This endpoint is public");
        return ResponseEntity.ok(message);
    }
    @GetMapping("/protected")
    public ResponseEntity getPrivate(){
        ResponseMessage message = new ResponseMessage();
        message.setMessage("This endpoint is public");
        return ResponseEntity.ok(message);
    }

}
