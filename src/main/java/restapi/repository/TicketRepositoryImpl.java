package restapi.repository;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import restapi.model.Ticket;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class TicketRepositoryImpl implements ITicketRepository {

    private EntityManager entityManager;

    @Autowired
    public TicketRepositoryImpl(EntityManager entityManager) {

        this.entityManager = entityManager;
    }

    @Override
    public List<Ticket> getAllTicket() {

        Session currentSession = entityManager.unwrap(Session.class);

        Query<Ticket> query =
                currentSession.createQuery("FROM Ticket", Ticket.class);

        List<Ticket> ticketList = query.getResultList();

        return ticketList;
    }

    @Override
    public Ticket getTicketById(Long id) {

        Session currentSession = entityManager.unwrap(Session.class);

        Ticket ticket = currentSession.get(Ticket.class, id);

        return ticket;
    }

    @Override
    public Ticket getTicketByTicketNo(Long ticketNo) {

        Session currentSession = entityManager.unwrap(Session.class);

        Query query= currentSession.createQuery("from Ticket where ticketNo=:ticketNo");
        query.setParameter("ticketNo", ticketNo);

        Ticket ticket = (Ticket) query.uniqueResult();

        return ticket;
    }

    @Override
    public void saveOrUpdateTicket(Ticket ticket) {

        Session currentSession = entityManager.unwrap(Session.class);

        currentSession.saveOrUpdate(ticket);
    }

    @Override
    public void deleteTicket(Long id) {

        Session currentSession = entityManager.unwrap(Session.class);

        Ticket ticket = currentSession.get(Ticket.class, id);

        currentSession.delete(ticket);
    }
}
