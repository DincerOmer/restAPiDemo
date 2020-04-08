package restapi.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "price")
public class Price extends BaseModel implements Serializable {

    public Price() {
    }

    public Price(long flightId, double priceValue, long isActv) {
        this.flightId = flightId;
        this.priceValue = priceValue;
        this.isActv = isActv;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "flight_id")
    private long flightId;

    @Column(name = "value")
    private double priceValue;

    @Column(name = "is_actv")
    private long isActv;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getPriceValue() {
        return priceValue;
    }

    public void setPriceValue(double priceValue) {
        this.priceValue = priceValue;
    }

    public long getFlightId() {
        return flightId;
    }

    public void setFlightId(long flightId) {
        this.flightId = flightId;
    }

    public long getIsActv() {
        return isActv;
    }

    public void setIsActv(long isActv) {
        this.isActv = isActv;
    }

    @Override
    public String toString() {
        return "Price{" +
                "id=" + id +
                ", flightId=" + flightId +
                ", priceValue=" + priceValue +
                ", isActv=" + isActv +
                '}';
    }
}
