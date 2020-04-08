package restapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;
import restapi.common.GeneralEnumerationDefinitions;
import restapi.common.GeneralUtils;
import restapi.model.*;
import restapi.modelRestApi.FlightRest;
import restapi.modelRestApi.TicketRest;
import restapi.service.*;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

@RestController
@EnableAutoConfiguration
@RequestMapping(value = "/api/ticket")
public class TicketController {

    @Autowired
    ITicketService ticketService;

    @Autowired
    IFlightService flightService;

    @Autowired
    IRouteService routeService;

    @Autowired
    ICompanyService companyService;

    @Autowired
    IAirportService airportService;

    @Autowired
    IQuotaService quotaService;

    @Autowired
    IPriceService priceService;

    @PostMapping("/save")
    public TicketRest saveOrUpdateTicket(@RequestBody TicketRest ticketRest) {

        boolean isUpdate = false;
        String responseCode = GeneralEnumerationDefinitions.RestApiResponse.ERROR.toString();
        String responseMessage;

        if(ticketRest.getId() != 0){
            isUpdate = true;
        }

        if(isFlightAvailable(ticketRest.getFlight())){

            long flightId = ticketRest.getFlight().getId();

            if (isQuotaAvailable(flightId)) {

                if (isPriceAvailable(ticketRest)) {

                    Price price = getTicketPrice(flightId);

                    Ticket ticket;
                    if (isUpdate) {
                        ticket = getTicketById(ticketRest.getId());
                    } else {
                        ticket = new Ticket(flightId, price.getId(), GeneralUtils.generateTicketNo());
                    }

                    ticketService.saveOrUpdateTicket(ticket);

                    ticketRest.setId(ticket.getId());

                    if(!isUpdate){
                        updateFlightQuota(flightId, 1);

                        updateFlightPrice(flightId);
                    }

                    responseCode = GeneralEnumerationDefinitions.RestApiResponse.SUCCESS.toString();

                    if(isUpdate){
                        responseMessage = "Ticket updated";
                    } else {
                        responseMessage = "Ticket added";
                    }

                } else {

                    responseMessage = "Price is false";

                }
            } else {

                responseMessage = "Quota is Full or Flight not found";

            }
        } else {

            responseMessage = "Flight not found";

        }

        ticketRest.setResponseCode(responseCode);
        ticketRest.setResponseMessage(responseMessage);

        return ticketRest;
    }

    @GetMapping("/list")
    public List<Ticket> getAllTicket() {

        return ticketService.getAllTicket();
    }

    @GetMapping("/list/{id}")
    public Ticket getTicketById(@PathVariable Long id) {

        return ticketService.getTicketById(id);
    }

    @GetMapping("/list/byTicketNo/{ticketNo}")
    public Ticket getTicketByTicketNo(@PathVariable Long ticketNo) {

        return ticketService.getTicketByTicketNo(ticketNo);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteTicket(@PathVariable(value = "id") Long id) {

        Ticket ticket = ticketService.getTicketById(id);

        if(ticket != null){

            ticketService.deleteTicket(ticket.getId());

            updateFlightQuota(ticket.getFlightId(), -1);

            /**
             * Burada fiyat da %10 kadar dusurulebilir.
             * **/

            return "Deleted Successfully id = " + id + " and quota +1 increased";
        }

        return "Ticket not found : id = " + id;
    }

    @DeleteMapping("/delete/byTicketNo/{ticketNo}")
    public String deleteTicketByTicketNo(@PathVariable(value = "ticketNo") Long ticketNo) {

        Ticket ticket = ticketService.getTicketByTicketNo(ticketNo);

        if(ticket != null){

            ticketService.deleteTicket(ticket.getId());

            updateFlightQuota(ticket.getFlightId(), -1);

            /**
             * Burada fiyat da %10 kadar dusurulebilir.
             * **/

            return "Deleted Successfully ticketNo = " + ticketNo + " and quota +1 increased";
        }

        return "Ticket not found : ticketNo = " + ticketNo;
    }

    private void updateFlightQuota(long flightId, long updateQuotaCount) {

        Quota quota = quotaService.getQuotaByFlightId(flightId);

        quota.setUsedQuota(quota.getUsedQuota() + updateQuotaCount);

        quotaService.saveOrUpdateQuota(quota);
    }

    private boolean isFlightAvailable(FlightRest flightRest){

        boolean isFlightAvailable = false;

        long companyId = getCompanyIdByName(flightRest.getCompany().getName());

        long routeId = getRouteIdByAirportNames(flightRest.getRoute().getDeparture().getName(), flightRest.getRoute().getDestination().getName());

        Flight flight = flightService.getFlightByCompanyIdRouteIdDate(companyId, routeId, flightRest.getDate());

        if (flight != null) {
            flightRest.setId(flight.getId());
            isFlightAvailable = true;
        }

        return isFlightAvailable;
    }


    private boolean isQuotaAvailable(long flightId) {

        boolean isQuotaAvailable = false;

        Quota quota = quotaService.getQuotaByFlightId(flightId);

        if (quota != null) {
            if (quota.getTotalQuota() > quota.getUsedQuota()) {
                isQuotaAvailable = true;
            }
        }

        return isQuotaAvailable;
    }

    private boolean isPriceAvailable(TicketRest ticketRest) {

        boolean isPriceAvailable = false;

        Price price = getTicketPrice(ticketRest.getFlight().getId());

        if (price != null) {
            if (price.getPriceValue() == ticketRest.getPrice().getPriceValue()) {
                isPriceAvailable = true;
            }
        }

        return isPriceAvailable;
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

    private void updateFlightPrice(long flightId) {

        Quota quota = quotaService.getQuotaByFlightId(flightId);
        if (quota != null) {

            double quotaRatio = quota.getTotalQuota() / 10;

            for (int i = 1; i <= quota.getTotalQuota(); i++) {

                if (quota.getUsedQuota() < quotaRatio) {
                    break;
                } else if (quota.getUsedQuota() == quotaRatio * i) {

                    Price price = priceService.getPriceByFlightId(flightId);

                    price.setIsActv(0);

                    priceService.saveOrUpdatePrice(price);

                    Price newPrice = new Price();

                    newPrice.setFlightId(price.getFlightId());

                    NumberFormat formatter = NumberFormat.getInstance(Locale.US);
                    formatter.setMinimumFractionDigits(2);

                    String priceStr = formatter.format(price.getPriceValue() * 1.1);

                    double formattedPrice = Double.valueOf(priceStr);

                    newPrice.setPriceValue(formattedPrice);
                    newPrice.setIsActv(1);

                    priceService.saveOrUpdatePrice(newPrice);

                    break;

                } else if (quota.getUsedQuota() < quotaRatio * (i + 1)) {
                    break;
                }
            }
        }
    }

    private Price getTicketPrice(long flightId){
        return priceService.getPriceByFlightId(flightId);
    }
}
