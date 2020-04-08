package restapi.service;

import restapi.model.Price;

import java.util.List;

public interface IPriceService {

    public List<Price> getAllPrice();

    public Price getPriceById(Long id);

    public Price getPriceByFlightId(Long flightId);

    public void saveOrUpdatePrice(Price price);

    public void deletePrice(Long id);
}
