package com.voronov.entities;

import javax.persistence.*;

@Entity
@Table(name = "`Stations`")
public class Station {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "name")
	private String name;

	public Station() {
	}

	public Station(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "entities.Station{" +
				"id=" + id +
				", name='" + name + '\'' +
				'}';
	}
}
