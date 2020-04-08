package restapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import restapi.model.Price;
import restapi.repository.IPriceRepository;

import java.util.List;

@Service
@Transactional
public class PriceServiceImpl implements IPriceService {

    @Autowired
    IPriceRepository repository;

    @Override
    public List<Price> getAllPrice() {
        return repository.getAllPrice();
    }

    @Override
    public Price getPriceById(Long id) {
        return repository.getPriceById(id);
    }

    @Override
    public Price getPriceByFlightId(Long flightId) {
        return repository.getPriceByFlightId(flightId);
    }

    @Override
    public void saveOrUpdatePrice(Price price) {
        repository.saveOrUpdatePrice(price);
    }

    @Override
    public void deletePrice(Long id) {
        repository.deletePrice(id);
    }
}