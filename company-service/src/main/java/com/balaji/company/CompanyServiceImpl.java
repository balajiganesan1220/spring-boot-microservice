package com.balaji.company;

import com.balaji.company.clients.ReviewClient;
import com.balaji.company.dto.ReviewMessage;
import jakarta.ws.rs.NotFoundException;
import org.springframework.stereotype.Service;



import java.util.List;
import java.util.Optional;
@Service
public class CompanyServiceImpl implements CompanyService {
    private final CompanyRepository companyRepository;
    private final ReviewClient reviewClient;


    public CompanyServiceImpl(CompanyRepository companyRepository, ReviewClient reviewClient) {
        this.companyRepository = companyRepository;

        this.reviewClient = reviewClient;
    }

    @Override
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    @Override
    public Company companyById(Long id) {
        Optional<Company> company=companyRepository.findById(id);
        return company.orElse(null);
    }

    @Override
    public Company createCompany(Company company) {
        if(company!=null)
        {
            return companyRepository.save(company);
        }
        return null;

    }

    @Override
    public Company updateCompany(Long id, Company company) {
        if(id==null || company==null){
            return null;

        }
        Company updatedCompany=companyRepository.findById(id).orElse(null);
        if(updatedCompany!=null) {
            updatedCompany.setName(company.getName());
            updatedCompany.setDescription(company.getDescription());
            return companyRepository.save(updatedCompany);
        }
        else {
            return null;
        }
    }

    @Override
    public Boolean deleteCompany(Long id) {

        if(id==null)
          return false;
        Company company=companyRepository.findById(id).orElse(null);
        if(company==null)
            return false;
        companyRepository.deleteById(id);
        return true;
    }

    @Override
    public void updateCompanyRating(ReviewMessage reviewMessage) {
        Company company=companyRepository.findById((reviewMessage.getCompanyId()))
                .orElseThrow(()->new NotFoundException("Company Not Found "+reviewMessage.getCompanyId() ));

        Double averageRating=reviewClient.getAverageRatingForCompany(reviewMessage.getCompanyId());

        company.setRating(averageRating);
        companyRepository.save(company);
    }
}

