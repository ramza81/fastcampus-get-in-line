package com.bandiera.getinline.controller.api;

import com.bandiera.getinline.dto.ApiDataResponse;
import com.bandiera.getinline.dto.AdminRequest;
import com.bandiera.getinline.dto.LoginRequest;
import org.springframework.web.bind.annotation.*;

@Deprecated
//@RequestMapping("/api")
//@RestController
public class ApiAuthController {

    @PostMapping("/sign-up")
    public ApiDataResponse<String> signUp(@RequestBody AdminRequest adminRequest) {
        return ApiDataResponse.empty();
    }

    @PostMapping("/login")
    public ApiDataResponse<String> login(@RequestBody LoginRequest loginRequest) {
        return ApiDataResponse.empty();
    }
}
