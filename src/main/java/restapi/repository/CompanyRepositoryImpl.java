package restapi.repository;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import restapi.model.Company;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class CompanyRepositoryImpl implements ICompanyRepository {

    private EntityManager entityManager;

    @Autowired
    public CompanyRepositoryImpl(EntityManager entityManager) {

        this.entityManager = entityManager;
    }

    @Override
    public List<Company> getAllCompany() {

        Session currentSession = entityManager.unwrap(Session.class);

        Query<Company> theQuery =
                currentSession.createQuery("FROM Company", Company.class);

        List<Company> companyList = theQuery.getResultList();

        return companyList;
    }

    @Override
    public Company getCompanyById(Long id) {

        Session currentSession = entityManager.unwrap(Session.class);

        Company company = currentSession.get(Company.class, id);

        return company;
    }

    @Override
    public Company getCompanyByName(String name) {

        Session currentSession = entityManager.unwrap(Session.class);

        Query query= currentSession.createQuery("from Company where name=:name");
        query.setParameter("name", name);

        Company company = (Company) query.uniqueResult();

        return company;
    }

    @Override
    public void saveOrUpdateCompany(Company company) {

        Session currentSession = entityManager.unwrap(Session.class);

        currentSession.saveOrUpdate(company);
    }

    @Override
    public void deleteCompany(Long id) {

        Session currentSession = entityManager.unwrap(Session.class);

        Company company = currentSession.get(Company.class, id);

        if(company == null){
            throw new IllegalArgumentException("Company not found");
        }

        currentSession.delete(company);
    }
}
