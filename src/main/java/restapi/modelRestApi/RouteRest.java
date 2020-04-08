package restapi.modelRestApi;

import restapi.model.Airport;
import restapi.model.BaseModel;

public class RouteRest extends BaseModel {

    public RouteRest() {
    }

    public RouteRest(Airport departure, Airport destination) {
        this.departure = departure;
        this.destination = destination;
    }

    private long id;

    private Airport departure;

    private Airport destination;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Airport getDeparture() {
        return departure;
    }

    public void setDeparture(Airport departure) {
        this.departure = departure;
    }

    public Airport getDestination() {
        return destination;
    }

    public void setDestination(Airport destination) {
        this.destination = destination;
    }
}
