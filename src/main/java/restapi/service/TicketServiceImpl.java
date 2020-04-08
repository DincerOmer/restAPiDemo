package restapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import restapi.model.Ticket;
import restapi.repository.ITicketRepository;

import java.util.List;

@Service
@Transactional
public class TicketServiceImpl implements ITicketService {

    @Autowired
    ITicketRepository repository;

    @Override
    public List<Ticket> getAllTicket() {

        return repository.getAllTicket();
    }

    @Override
    public Ticket getTicketById(Long id) {

        return repository.getTicketById(id);
    }

    @Override
    public Ticket getTicketByTicketNo(Long ticketNo) {

        return repository.getTicketByTicketNo(ticketNo);
    }

    @Override
    public void saveOrUpdateTicket(Ticket ticket) {

        repository.saveOrUpdateTicket(ticket);
    }

    @Override
    public void deleteTicket(Long id) {

        repository.deleteTicket(id);
    }
}

