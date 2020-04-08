package restapi.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "route")
public class Route extends BaseModel implements Serializable {

    public Route() {
    }

    public Route(long departureId, long destinationId) {
        this.departureId = departureId;
        this.destinationId = destinationId;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "departure_id", nullable = false)
    private long departureId;

    @Column(name = "destination_id", nullable = false)
    private long destinationId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getDepartureId() {
        return departureId;
    }

    public void setDepartureId(long departureId) {
        this.departureId = departureId;
    }

    public long getDestinationId() {
        return destinationId;
    }

    public void setDestinationId(long destinationId) {
        this.destinationId = destinationId;
    }

    @Override
    public String toString() {
        return "Route{" +
                "id=" + id +
                ", departureId=" + departureId +
                ", destinationId=" + destinationId +
                '}';
    }
}
