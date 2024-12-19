package com.balaji.company;



import com.balaji.company.dto.ReviewMessage;

import java.io.FileNotFoundException;
import java.util.List;

public interface CompanyService {
    public List<Company> getAllCompanies();

    public Company companyById(Long id);

    Company createCompany(Company company);

    Company updateCompany(Long id, Company company);

    Boolean deleteCompany(Long id);


    void updateCompanyRating(ReviewMessage reviewMessage) throws FileNotFoundException;

}
