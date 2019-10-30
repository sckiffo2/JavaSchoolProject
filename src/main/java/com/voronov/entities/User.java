package com.voronov.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
public class User extends SuperEntity{

	@Column(name = "username")
	private String username;

	@Column(name = "password")
	private String password;

	@Column(name = "mail")
	private String mail;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "users_roles",
			joinColumns = { @JoinColumn(name = "user_id") },
			inverseJoinColumns = { @JoinColumn(name = "role_id") })
	private List<Role> userRoles;

	@Column(name = "active")
	private Boolean active;

	public User(String username, String password, String mail) {
		this.username = username;
		this.password = password;
		this.mail = mail;
	}
}
