package com.voronov.service;

import com.voronov.dao.DAOinterfaces.StationDao;
import com.voronov.dto.StationScheduleDTO;
import com.voronov.entities.Station;
import com.voronov.entities.Trip;
import com.voronov.service.exceptions.BusinessLogicException;
import com.voronov.service.serviceInterfaces.StationService;
import com.voronov.service.serviceInterfaces.TripService;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Setter
@Service
@NoArgsConstructor
public class StationServiceImpl implements StationService {

	private static final Logger logger = LoggerFactory.getLogger(StationServiceImpl.class);

	@Autowired
	private StationDao stationDao;
	@Autowired
	private TripService tripService;

	@Override
	public Station findById(long id) {
		return stationDao.findById(id);
	}

	@Override
	public Station findByName(String name) {
		return stationDao.findByName(name);
	}

	@Override
	public List<StationScheduleDTO> getScheduleOfStation(String name, LocalDate date) {
		logger.debug("start");
		Station station = findByName(name);
		if (station == null) {
			logger.error("BusinessLogicException Станции с таким названием не существует.");
			throw new BusinessLogicException("Станции с таким названием не существует.");
		}

		List<StationScheduleDTO> result = new ArrayList<>();

		List<Trip> tripsOfStation = tripService.findTripsByStationId(station.getId());

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
			scheduleRow.setCanceled(trip.isCanceled());
			subResult.add(scheduleRow);
		}

		for (StationScheduleDTO row: subResult) {
			if ((row.getArrival() != null && row.getArrival().toLocalDate().equals(date)) ||
					row.getDeparture() != null && row.getDeparture().toLocalDate().equals(date)){
				result.add(row);
			}
		}
		Collections.sort(result);
		logger.debug("end");
		return result;
	}

	@Override
	public boolean isExist(String name) {
		return stationDao.isExist(name);
	}

	@Override
	public void save(String name) {
		logger.debug("");
		if (!isExist(name)) {
			stationDao.save(new Station(name));
		} else {
			throw new BusinessLogicException("Станция с таким названием уже существует");
		}
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