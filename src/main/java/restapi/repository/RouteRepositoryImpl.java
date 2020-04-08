package restapi.repository;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import restapi.model.Route;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class RouteRepositoryImpl implements IRouteRepository {

    private EntityManager entityManager;

    @Autowired
    public RouteRepositoryImpl(EntityManager entityManager) {

        this.entityManager = entityManager;
    }

    @Override
    public List<Route> getAllRoute() {

        Session currentSession = entityManager.unwrap(Session.class);

        Query<Route> theQuery =
                currentSession.createQuery("FROM Route", Route.class);

        List<Route> routeList = theQuery.getResultList();

        return routeList;
    }

    @Override
    public Route getRouteById(Long id) {

        Session currentSession = entityManager.unwrap(Session.class);

        Route route = currentSession.get(Route.class, id);

        return route;
    }

    @Override
    public Route getRouteByAirportIds(long departureId, long destinationId) {

        Session currentSession = entityManager.unwrap(Session.class);

        Query query = currentSession.createQuery("from Route where departureId=:departureId and destinationId=:destinationId");
        query.setParameter("departureId", departureId);
        query.setParameter("destinationId", destinationId);

        Route route = (Route) query.uniqueResult();

        return route;
    }

    @Override
    public void saveOrUpdateRoute(Route route) {

        Session currentSession = entityManager.unwrap(Session.class);

        currentSession.saveOrUpdate(route);
    }

    @Override
    public void deleteRoute(Long id) {

        Session currentSession = entityManager.unwrap(Session.class);

        Route route = currentSession.get(Route.class, id);

        if(route == null){
            throw new IllegalArgumentException("Route not found");
        }

        currentSession.delete(route);
    }
}
