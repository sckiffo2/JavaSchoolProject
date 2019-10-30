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
public class StationScheduleDTO {

	private final String TIME_FORMAT_PATTERN = "HH:mm";

	private String routeNumber;

	private String routeName;

	private LocalDateTime arrival;

	private LocalDateTime departure;

	private boolean canceled;

	public String getArrivalTimeString() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(TIME_FORMAT_PATTERN);
		if (arrival != null) {
			return arrival.format(formatter);
		}
		return "-";
	}

	public String getDepartureTimeString() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(TIME_FORMAT_PATTERN);
		if (departure != null) {
			return departure.format(formatter);
		}
		return "-";
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
