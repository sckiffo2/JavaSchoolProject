package com.voronov.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Table(name = "trip_station")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TripStation extends SuperEntity {

	@ManyToOne
	private Trip trip;

	@ManyToOne
	private Station station;

	@Column(name = "arrival_time")
	private LocalDateTime arrivalTime;

	@Column(name = "departure_time")
	private LocalDateTime departureTime;

	@Column(name = "index_in_trip")
	private int indexInRoute;
}
