package restapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import restapi.model.Route;
import restapi.repository.IRouteRepository;

import java.util.List;

@Service
@Transactional
public class RouteServiceImpl implements IRouteService {

    @Autowired
    IRouteRepository repository;

    @Override
    public List<Route> getAllRoute() {

        return repository.getAllRoute();
    }

    @Override
    public Route getRouteById(Long id) {

        return repository.getRouteById(id);
    }

    @Override
    public Route getRouteByAirportIds(long departureId, long destinationId) {
        return repository.getRouteByAirportIds(departureId, destinationId);
    }

    @Override
    public void saveOrUpdateRoute(Route route) {

        repository.saveOrUpdateRoute(route);
    }

    @Override
    public void deleteRoute(Long id) {

        repository.deleteRoute(id);
    }
}

