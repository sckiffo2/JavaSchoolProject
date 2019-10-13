package com.voronov.entities;

import javax.persistence.*;

@Entity
@Table(name = "stations")
public class Station {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(updatable = false, nullable = false)
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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + (name == null ? 0 : name.hashCode());
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
		Station that = (Station) obj;
		return id == that.id && name.equals(that.name);
	}

	@Override
	public String toString() {
		return "entities.Station{" +
				"id=" + id +
				", name='" + name + '\'' +
				'}';
	}
}
