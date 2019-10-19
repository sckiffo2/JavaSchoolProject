package com.voronov.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "trips")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Trip extends SuperEntity {

	@Column(name = "start_date")
	private Date startDate;

	@Column(name = "canceled")
	private Boolean canceled;

	@OneToMany
	@JoinColumn(name = "trip_id")
	private List<Ticket> ticketsList;

	@ManyToOne
	private Route route;
}
