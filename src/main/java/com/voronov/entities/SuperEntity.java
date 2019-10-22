package com.voronov.entities;

import lombok.EqualsAndHashCode;

import javax.persistence.*;

@EqualsAndHashCode
@MappedSuperclass
public abstract class SuperEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(updatable = false, nullable = false)
	protected Long id;

	public String toString() {
		return "SuperEntity(id=" + this.id + ")";
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
