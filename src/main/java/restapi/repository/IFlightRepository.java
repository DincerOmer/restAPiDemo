package restapi.repository;

import restapi.model.Flight;

import java.util.List;

public interface IFlightRepository {

    public List<Flight> getAllFlight();

    public Flight getFlightById(Long id);

    public List<Flight> getFlightListByCompanyId(Long companyId);

    public Flight getFlightByCompanyIdRouteIdDate(Long companyId, Long routeId, String date);

    public void saveOrUpdateFlight(Flight flight);

    public void deleteFlight(Long id);
}
