package com.balaji.company.messaging;


import com.balaji.company.Company;
import com.balaji.company.CompanyRepository;
import com.balaji.company.CompanyService;
import com.balaji.company.dto.ReviewMessage;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;

@Service
public class ReviewMessageConsumer{
    private final CompanyService companyService;

    public ReviewMessageConsumer(CompanyService companyService) {
        this.companyService = companyService;
    }

    @RabbitListener(queues = "companyRatingQueue")
    public void consumeMessage(ReviewMessage reviewMessage) throws FileNotFoundException {
        companyService.updateCompanyRating(reviewMessage);

    }
}
