package restapi.repository;

import restapi.model.Ticket;

import java.util.List;

public interface ITicketRepository {

    public List<Ticket> getAllTicket();

    public Ticket getTicketById(Long id);

    public Ticket getTicketByTicketNo(Long ticketNo);

    public void saveOrUpdateTicket(Ticket ticket);

    public void deleteTicket(Long id);
}
