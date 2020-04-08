package restapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import restapi.model.Airport;
import restapi.repository.IAirportRepository;

import java.util.List;

@Service
@EnableAutoConfiguration
@Transactional
public class AirportServiceImpl implements IAirportService {

    @Autowired
    IAirportRepository repository;

    @Override
    public List<Airport> getAllAirport() {

        return repository.getAllAirport();
    }

    @Override
    public Airport getAirportById(Long id) {

        return repository.getAirportById(id);
    }

    @Override
    public Airport getAirportByName(String name) {

        return repository.getAirportByName(name);
    }

    @Override
    public void saveOrUpdateAirport(Airport airport) {

        repository.saveOrUpdateAirport(airport);
    }

    @Override
    public void deleteAirport(Long id) {

        repository.deleteAirport(id);
    }
}
