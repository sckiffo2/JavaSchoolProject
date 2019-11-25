package com.voronov.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RemoteScheduleDto {

	private String routeNumber;

	private String routeName;

	private boolean canceled;

	private int delay = 0;

	private String arrival;

	private String departure;

	private String stationName;
}
