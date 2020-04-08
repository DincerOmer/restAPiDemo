package restapi.repository;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Repository;
import restapi.model.Airport;

import javax.persistence.EntityManager;
import java.util.List;

@Configurable
@Repository
public class AirportRepositoryImpl implements IAirportRepository {

    private EntityManager entityManager;

    @Autowired
    public AirportRepositoryImpl(EntityManager entityManager) {

        this.entityManager = entityManager;
    }

    @Override
    public List<Airport> getAllAirport() {

        Session currentSession = entityManager.unwrap(Session.class);
        Query<Airport> theQuery =
                currentSession.createQuery("FROM Airport", Airport.class);

        List<Airport> airportList = theQuery.getResultList();

        return airportList;
    }

    @Override
    public Airport getAirportById(Long id) {

        Session currentSession = entityManager.unwrap(Session.class);

        Airport airport = currentSession.get(Airport.class, id);

        return airport;
    }

    @Override
    public Airport getAirportByName(String name) {

        Session currentSession = entityManager.unwrap(Session.class);

        Query query = currentSession.createQuery("from Airport where name=:name");
        query.setParameter("name", name);

        Airport airport = (Airport) query.uniqueResult();

        return airport;
    }

    @Override
    public void saveOrUpdateAirport(Airport airport) {

        Session currentSession = entityManager.unwrap(Session.class);

        currentSession.saveOrUpdate(airport);
    }

    @Override
    public void deleteAirport(Long id) {

        Session currentSession = entityManager.unwrap(Session.class);

        Airport airport = currentSession.get(Airport.class, id);

        if(airport == null){
            throw new IllegalArgumentException("Airport not found");
        }

        currentSession.delete(airport);
    }
}
