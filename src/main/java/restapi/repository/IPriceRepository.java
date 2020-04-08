package restapi.repository;

import restapi.model.Price;

import java.util.List;

public interface IPriceRepository {

    public List<Price> getAllPrice();

    public Price getPriceById(Long id);

    public Price getPriceByFlightId(Long flightId);

    public void saveOrUpdatePrice(Price price);

    public void deletePrice(Long id);
}
