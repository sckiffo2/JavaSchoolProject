package com.voronov.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "route_station")
@Getter
@Setter
public class RouteStation extends SuperEntity {

	@ManyToOne
	@JoinColumn(name = "route_id")
	private Route route;

	@ManyToOne
	@JoinColumn(name = "station_id")
	private Station station;

	@Column(name = "arrival_time")
	private Integer arrivalTime;

	@Column(name = "departure_time")
	private Integer departureTime;

	@Column(name = "index_in_route")
	private int indexInRoute;

	public RouteStation(Route route, Station station, Integer arrivalTime, Integer departureTime, int indexInRoute) {
		this.route = route;
		this.station = station;
		this.arrivalTime = arrivalTime;
		this.departureTime = departureTime;
		this.indexInRoute = indexInRoute;
	}

	public RouteStation() {
	}

	public String getArrivalTimeToString() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
		if (arrivalTime != null) {
			LocalTime time = LocalTime.ofSecondOfDay(arrivalTime % 86400);
			return time.format(formatter);
		}
		return "-";
	}

	public String getDepartureTimeToString() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
		if (departureTime != null) {
			LocalTime time = LocalTime.ofSecondOfDay(departureTime % 86400);
			return time.format(formatter);
		}
		return "-";
	}
}
