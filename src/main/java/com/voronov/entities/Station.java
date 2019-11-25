package com.voronov.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "stations")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Station extends SuperEntity {

	@Column(name = "name")
	String name;

	public Station(long id, String name) {
		super.setId(id);
		this.name = name;
	}
}
