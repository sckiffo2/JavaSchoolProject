package com.voronov.entities;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tickets")
@ToString(callSuper = true)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Ticket extends SuperEntity{

	@ManyToOne
    private Passenger passenger;

    @ManyToOne
    private Trip Trip;

    @Column(name = "start_station_id")
    private Long startStationId;

    @Column(name = "end_station_id")
    private Long endStationId;

    @Column(name = "wagon_number")
    private int wagonNumber;

    @Column(name = "seat_number")
    private int seatNumber;

    @Column(name = "price")
    private double price;

	@Column(name = "booked")
    private boolean isBooked;
}
