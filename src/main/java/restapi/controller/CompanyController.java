package restapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;
import restapi.common.GeneralEnumerationDefinitions;
import restapi.model.Company;
import restapi.service.ICompanyService;

import java.util.List;

@RestController
@EnableAutoConfiguration
@RequestMapping(value = "/api/company")
public class CompanyController {

    @Autowired
    ICompanyService companyService;

    @PostMapping("/save")
    public Company saveOrUpdateCompany(@RequestBody Company company) {

        boolean isUpdate = false;
        String responseCode = GeneralEnumerationDefinitions.RestApiResponse.ERROR.toString();
        String responseMessage;

        if(company.getId() != 0){
            isUpdate = true;
        }

        if (company.getName() != null && !company.getName().isEmpty() &&
                company.getAddress() != null && !company.getAddress().isEmpty() &&
                company.getTelephone() != null && !company.getTelephone().isEmpty()) {

            companyService.saveOrUpdateCompany(company);

            responseCode = GeneralEnumerationDefinitions.RestApiResponse.SUCCESS.toString();
            responseMessage = "Company added";

            if(isUpdate){
                responseMessage = "Company updated";
            }

        } else {

            responseMessage = "name , address or telephone can not null or empty";

        }

        company.setResponseCode(responseCode);
        company.setResponseMessage(responseMessage);

        return company;
    }

    @GetMapping("/list")
    public List<Company> getAllCompany() {

        return companyService.getAllCompany();
    }

    @GetMapping("/list/byId/{id}")
    public Company getCompanyById(@PathVariable Long id) {

        Company company = companyService.getCompanyById(id);

        if (company == null) {

            company = new Company();

            company.setResponseCode(GeneralEnumerationDefinitions.RestApiResponse.ERROR.toString());
            company.setResponseMessage("Company not found");

        }

        return company;
    }

    @GetMapping("/list/byName/{name}")
    public Company getCompanyByName(@PathVariable String name) {

        Company company = companyService.getCompanyByName(name);

        if (company == null) {

            company = new Company();

            company.setResponseCode(GeneralEnumerationDefinitions.RestApiResponse.ERROR.toString());
            company.setResponseMessage("Company not found");

        }

        return company;
    }

    @DeleteMapping("/delete/{id}")
    public String deleteCompany(@PathVariable(value = "id") Long id) {

        Company company = companyService.getCompanyById(id);

        if (company != null) {

            companyService.deleteCompany(id);

            return "Company Deleted Successfully id = " + id;

        }

        return "Company Not Found. id = " + id;
    }
}
