package com.voronov.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;

@ToString
@EqualsAndHashCode
@MappedSuperclass
public abstract class SuperEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(updatable = false, nullable = false)
	@Getter protected int id;

	protected boolean canEqual(final Object other) {
		return other instanceof SuperEntity;
	}

}
