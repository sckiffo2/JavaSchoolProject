package com.voronov.scheduling;

import com.voronov.service.serviceInterfaces.TicketService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class ScheduleTask {

	private static final Logger logger = LoggerFactory.getLogger(ScheduleTask.class);

	@Autowired
	TicketService ticketService;

	//every 30 seconds (seconds, minutes, hours, day of month, month, day of week, year(optional))
	@Scheduled(cron = "0 0/1 * * * ?")
	public void deleteBookedTicketsSchedule() {
		logger.debug("ScheduledTask: deleting tickets what booked too long.");
		ticketService.deleteLongBookedTickets();
	}

	@Scheduled(cron = "0 0 1 * * ?")
	public void createTripSchedule() {
		//todo auto create trips by schedule pattern of route
	}
}
