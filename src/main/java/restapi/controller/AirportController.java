package restapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;
import restapi.common.GeneralEnumerationDefinitions;
import restapi.model.Airport;
import restapi.service.IAirportService;

import java.util.List;

@RestController
@EnableAutoConfiguration
@RequestMapping(value = "/api/airport")
public class AirportController {

    @Autowired
    IAirportService airportService;

    @PostMapping("/save")
    public Airport saveOrUpdateAirport(@RequestBody Airport airport) {

        boolean isUpdate = false;
        String responseCode = GeneralEnumerationDefinitions.RestApiResponse.ERROR.toString();
        String responseMessage;

        if(airport.getId() != 0){
            isUpdate = true;
        }

        if (airport.getName() != null && !airport.getName().isEmpty() &&
                airport.getAddress() != null && !airport.getAddress().isEmpty()) {

            airportService.saveOrUpdateAirport(airport);

            responseCode = GeneralEnumerationDefinitions.RestApiResponse.SUCCESS.toString();
            responseMessage = "Airport added";

            if(isUpdate){
                responseMessage = "Airport updated";
            }

        } else {

            responseMessage = "name or address can not null or empty";

        }

        airport.setResponseCode(responseCode);
        airport.setResponseMessage(responseMessage);

        return airport;
    }

    @GetMapping("/list")
    public List<Airport> getAllAirport() {

        return airportService.getAllAirport();
    }

    @GetMapping("/list/byId/{id}")
    public Airport getAirportById(@PathVariable Long id) {

        Airport airport = airportService.getAirportById(id);

        if (airport == null) {

            airport = new Airport();

            airport.setResponseCode(GeneralEnumerationDefinitions.RestApiResponse.ERROR.toString());
            airport.setResponseMessage("Airport not found");

        }

        return airport;
    }

    @GetMapping("/list/byName/{name}")
    public Airport getAirportByName(@PathVariable String name) {

        Airport airport = airportService.getAirportByName(name);

        if (airport == null) {

            airport = new Airport();

            airport.setResponseCode(GeneralEnumerationDefinitions.RestApiResponse.ERROR.toString());
            airport.setResponseMessage("Airport not found");

        }

        return airport;
    }

    @DeleteMapping("/delete/{id}")
    public String deleteAirport(@PathVariable(value = "id") Long id) {

        Airport airport = airportService.getAirportById(id);

        if (airport != null) {

            airportService.deleteAirport(id);

            return "Airport Deleted Successfully id = " + id;

        }

        return "Airport Not Found. id = " + id;
    }
}
