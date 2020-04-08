package restapi.service;

import restapi.model.Quota;

import java.util.List;

public interface IQuotaService {

    public List<Quota> getAllQuota();

    public Quota getQuotaById(Long id);

    public Quota getQuotaByFlightId(Long flightId);

    public void saveOrUpdateQuota(Quota quota);

    public void deleteQuota(Long id);
}
