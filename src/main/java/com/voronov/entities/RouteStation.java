package com.voronov.entities;

import lombok.Data;

import javax.persistence.*;
import java.time.Duration;


@Entity
@Table(name = "route_station")
@Data
public class RouteStation {

	@ManyToOne
	@JoinColumn(name = "route_id")
	private Integer routeId;

	@ManyToOne
	@JoinColumn(name = "station_id")
	private Integer stationId;

	@Column(name = "arrival_time",
			columnDefinition = "interval")
	private Duration arrivalTime;

	@Column(name = "departure_time",
			columnDefinition = "interval")
	private Duration departureTime;

	@Column(name = "index_in_route")
	private Integer indexInRoute;
}
