package com.voronov.service;

import com.voronov.StationScheduleDTO;
import com.voronov.dao.DAOinterfaces.StationDao;
import com.voronov.entities.Station;
import com.voronov.entities.Trip;
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

@Setter
@Service
@NoArgsConstructor
public class StationServiceImpl implements StationService {

	@Autowired
	private StationDao stationDao;
	@Autowired
	private TripService tripService;

	@Override
	public Station findById(int id) {
		return stationDao.findById(id);
	}

	@Override
	public Station findByName(String name) {
		return stationDao.findByName(name);
	}

	@Override
	public List<StationScheduleDTO> getScheduleOfStation(long id, LocalDate date) {

		List<Trip> tripsOfStation = tripService.findTripsByStationId(id);

		List<StationScheduleDTO> subResult = new ArrayList<>();
		for (Trip trip : tripsOfStation) {
			StationScheduleDTO scheduleRow = new StationScheduleDTO();
			scheduleRow.setRouteNumber(trip.getRoute().getNumber());
			scheduleRow.setRouteName(trip.getRoute().getName());
			if (trip.getRoute().getStationsOnRoute().get(0).getArrivalTime() != null) {
				LocalDateTime arrivalTime = trip.getStartDate().atStartOfDay();
				arrivalTime = arrivalTime.plusSeconds(trip.getRoute().getStationsOnRoute().get(0).getArrivalTime());
				scheduleRow.setArrival(arrivalTime);
			}
			if (trip.getRoute().getStationsOnRoute().get(0).getDepartureTime() != null) {
				LocalDateTime departureTime = trip.getStartDate().atStartOfDay();
				departureTime = departureTime.plusSeconds(trip.getRoute().getStationsOnRoute().get(0).getDepartureTime());
				scheduleRow.setDeparture(departureTime);
			}
			scheduleRow.setCanceled(trip.getCanceled());
			subResult.add(scheduleRow);
		}

		List<StationScheduleDTO> result = new ArrayList<>();
		for (StationScheduleDTO row: subResult) {
			if ((row.getArrival() != null && row.getArrival().toLocalDate().equals(date)) ||
					row.getDeparture() != null && row.getDeparture().toLocalDate().equals(date)){
				result.add(row);
			}
		}

		return result;
	}

	@Override
	public void save(Station station) {
		stationDao.save(station);
	}

	@Override
	public void update(Station station) {
		stationDao.update(station);
	}

	@Override
	public void delete(int id) {
		Station deleteStation = stationDao.findById(id);
		stationDao.delete(deleteStation);
	}

	@Override
	public List<Station> findAll() {
		return stationDao.findAll();
	}

}