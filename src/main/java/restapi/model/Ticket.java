package restapi.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "ticket")
public class Ticket extends BaseModel implements Serializable {

    public Ticket() {
    }

    public Ticket(long flightId, long priceId, long ticketNo) {

        this.flightId = flightId;
        this.ticketNo = ticketNo;
        this.priceId = priceId;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "flight_id")
    private long flightId;

    @Column(name = "ticket_no")
    private long ticketNo;

    @Column(name = "price_id")
    private long priceId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getFlightId() {
        return flightId;
    }

    public void setFlightId(long flightId) {
        this.flightId = flightId;
    }

    public long getTicketNo() {
        return ticketNo;
    }

    public void setTicketNo(long ticketNo) {
        this.ticketNo = ticketNo;
    }

    public long getPriceId() {
        return priceId;
    }

    public void setPriceId(long priceId) {
        this.priceId = priceId;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", flightId=" + flightId +
                ", ticketNo=" + ticketNo +
                ", priceId=" + priceId +
                '}';
    }
}
