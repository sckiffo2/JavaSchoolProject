package com.voronov.entities;

import com.sun.org.glassfish.gmbal.NameValue;

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
	public int hashCode() {
		int result = id;
		result = 31 * result + (name != null ? name.hashCode() : 0);
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
		return id != that.id && !name.equals(that.name);
	}

	@Override
	public String toString() {
		return "entities.Station{" +
				"id=" + id +
				", name='" + name + '\'' +
				'}';
	}
}
