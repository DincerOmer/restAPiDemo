package restapi.repository;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import restapi.model.Price;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class PriceRepositoryImpl implements IPriceRepository {

    private EntityManager entityManager;

    @Autowired
    public PriceRepositoryImpl(EntityManager entityManager) {

        this.entityManager = entityManager;
    }

    @Override
    public List<Price> getAllPrice() {

        Session currentSession = entityManager.unwrap(Session.class);

        Query<Price> theQuery =
                currentSession.createQuery("FROM Price", Price.class);

        List<Price> priceList = theQuery.getResultList();

        return priceList;
    }

    @Override
    public Price getPriceById(Long id) {

        Session currentSession = entityManager.unwrap(Session.class);

        Price price = currentSession.get(Price.class, id);

        return price;
    }

    @Override
    public Price getPriceByFlightId(Long flightId) {

        Session currentSession = entityManager.unwrap(Session.class);

        Query query = currentSession.createQuery("from Price where flightId=:flightId and isActv=:isActv");
        query.setParameter("flightId", flightId);
        query.setParameter("isActv", 1l);

        Price price = (Price) query.uniqueResult();

        return price;
    }

    @Override
    public void saveOrUpdatePrice(Price price) {

        Session currentSession = entityManager.unwrap(Session.class);

        currentSession.saveOrUpdate(price);
    }

    @Override
    public void deletePrice(Long id) {

        Session currentSession = entityManager.unwrap(Session.class);

        Price price = currentSession.get(Price.class, id);

        currentSession.delete(price);
    }
}
