package com.voronov.entities;

import javax.persistence.*;

@Entity
@Table(name = "routes")
public class Route {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false)
    private String id;
    @Column(name = "name")
    private String name;

    //todo непонятно как получать поле
    //start_station_id  определяется из индекса станции в сводной таблице route_station
    // но столбец index_in_route не относится ни к станции, ни к маршруту напрямую
    // как его выводить
    //todo manyTOmany
    @Column(name = "start_station_id")
    private int startStation;
    //todo manyTOmany
    @Column(name = "end_station_id")
    private int endStation;
    @Column(name = "schedule_pattern")
    private String schedulePattern;

    public Route() {
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStartStation() {
        return startStation;
    }

    public void setStartStation(int startStation) {
        this.startStation = startStation;
    }

    public int getEndStation() {
        return endStation;
    }

    public void setEndStation(int endStation) {
        this.endStation = endStation;
    }

    public String getSchedulePattern() {
        return schedulePattern;
    }

    public void setSchedulePattern(String schedulePattern) {
        this.schedulePattern = schedulePattern;
    }

}
