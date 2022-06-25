package com.bandiera.getinline.controller.api;

import com.bandiera.getinline.dto.APIDataResponse;
import com.bandiera.getinline.dto.AdminRequest;
import com.bandiera.getinline.dto.LoginRequest;
import org.springframework.web.bind.annotation.*;

//@RequestMapping("/api")
//@RestController
public class APIAuthController {

    @PostMapping("/sign-up")
    public APIDataResponse<String> signUp(@RequestBody AdminRequest adminRequest) {
        return APIDataResponse.empty();
    }

    @PostMapping("/login")
    public APIDataResponse<String> login(@RequestBody LoginRequest loginRequest) {
        return APIDataResponse.empty();
    }
}
