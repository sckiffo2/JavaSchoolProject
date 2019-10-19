package com.voronov.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User extends SuperEntity{

	@Column(name = "password")
	private String password;

	@Column(name = "mail")
	private String mail;

	@Column(name = "roleId")
	private Integer roleId;

	@Column(name = "active")
	private Boolean active;
}