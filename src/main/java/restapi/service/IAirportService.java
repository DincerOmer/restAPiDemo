package restapi.service;

import restapi.model.Airport;

import java.util.List;

public interface IAirportService {

    public List<Airport> getAllAirport();

    public Airport getAirportById(Long id);

    public Airport getAirportByName(String name);

    public void saveOrUpdateAirport(Airport airport);

    public void deleteAirport(Long id);
}
