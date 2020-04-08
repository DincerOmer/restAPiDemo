package restapi.repository;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import restapi.model.Flight;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class FlightRepositoryImpl implements IFlightRepository {

    private EntityManager entityManager;

    @Autowired
    public FlightRepositoryImpl(EntityManager entityManager) {

        this.entityManager = entityManager;
    }

    @Override
    public List<Flight> getAllFlight() {

        Session currentSession = entityManager.unwrap(Session.class);

        Query<Flight> query =
                currentSession.createQuery("FROM Flight", Flight.class);

        List<Flight> flightList = query.getResultList();

        return flightList;
    }

    @Override
    public Flight getFlightById(Long id) {

        Session currentSession = entityManager.unwrap(Session.class);

        Flight flight = currentSession.get(Flight.class, id);

        return flight;
    }

    @Override
    public List<Flight> getFlightListByCompanyId(Long companyId) {

        Session currentSession = entityManager.unwrap(Session.class);

        Query query= currentSession.createQuery("from Flight where companyId=:companyId");
        query.setParameter("companyId", companyId);

        List<Flight> flightList = query.getResultList();

        return flightList;
    }

    @Override
    public Flight getFlightByCompanyIdRouteIdDate(Long companyId, Long routeId, String date) {

        Session currentSession = entityManager.unwrap(Session.class);

        Query query = currentSession.createQuery("from Flight where companyId=:companyId and routeId=:routeId and date=:date");
        query.setParameter("companyId", companyId);
        query.setParameter("routeId", routeId);
        query.setParameter("date", date);

        Flight flight = (Flight) query.uniqueResult();

        return flight;
    }

    @Override
    public void saveOrUpdateFlight(Flight flight) {

        Session currentSession = entityManager.unwrap(Session.class);

        currentSession.saveOrUpdate(flight);
    }

    @Override
    public void deleteFlight(Long id) {

        Session currentSession = entityManager.unwrap(Session.class);

        Flight flight = currentSession.get(Flight.class, id);

        currentSession.delete(flight);
    }
}

