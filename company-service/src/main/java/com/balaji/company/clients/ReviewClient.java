package com.balaji.company.clients;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name="REVIEW-SERVICE",
url = "{$review-service.url}")
public interface ReviewClient {
    @GetMapping("/reviews/average")
    public Double getAverageRatingForCompany(@RequestParam("companyId") Long companyId);
}
