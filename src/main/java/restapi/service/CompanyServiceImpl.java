package restapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import restapi.model.Company;
import restapi.repository.ICompanyRepository;

import java.util.List;

@Service
@Transactional
public class CompanyServiceImpl implements ICompanyService {

    @Autowired
    ICompanyRepository repository;

    @Override
    public List<Company> getAllCompany() {

        return repository.getAllCompany();
    }

    @Override
    public Company getCompanyById(Long id) {

        return repository.getCompanyById(id);
    }

    @Override
    public Company getCompanyByName(String name) {

        return repository.getCompanyByName(name);
    }

    @Override
    public void saveOrUpdateCompany(Company company) {

        repository.saveOrUpdateCompany(company);
    }

    @Override
    public void deleteCompany(Long id) {

        repository.deleteCompany(id);
    }
}
