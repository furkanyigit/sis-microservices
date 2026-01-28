package com.furkan.enrollmentservice.client;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "payment-service")
public interface PaymentClient {

    @GetMapping("/payments/check")
    boolean checkPayment(@RequestParam Long studentId, @RequestParam String course);
}