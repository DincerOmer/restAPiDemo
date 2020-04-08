package restapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import restapi.model.Flight;
import restapi.repository.IFlightRepository;

import java.util.List;

@Service
@Transactional
public class FlightServiceImpl implements IFlightService {

    @Autowired
    IFlightRepository repository;

    @Override
    public List<Flight> getAllFlight() {

        return repository.getAllFlight();
    }

    @Override
    public Flight getFlightById(Long id) {

        return repository.getFlightById(id);
    }

    @Override
    public List<Flight> getFlightListByCompanyId(Long companyId) {

        return repository.getFlightListByCompanyId(companyId);
    }

    @Override
    public Flight getFlightByCompanyIdRouteIdDate(Long companyId, Long routeId, String date) {

        return repository.getFlightByCompanyIdRouteIdDate(companyId, routeId, date);
    }

    @Override
    public void saveOrUpdateFlight(Flight flight) {

        repository.saveOrUpdateFlight(flight);
    }

    @Override
    public void deleteFlight(Long id) {

        repository.deleteFlight(id);
    }
}
