package restapi.service;

import restapi.model.Ticket;

import java.util.List;

public interface ITicketService {

    public List<Ticket> getAllTicket();

    public Ticket getTicketById(Long id);

    public Ticket getTicketByTicketNo(Long ticketNo);

    public void saveOrUpdateTicket(Ticket route);

    public void deleteTicket(Long id);
}
