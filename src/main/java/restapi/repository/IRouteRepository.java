package restapi.repository;

import restapi.model.Route;

import java.util.List;

public interface IRouteRepository {

    public List<Route> getAllRoute();

    public Route getRouteById(Long id);

    public Route getRouteByAirportIds(long departureId, long destinationId);

    public void saveOrUpdateRoute(Route route);

    public void deleteRoute(Long id);
}
