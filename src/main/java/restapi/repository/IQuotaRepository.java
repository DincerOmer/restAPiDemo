package restapi.repository;

import restapi.model.Quota;

import java.util.List;

public interface IQuotaRepository {

    public List<Quota> getAllQuota();

    public Quota getQuotaById(Long id);

    public Quota getQuotaByFlightId(Long flightId);

    public void saveOrUpdateQuota(Quota route);

    public void deleteQuota(Long id);
}
