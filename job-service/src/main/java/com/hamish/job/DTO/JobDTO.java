package com.hamish.job.DTO;

import com.hamish.job.external.Company;
import com.hamish.job.external.Review;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
public class JobDTO {
    private Long id;
    private String title;
    private String description;
    private Long minSalary;
    private Long maxSalary;
    private String location;
    private Company company;
    private List<Review> reviews;
}
