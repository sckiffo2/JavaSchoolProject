package com.voronov;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

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
