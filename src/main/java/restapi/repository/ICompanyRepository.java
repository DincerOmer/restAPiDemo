package restapi.repository;

import restapi.model.Company;

import java.util.List;

public interface ICompanyRepository {

    public List<Company> getAllCompany();

    public Company getCompanyById(Long id);

    public Company getCompanyByName(String name);

    public void saveOrUpdateCompany(Company company);

    public void deleteCompany(Long id);
}
