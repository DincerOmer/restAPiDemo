package restapi.modelRestApi;

import restapi.model.*;

public class FlightRest extends BaseModel {

    public FlightRest() {
    }

    public FlightRest(Company company, RouteRest route, Price price, String date) {
        this.company = company;
        this.route = route;
        this.date = date;
    }

    private long id;

    private Company company;

    private RouteRest route;

    private Price price;

    private Quota quota;

    private String date;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public RouteRest getRoute() {
        return route;
    }

    public void setRoute(RouteRest route) {
        this.route = route;
    }

    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }

    public Quota getQuota() {
        return quota;
    }

    public void setQuota(Quota quota) {
        this.quota = quota;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
