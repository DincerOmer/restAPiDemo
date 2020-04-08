package restapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;
import restapi.common.GeneralEnumerationDefinitions;
import restapi.model.*;
import restapi.modelRestApi.FlightRest;
import restapi.service.*;

import java.util.List;

@RestController
@EnableAutoConfiguration
@RequestMapping(value = "/api/flight")
public class FlightController {

    @Autowired
    IFlightService flightService;

    @Autowired
    IQuotaService quotaService;

    @Autowired
    IPriceService priceService;

    @Autowired
    IRouteService routeService;

    @Autowired
    ICompanyService companyService;

    @Autowired
    IAirportService airportService;

    @PostMapping("/save")
    public FlightRest saveOrUpdateFlight(@RequestBody FlightRest flightRest) {

        boolean isUpdate = false;
        String responseCode = GeneralEnumerationDefinitions.RestApiResponse.ERROR.toString();
        String responseMessage;

        if(flightRest.getId() != 0){
            isUpdate = true;
        }

        if (flightRest.getCompany() != null && flightRest.getCompany().getName() != null &&
            flightRest.getRoute() != null && flightRest.getRoute().getDeparture() != null && flightRest.getRoute().getDeparture().getName() != null &&
            flightRest.getRoute().getDestination() != null && flightRest.getRoute().getDestination().getName() != null &&
            flightRest.getPrice() != null && flightRest.getPrice().getPriceValue() != 0 &&
            flightRest.getQuota() != null && flightRest.getQuota().getTotalQuota() != 0 &&
            flightRest.getDate() != null && !flightRest.getDate().isEmpty()) {

            boolean isFlightAvailable = true;

            long companyId = getCompanyIdByName(flightRest.getCompany().getName());
            long routeId = getRouteIdByAirportNames(flightRest.getRoute().getDeparture().getName(), flightRest.getRoute().getDestination().getName());

            if(companyId == 0 || routeId == 0){

                isFlightAvailable = false;

            }

            if(isFlightAvailable){

                Flight flight = new Flight(companyId, routeId, flightRest.getDate());

                if (isUpdate) {
                    flight.setId(flightRest.getId());
                }

                flightService.saveOrUpdateFlight(flight);

                flightRest.setId(flight.getId());
                flightRest.getCompany().setId(companyId);
                flightRest.getRoute().setId(routeId);

                savePrice(isUpdate, flightRest);

                saveQuota(isUpdate, flightRest);

                responseCode = GeneralEnumerationDefinitions.RestApiResponse.SUCCESS.toString();

                if(isUpdate){
                    responseMessage = "Flight updated";
                } else {
                    responseMessage = "Flight added";
                }

            } else {

                responseMessage = "Company or Route not found";

            }

        } else {

            responseMessage = "Company, Route, Price, Quota or date can not null or empty";

        }

        flightRest.setResponseCode(responseCode);
        flightRest.setResponseMessage(responseMessage);

        return flightRest;
    }

    @GetMapping("/list")
    public List<Flight> getAllFlight() {

        return flightService.getAllFlight();
    }

    @GetMapping("/list/{id}")
    public Flight getFlightById(@PathVariable Long id) {

        return flightService.getFlightById(id);
    }

    @GetMapping("/list/byCompanyName/{companyName}")
    public List<Flight> getFlightByCompanyName(@PathVariable String companyName) {

        return flightService.getFlightListByCompanyId(getCompanyIdByName(companyName));
    }

    @DeleteMapping("/delete/{id}")
    public String deleteFlight(@PathVariable(value = "id") Long id) {

        flightService.deleteFlight(id);

        return "Deleted Successfully id = " + id;
    }

    private void savePrice(boolean isUpdate, FlightRest flightRest) {

        Price price;

        if(isUpdate){
            price = getPriceByFlightId(flightRest.getId());
            price.setPriceValue(flightRest.getPrice().getPriceValue());
        } else{
            price = new Price(flightRest.getId(), flightRest.getPrice().getPriceValue(), 1);
        }

        priceService.saveOrUpdatePrice(price);
        flightRest.setPrice(price);
    }

    private void saveQuota(boolean isUpdate, FlightRest flightRest) {

        Quota quota;

        if(isUpdate){
            quota = getQuotaByFlightId(flightRest.getId());
            quota.setTotalQuota(flightRest.getQuota().getTotalQuota());
        } else{
            quota = new Quota(flightRest.getId(), flightRest.getQuota().getTotalQuota(), 0);
        }

        quotaService.saveOrUpdateQuota(quota);
        flightRest.setQuota(quota);
    }

    private long getCompanyIdByName(String companyName) {

        long companyId = 0;

        Company company = companyService.getCompanyByName(companyName);

        if (company != null) {
            companyId = company.getId();
        }

        return companyId;
    }

    private long getRouteIdByAirportNames(String departureName, String destinationName) {

        long routeId = 0;

        long departureId = getAirportIdByName(departureName);
        long destinationId = getAirportIdByName(destinationName);

        if(departureId != 0 && destinationId != 0){

            Route route = routeService.getRouteByAirportIds(departureId, destinationId);

            if (route != null) {
                routeId = route.getId();
            }
        }

        return routeId;
    }

    private long getAirportIdByName(String airportName) {

        long airportId = 0;

        Airport airport = airportService.getAirportByName(airportName);

        if (airport != null) {
            airportId = airport.getId();
        }

        return airportId;
    }

    private Price getPriceByFlightId(long flightId){
        return priceService.getPriceByFlightId(flightId);
    }

    private Quota getQuotaByFlightId(long flightId){

        return quotaService.getQuotaByFlightId(flightId);
    }
}
