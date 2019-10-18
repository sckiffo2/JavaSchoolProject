package com.voronov.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "routes")
@Getter
@Setter
@NoArgsConstructor
public class Route extends SuperEntity {

    @Column(name = "route_number")
	private String routeNumber;

	@Column(name = "name")
    private String name;

    @Column(name = "schedule_pattern")
    private String schedulePattern;

    @OneToMany(mappedBy = "route", fetch = FetchType.LAZY)
    private List<RouteStation> stationsOnRoute = new ArrayList<>();

	public Route(String routeNumber, String name, String schedulePattern) {
		this.routeNumber = routeNumber;
		this.name = name;
		this.schedulePattern = schedulePattern;
	}
}
