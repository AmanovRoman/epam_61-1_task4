
package com.epam.spring.hometask.dao;

import com.epam.spring.hometask.domain.Ticket;

public interface TicketDao extends AbstractDomainObjectDao<Ticket> {
    void update(Ticket ticket);
}
