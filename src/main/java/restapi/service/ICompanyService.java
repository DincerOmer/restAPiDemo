package restapi.service;

import restapi.model.Company;

import java.util.List;

public interface ICompanyService {

    public List<Company> getAllCompany();

    public Company getCompanyById(Long id);

    public Company getCompanyByName(String name);

    public void saveOrUpdateCompany(Company company);

    public void deleteCompany(Long id);
}
