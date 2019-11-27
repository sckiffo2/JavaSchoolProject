package com.voronov.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StationScheduleDto implements Comparable<StationScheduleDto>{

	private final String TIME_FORMAT_PATTERN = "HH:mm";

	private String routeNumber;

	private String routeName;

	private LocalDateTime arrival;

	private LocalDateTime departure;

	private boolean canceled;

	private int delay = 0;

	public String getCanceledToString() {
		if (canceled) {
			return "отменен";
		} else {
			return "активен";
		}
	}

	public String getDelayToString() {
		if (canceled) {
			return "отменен";
		}
		if (delay == 0) {
			return "вовремя";
		}
		int delayHours = delay / 60;
		int delayMinutes = delay % 60;
		String result = "";
		switch (delayHours) {
			case 0:
				break;
			case 1:
				result = delayHours + " час";
				break;
			case 2:
			case 3:
			case 4:
				result = delayHours + " часа";
				break;
			default:
				return "более 5 часов";
		}
		if (delayMinutes > 10 && delayMinutes < 21) {
			result += " "  + delayMinutes + " минут";
			return result;
		}
		switch (delayMinutes % 10) {
			case 1:
				result += " "  + delayMinutes + " минута";
				break;
			case 2:
			case 3:
			case 4:
				result += " "  + delayMinutes + " минуты";
				break;
			default:
				result += " "  + delayMinutes + " минут";
				break;
		}
		return result;
	}

	public String getArrivalTimeToString() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(TIME_FORMAT_PATTERN);
		if (arrival != null) {
			return arrival.format(formatter);
		}
		return "-";
	}

	public String getDepartureTimeToString() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(TIME_FORMAT_PATTERN);
		if (departure != null) {
			return departure.format(formatter);
		}
		return "-";
	}

	@Override
	public int compareTo(StationScheduleDto o) {
		if (arrival != null) {
			if (o.arrival != null) {
				return arrival.compareTo(o.arrival);
			} else {
				return arrival.compareTo(o.departure);
			}
		} else {
			if (o.arrival != null) {
				return departure.compareTo(o.arrival);
			} else {
				return departure.compareTo(o.departure);
			}
		}
	}

	@Override
	public String toString() {
		return "StationScheduleDto{" +
				"routeNumber='" + routeNumber + '\'' +
				", routeName='" + routeName + '\'' +
				", arrival=" + arrival +
				", departure=" + departure +
				", canceled=" + canceled +
				'}';
	}
}
