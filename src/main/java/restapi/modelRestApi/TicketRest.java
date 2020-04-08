package restapi.modelRestApi;

import restapi.model.BaseModel;
import restapi.model.Price;

public class TicketRest extends BaseModel {

    public TicketRest() {
    }

    public TicketRest(FlightRest flight, Price price) {
        this.flight = flight;
        this.price = price;
    }

    private long id;

    private FlightRest flight;

    private Price price;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public FlightRest getFlight() {
        return flight;
    }

    public void setFlight(FlightRest flight) {
        this.flight = flight;
    }

    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }
}
