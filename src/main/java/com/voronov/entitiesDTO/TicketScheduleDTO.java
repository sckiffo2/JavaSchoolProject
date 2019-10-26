package com.voronov.entitiesDTO;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TicketScheduleDTO {

	private String routeNumber;

	private String routeName;

	private LocalDateTime arrival;

	private LocalDateTime departure;

	private boolean canceled;

	public String getArrivalTimeString() {
		if (canceled) {
			return "отменен";
		}
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
		return arrival.format(formatter);
	}

	public String getDepartureTimeString() {
		if (canceled) {
			return "отменен";
		}
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
		return departure.format(formatter);
	}

	@Override
	public String toString() {
		return "StationScheduleDTO{" +
				"routeNumber='" + routeNumber + '\'' +
				", routeName='" + routeName + '\'' +
				", arrival=" + arrival +
				", departure=" + departure +
				", canceled=" + canceled +
				'}';
	}
}
