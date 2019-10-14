package com.voronov.entities;

import javax.persistence.*;

@Entity
@Table(name = "wagons")
public class Wagon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false)
    private int id;

    @Column(name = "seats_number")
    private int seatsNumber;

    @Column(name = "name")
    private String name;

    public Wagon() {
    }

    public Wagon(int seatsNumber, String name) {
        this.seatsNumber = seatsNumber;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public int getSeatsNumber() {
        return seatsNumber;
    }

    public void setSeatsNumber(int seatsNumber) {
        this.seatsNumber = seatsNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        result = prime * result + (name == null ? 0 : name.hashCode());
        result = prime * result + seatsNumber;

        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Wagon that = (Wagon) obj;
        return id == that.id
                && name.equals(that.name)
                && seatsNumber == that.seatsNumber;
    }

    @Override
    public String toString() {
        return "entities.Wagon{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", seatsNumber='" + seatsNumber +
                '}';
    }
}
