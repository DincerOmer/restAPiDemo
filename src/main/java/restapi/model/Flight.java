package restapi.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "flight")
public class Flight extends BaseModel implements Serializable {

    public Flight() {
    }

    public Flight(long companyId, long routeId, String date) {
        this.companyId = companyId;
        this.routeId = routeId;
        this.date = date;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "company_id", nullable = false)
    private long companyId;

    @Column(name = "route_id", nullable = false)
    private long routeId;

    @Column(name = "date")
    private String date;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(long companyId) {
        this.companyId = companyId;
    }

    public long getRouteId() {
        return routeId;
    }

    public void setRouteId(long routeId) {
        this.routeId = routeId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Flight{" +
                "id=" + id +
                ", companyId=" + companyId +
                ", routeId=" + routeId +
                ", date='" + date + '\'' +
                '}';
    }
}
