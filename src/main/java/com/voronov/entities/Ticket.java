package com.voronov.entities;

import javax.persistence.*;

@Entity
@Table(name = "tickets")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false)
    private int id;
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
