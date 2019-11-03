package com.voronov.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Getter
@Entity
@Table(name = "trips")
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Trip extends SuperEntity {

	@Column(name = "start_date")
	private LocalDate startDate;

	@Column(name = "canceled")
	private boolean canceled;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
			name = "trip_station",
			joinColumns = {@JoinColumn(name = "trip_id")},
			inverseJoinColumns = {@JoinColumn(name = "station_id")}
	)
	private List<Station> stations;

	@OneToMany(mappedBy = "trip", fetch = FetchType.EAGER)
	private List<TripStation> stationsOnTrip;

	@OneToMany
	@JoinColumn(name = "trip_id")
	private List<Ticket> ticketsList;

	@ManyToOne
	private Route route;

	public Trip(Route route, LocalDate startDate) {
		this.route = route;
		this.startDate = startDate;
	}
}
