package com.voronov.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "wagons")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Wagon extends SuperEntity{

    @Column(name = "seats_number")
    private int seatsNumber;

    @Column(name = "name")
    private String name;

}
