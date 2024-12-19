package com.balaji.company;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/companies")
public class companyController {
    private final CompanyService companyService;

    public companyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping
    public ResponseEntity<List<Company>> getAllCompanies(){
        List<Company> companies=companyService.getAllCompanies();
        if(companies.isEmpty())
          return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        else 
            return new ResponseEntity<>(companies,HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Company> companyById(@PathVariable Long id){
        Company company=companyService.companyById(id);
        if(company!=null)
            return new ResponseEntity<>(company,HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PostMapping
    public ResponseEntity<String> createCompany(@RequestBody Company company) {
        Company createdCompany = companyService.createCompany(company);
        if (createdCompany != null) {
            return new ResponseEntity<>("Successfully Created", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Not Created", HttpStatus.BAD_REQUEST);
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<String> updateCompany(@PathVariable Long id,@RequestBody Company company){
        Company updatedCompany=companyService.updateCompany(id,company);
        if(updatedCompany!=null)
            return new ResponseEntity<>("Company Successfully Updated",HttpStatus.OK);
        else
            return new ResponseEntity<>("Invalid company or id",HttpStatus.NOT_FOUND);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCompany(@PathVariable Long id){
        Boolean isDelete=companyService.deleteCompany(id);
        if(isDelete)
            return new ResponseEntity<>("Company Successfully Deleted",HttpStatus.OK);
        else
            return new ResponseEntity<>("Company Not Found",HttpStatus.NOT_FOUND);
    }

}
