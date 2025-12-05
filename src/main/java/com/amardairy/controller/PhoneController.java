package com.amardairy.controller;

import com.amardairy.service.PhoneVerificationService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/phone")
public class PhoneController {

    private final PhoneVerificationService phoneService;

    public PhoneController(PhoneVerificationService phoneService) {
        this.phoneService = phoneService;
    }

    @GetMapping("/verify")
    public String verifyPhone(@RequestParam String phone) {
        return phoneService.verifyPhone(phone);
    }
}
