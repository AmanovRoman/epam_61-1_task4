package com.epam.spring.hometask.aspects;

import com.epam.spring.hometask.domain.Event;
import com.epam.spring.hometask.domain.Ticket;
import com.epam.spring.hometask.domain.information.CommonInformation;
import com.epam.spring.hometask.service.CommonInfoService;
import com.epam.spring.hometask.service.EventService;
import com.epam.spring.hometask.service.ScheduledService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Aspect
public class CounterAspect {
    @Autowired
    ApplicationContext context;
    @Autowired
    ScheduledService scheduledService;
    @Autowired
    EventService eventService;
    @Autowired
    CommonInfoService commonInfoService;

    @AfterReturning(
            value = "execution(* *.getEventByName(..))",
            returning = "retVal"
    )
    public void countEventAccess(Event retVal) {
        if (retVal != null) {
            CommonInformation info = getInfo(retVal);
            commonInfoService.increaseAccessedByName(info);
        }
    }


    @Around("execution(* *.getTicketBasePrice(..))")
    public Object countPriceQuery(ProceedingJoinPoint joinPoint) throws Throwable {
        Object o = joinPoint.proceed();
        Event event = (Event) joinPoint.getThis();
        CommonInformation info = getInfo(event);
        commonInfoService.increasePriceQueried(info);
        return o;
    }
/**/
    @AfterReturning(
            value = "execution(* *.bookTickets(..)))",
            returning = "retVal"
    )
    public void countTicketBooking(List<Ticket> retVal) {
        retVal.forEach((ticket) -> {
            Event event = this.eventService.getEventById(
                    scheduledService.getScheduledById(
                            ticket.getScheduledEventId()).getEventId());
            if (event != null) {
                CommonInformation info = getInfo(event);
                commonInfoService.increaseTicketBooked(info);
            }
        });
    } /**/

    private CommonInformation getInfo(Event event) {
        CommonInformation info = commonInfoService.findByEventId(event.getId());
        if (info == null) {
            info = (CommonInformation) context.getBean("commonInformation");
            info.setEventId(event.getId());
            info.setTicketsBookedCounter(0);
            info.setAccessedByNameCounter(0);
            info.setPriceQueriedCounter(0);
            info.setId(commonInfoService.saveInfo(info));
        }
        return info;
    }
}