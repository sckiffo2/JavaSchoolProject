package com.voronov.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "route_station")
@Getter
@Setter
@NoArgsConstructor
public class RouteStation extends SuperEntity {

	@ManyToOne(targetEntity = Route.class)
	@JoinColumn(name = "route_id")
	private Route route;

	@ManyToOne(targetEntity = Station.class)
	@JoinColumn(name = "station_id")
	private Station station;

	@Column(name = "arrival_time")
	private int arrivalTime;

	@Column(name = "departure_time")
	private int departureTime;

	@Column(name = "index_in_route")
	private Integer indexInRoute;
}
