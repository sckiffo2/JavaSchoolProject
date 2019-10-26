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

	private String routeNumber;

	private String routeName;

	private LocalDateTime arrival;

	private LocalDateTime departure;

	private boolean canceled;

	public String getArrivalTimeString() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
		return arrival.format(formatter);
	}

	public String getDepartureTimeString() {
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
