package restapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;
import restapi.common.GeneralEnumerationDefinitions;
import restapi.model.Airport;
import restapi.model.Company;
import restapi.model.Route;
import restapi.modelRestApi.RouteRest;
import restapi.service.IAirportService;
import restapi.service.IRouteService;

import java.util.ArrayList;
import java.util.List;

@RestController
@EnableAutoConfiguration
@RequestMapping(value = "/api/route")
public class RouteController {

    @Autowired
    IRouteService routeService;

    @Autowired
    IAirportService airportService;

    @PostMapping("/save")
    public RouteRest saveOrUpdateRoute(@RequestBody RouteRest routeRest) {

        boolean isUpdate = false;
        String responseCode = GeneralEnumerationDefinitions.RestApiResponse.ERROR.toString();
        String responseMessage;

        if(routeRest.getId() != 0){
            isUpdate = true;
        }

        if (routeRest.getDeparture() != null && routeRest.getDestination() != null) {

            if (routeRest.getDeparture().getName() != null && !routeRest.getDeparture().getName().isEmpty() &&
                routeRest.getDestination().getName() != null && !routeRest.getDestination().getName().isEmpty()) {

                if(!routeRest.getDeparture().getName().equals(routeRest.getDestination().getName())) {

                    boolean isRouteAvailable = false;

                    long departureId = getAirportIdByName(routeRest.getDeparture().getName());
                    long destinationId = getAirportIdByName(routeRest.getDestination().getName());

                    if(departureId != 0 && destinationId != 0){

                        isRouteAvailable = true;

                        routeRest.getDeparture().setId(departureId);
                        routeRest.getDestination().setId(destinationId);

                    }

                    if (isRouteAvailable) {

                        Route route = new Route(departureId, destinationId);

                        if(isUpdate){
                            route.setId(routeRest.getId());
                        }

                        routeService.saveOrUpdateRoute(route);

                        routeRest.setId(route.getId());

                        responseCode = GeneralEnumerationDefinitions.RestApiResponse.SUCCESS.toString();
                        responseMessage = "Route added";

                        if(isUpdate){
                            responseMessage = "Route updated";
                        }

                    } else {

                        responseMessage = "Departure or Destination not found";

                    }

                } else {

                    responseMessage = "Departure name and Destination name can not same";

                }
            } else {

                responseMessage = "Departure name or Destination name can not null or empty";

            }

        } else {

            responseMessage = "Departure or Destination can not null";

        }

        routeRest.setResponseCode(responseCode);
        routeRest.setResponseMessage(responseMessage);

        return routeRest;
    }

    @GetMapping("/list/")
    public List<Route> getAllRoute() {

        return routeService.getAllRoute();
    }

    @GetMapping("/list/byName")
    public List<RouteRest> getAllRouteByName() {

        List<RouteRest> routeRestList = new ArrayList<RouteRest>();

        for(Route route : routeService.getAllRoute()){

            RouteRest routeRest = new RouteRest();
            routeRest.setId(route.getId());

            Airport departure = airportService.getAirportById(route.getDepartureId());
            routeRest.setDeparture(departure);

            Airport destination = airportService.getAirportById(route.getDestinationId());
            routeRest.setDestination(destination);

            routeRestList.add(routeRest);
        }

        return routeRestList;
    }

    @GetMapping("/list/byId/{id}")
    public Route getRouteById(@PathVariable Long id) {

        Route route = routeService.getRouteById(id);

        if (route == null) {

            route = new Route();

            route.setResponseCode(GeneralEnumerationDefinitions.RestApiResponse.ERROR.toString());
            route.setResponseMessage("Route not found");

        }

        return route;
    }

    @GetMapping("/list/byAirportNames/{departureName}/{destinationName}")
    public RouteRest getRouteByAirportNames(@PathVariable String departureName, @PathVariable String destinationName) {

        RouteRest routeRest = new RouteRest();

        Airport departure = airportService.getAirportByName(departureName);
        Airport destination = airportService.getAirportByName(destinationName);

        Route route = null;

        if(departure != null && destination != null){
            route = routeService.getRouteByAirportIds(departure.getId(), destination.getId());
        }

        if (route == null) {

            routeRest.setResponseCode(GeneralEnumerationDefinitions.RestApiResponse.ERROR.toString());
            routeRest.setResponseMessage("Route not found");

        } else {
            routeRest.setId(route.getId());
            routeRest.setDeparture(departure);
            routeRest.setDestination(destination);
        }

        return routeRest;
    }

    @DeleteMapping("/delete/{id}")
    public String deleteRoute(@PathVariable(value = "id") Long id) {

        Route route = routeService.getRouteById(id);

        if (route != null) {

            routeService.deleteRoute(id);

            return "Route Deleted Successfully id = " + id;

        }

        return "Route Not Found. id = " + id;
    }

    private long getAirportIdByName(String airportName) {

        long airportId = 0;

        Airport airport = airportService.getAirportByName(airportName);

        if (airport != null) {
            airportId = airport.getId();
        }

        return airportId;
    }
}
