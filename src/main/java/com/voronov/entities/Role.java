package com.voronov.entities;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "roles")

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Role extends SuperEntity {

	@Column(name = "role")
	private String role;

	@Override
	public String toString() {
		return "role";
	}
}
