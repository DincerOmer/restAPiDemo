package restapi.repository;

import restapi.model.Airport;

import java.util.List;

public interface IAirportRepository {

    public List<Airport> getAllAirport();

    public Airport getAirportById(Long id);

    public Airport getAirportByName(String name);

    public void saveOrUpdateAirport(Airport airport);

    public void deleteAirport(Long id);
}
