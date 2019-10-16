package com.voronov.entities;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "tickets")
@ToString(callSuper = true)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Ticket extends SuperEntity{

    @Column(name = "passenger_id")
    private int passengerId;

    @Column(name = "trip_id")
    private int tripId;

    @Column(name = "start_station_id")
    private int startStationId;

    @Column(name = "end_station_id")
    private int endStationId;

    @Column(name = "wagon_number")
    private int wagonNumber;

    @Column(name = "seat_number")
    private int seatNumber;

    @Column(name = "price")
    private double price;
}
