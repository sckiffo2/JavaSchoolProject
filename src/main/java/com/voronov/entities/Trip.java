package com.voronov.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "trips")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Trip extends SuperEntity {

	@Column(name = "start_date")
	private LocalDate startDate;

	@Column(name = "canceled")
	private Boolean canceled;

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
}
