package com.amardairy.serviceImpl;

import com.amardairy.service.PhoneVerificationService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PhoneVerificationServiceImpl implements PhoneVerificationService {

    @Value("${abstractapi.phone.api-key}")
    private String abstractApiKey;

    @Value("${abstractapi.phone.url}")
    private String apiUrl;

    private final RestTemplate restTemplate = new RestTemplate();

    public String verifyPhone(String phone) {

        String url = apiUrl + "?api_key=" + abstractApiKey + "&phone=" + phone;

        return restTemplate.getForObject(url, String.class);
    }
}
