package restapi.repository;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import restapi.model.Quota;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class QuotaRepositoryImpl implements IQuotaRepository {

    private EntityManager entityManager;

    @Autowired
    public QuotaRepositoryImpl(EntityManager entityManager) {

        this.entityManager = entityManager;
    }

    @Override
    public List<Quota> getAllQuota() {

        Session currentSession = entityManager.unwrap(Session.class);

        Query<Quota> query =
                currentSession.createQuery("FROM Quota", Quota.class);

        List<Quota> quotaList = query.getResultList();

        return quotaList;
    }

    @Override
    public Quota getQuotaById(Long id) {

        Session currentSession = entityManager.unwrap(Session.class);

        Quota quota = currentSession.get(Quota.class, id);

        return quota;
    }

    @Override
    public Quota getQuotaByFlightId(Long flightId) {

        Session currentSession = entityManager.unwrap(Session.class);

        Query query= currentSession.createQuery("from Quota where flightId=:flightId");
        query.setParameter("flightId", flightId);

        Quota quota = (Quota) query.uniqueResult();

        return quota;
    }

    @Override
    public void saveOrUpdateQuota(Quota quota) {

        Session currentSession = entityManager.unwrap(Session.class);

        currentSession.saveOrUpdate(quota);
    }

    @Override
    public void deleteQuota(Long id) {

        Session currentSession = entityManager.unwrap(Session.class);

        Quota quota = currentSession.get(Quota.class, id);

        currentSession.delete(quota);
    }
}
