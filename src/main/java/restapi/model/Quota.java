package restapi.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "quota")
public class Quota extends BaseModel implements Serializable {

    public Quota() {
    }

    public Quota(long flightId, long totalQuota, long usedQuota) {

        this.flightId = flightId;
        this.totalQuota = totalQuota;
        this.usedQuota = usedQuota;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "flight_id")
    private long flightId;

    @Column(name = "total_quota")
    private long totalQuota;

    @Column(name = "used_quota")
    private long usedQuota;

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

    public long getTotalQuota() {
        return totalQuota;
    }

    public void setTotalQuota(long totalQuota) {
        this.totalQuota = totalQuota;
    }

    public long getUsedQuota() {
        return usedQuota;
    }

    public void setUsedQuota(long usedQuota) {
        this.usedQuota = usedQuota;
    }

    @Override
    public String toString() {
        return "Quota{" +
                "id=" + id +
                ", flightId=" + flightId +
                ", totalQuota=" + totalQuota +
                ", usedQuota=" + usedQuota +
                '}';
    }
}
