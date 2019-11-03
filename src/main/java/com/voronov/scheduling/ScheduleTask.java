package com.voronov.scheduling;

import com.voronov.service.serviceInterfaces.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class ScheduleTask {

	@Autowired
	TicketService ticketService;

	//every 30 seconds (seconds, minutes, hours, day of month, month, day of week, year(optional))
	@Scheduled(cron = "0 0/1 * * * ?")
	public void deleteBookedTicketsSchedule() {
		ticketService.deleteLongBookedTickets();
	}

	@Scheduled(cron = "0 0 1 * * ?")
	public void createTripSchedule() {
		//todo auto create trips by schedule pattern of route
		System.out.println("---------------------------------------------------------------------------------");
	}
}
