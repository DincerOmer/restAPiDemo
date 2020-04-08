package restapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import restapi.model.Quota;
import restapi.repository.IQuotaRepository;

import java.util.List;

@Service
@Transactional
public class QuotaServiceImpl implements  IQuotaService {

    @Autowired
    IQuotaRepository repository;

    @Override
    public List<Quota> getAllQuota() {

        return repository.getAllQuota();
    }

    @Override
    public Quota getQuotaById(Long id) {

        return repository.getQuotaById(id);
    }

    @Override
    public Quota getQuotaByFlightId(Long flightId) {

        return repository.getQuotaByFlightId(flightId);
    }

    @Override
    public void saveOrUpdateQuota(Quota quota) {

        repository.saveOrUpdateQuota(quota);
    }

    @Override
    public void deleteQuota(Long id) {

        repository.deleteQuota(id);
    }
}
