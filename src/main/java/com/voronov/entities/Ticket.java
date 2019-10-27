package com.voronov.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "tickets")
@ToString(callSuper = true)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Ticket extends SuperEntity{

	@ManyToOne
    @JoinColumn(name = "passenger_id")
    private Passenger passenger;

    @ManyToOne
    @JoinColumn(name = "trip_id")
    private Trip trip;

    @ManyToOne
    @JoinColumn(name = "start_station_id")
    private Station departureStation;

    @ManyToOne
    @JoinColumn(name = "end_station_id")
    private Station arrivalStation;

    @Column(name = "wagon_number")
    private int wagonNumber;

    @Column(name = "seat_number")
    private int seatNumber;

    @Column(name = "price")
    private double price;

	@Column(name = "booked")
    private boolean isBooked;
}
