
package com.epam.spring.hometask.service.domain;

import com.epam.spring.hometask.domain.Ticket;

public interface TicketDao extends AbstractDomainObjectDao<Ticket> {
    void update(Ticket ticket);
}
