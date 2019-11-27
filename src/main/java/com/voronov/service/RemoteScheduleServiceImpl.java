package com.voronov.service;

import com.voronov.dto.RemoteScheduleDto;
import com.voronov.entities.Station;
import com.voronov.entities.Trip;
import com.voronov.entities.TripStation;
import com.voronov.service.serviceInterfaces.RemoteScheduleService;
import com.voronov.service.serviceInterfaces.StationService;
import com.voronov.service.serviceInterfaces.TripService;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Setter
@NoArgsConstructor
public class RemoteScheduleServiceImpl implements RemoteScheduleService {

	@Autowired
	private StationService stationService;
	@Autowired
	private TripService tripService;

	@Override
	public List<RemoteScheduleDto> getSchedule(long id) {
		Station station = stationService.findById(id);

		List<Trip> tripsOfStation = tripService.findTripsByStationId(station.getId());

		List<RemoteScheduleDto> result = new ArrayList<>();
		for (Trip trip : tripsOfStation) {
			boolean isTripActual = false;
			RemoteScheduleDto scheduleRow = new RemoteScheduleDto();
			scheduleRow.setStationName(station.getName());
			scheduleRow.setRouteNumber(trip.getRoute().getNumber());
			scheduleRow.setRouteName(trip.getRoute().getName());
			scheduleRow.setCanceled(trip.isCanceled());
			scheduleRow.setDelay(trip.getDelay());
			TripStation tripStation = trip.getStationsOnTrip().stream()
					.filter(x -> x.getStation().equals(station))
					.findFirst()
					.get();
			if (tripStation.getArrivalTime() != null) {
				LocalDateTime arrivalTime = tripStation.getArrivalTime();
				scheduleRow.setArrival(arrivalTime.toString());
				if (arrivalTime.toLocalDate().isEqual(LocalDate.now()) ||
						arrivalTime.toLocalDate().isEqual(LocalDate.now().plusDays(1))) {
					isTripActual = true;
				}
			}
			if (tripStation.getDepartureTime() != null) {
				LocalDateTime departureTime = tripStation.getDepartureTime();
				scheduleRow.setDeparture(departureTime.toString());
				if (departureTime.toLocalDate().isEqual(LocalDate.now()) ||
						departureTime.toLocalDate().isEqual(LocalDate.now().plusDays(1))) {
					isTripActual = true;
				}
			}
			if (isTripActual) {
				result.add(scheduleRow);
			}
		}
		return result;
	}

	@Override
	public Station getStation(long id) {
		return stationService.findById(id);
	}

	@Override
	public List<Station> getAllStations() {
		return stationService.findAll();
	}
}
