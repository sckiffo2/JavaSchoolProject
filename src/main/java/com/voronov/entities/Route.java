package com.voronov.entities;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "routes")
@ToString(callSuper = true)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Route extends SuperEntity{

    @Column(name = "route_number")
	private String routeNumber;

	@Column(name = "name")
    private String name;

    @Column(name = "schedule_pattern")
    private String schedulePattern;

    @OneToMany(mappedBy = "routeId", fetch = FetchType.LAZY)
    private List<RouteStation> stationsOnRoute = new ArrayList<>();
}
