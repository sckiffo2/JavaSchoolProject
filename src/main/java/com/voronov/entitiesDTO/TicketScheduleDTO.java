package com.voronov.entitiesDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TicketScheduleDTO {

    private final String TIME_FORMAT_PATTERN = "dd.MM.uuuu HH:mm";

	private long tripId;

	private String routeNumber;

	private String routeName;

	private LocalDateTime arrival;

	private LocalDateTime departure;

	private boolean canceled;

	private String departureStation;

	private String arrivalStation;

	public String getArrivalTimeString() {
		if (canceled) {
			return "отменен";
		}
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(TIME_FORMAT_PATTERN);
		return arrival.format(formatter);
	}

	public String getDepartureTimeString() {
		if (canceled) {
			return "отменен";
		}
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(TIME_FORMAT_PATTERN);
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
