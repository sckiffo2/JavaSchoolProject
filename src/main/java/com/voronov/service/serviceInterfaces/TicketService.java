package com.voronov.service.serviceInterfaces;

import com.voronov.dao.DAOinterfaces.TicketDao;
import com.voronov.entities.Passenger;
import com.voronov.entities.Station;
import com.voronov.entities.Ticket;
import com.voronov.entities.Trip;
import com.voronov.dto.TicketScheduleDTO;

import java.time.LocalDate;
import java.util.List;

public interface TicketService {

	/***
	 * retrieve all Ticket entities
	 * @return list of all tickets
	 */
	List<Ticket> findAll();


	/***
	 * retrieve Ticket entity by name
	 * @param id Ticket id field
	 * @return Ticket entity
	 */
	Ticket findById(long id);

	/***
	 * find and retrieve all ticket what belongs ti requested Trip
	 * @param id Trip id
	 * @return list of all tickets what belong to trin trip
	 */
	List<Ticket> findAllTicketsByTripId(long id);

	/***
	 * produces list of TicketScheduleDTO
	 * @param departureStation name of departure Station
	 * @param arrivalStation mane of arriaval Station
	 * @param departureDate date of departure from departure station
	 * @return list of TicketScheduleDTO
	 */
	List<TicketScheduleDTO> findTripsByStationsAndDate(String departureStation, String arrivalStation, String departureDate);

	/***
	 * produce matrix of all places what can be taken on requested trip from departure to arrival Station
	 * @param tripId Trip id
	 * @param departureStationId departure Station id
	 * @param arrivalStationId arrival Station id
	 * @return matrix of free places by wagons
	 */
	List<List<Integer>> findFreePlaces(long tripId, String departureStationId, String arrivalStationId);

	/***
	 * save in DB ticket with empty Passenger field. While place is booked it cannot be taken again.
	 * @param tripId  Trip id
	 * @param departureStationName departure Station name
	 * @param arrivalStationName arrival Station name
	 * @param placeNumber place number for ticket
	 * @param wagonNumber wagon number for ticket
	 */
	void bookTicket(long tripId, String departureStationName, String arrivalStationName, int placeNumber, int wagonNumber);

	/***
	 * check if desired place тще bought or booked
	 * @param trip Trip id
	 * @param wagon wagon number for ticket
	 * @param place place number for ticket
	 * @return return true if place free and can be bought
	 */
	boolean isPlaceFree(Trip trip, int wagon, int place);

	/***
	 * add Passenger in Ticket what was created(booked) early.
	 * @param passenger Passenger entity
	 * @param tripId Trip id
	 * @param wagon wagon number for ticket
	 * @param place place number for ticket
	 */
	void registerPassengerToTrip(Passenger passenger, long tripId, int wagon, int place);

	/***
	 * check if time to departure of desired trip from desired station less than 10min
	 * @param trip Trip
	 * @param departureStationId Station id
	 * @return true if too late and ticket cannot be bought
	 */
	boolean isTooLateToBuyTicket(Trip trip, long departureStationId);

	/***
	 * find and retrieve ticket by trip, wagon and place
	 * @param tripId Trip id
	 * @param wagon wagon number
	 * @param place place number
	 * @return desired Ticket entity
	 */
	Ticket findTicketByTripAndPlace(long tripId, int wagon, int place);

	/***
	 * delete tickets what was booked for more than TicketServiceImpl.TIME_TO_REGISTER_TICKET minutes
	 */
	void deleteLongBookedTickets();

	/***
	 * retrieve all Station entities
	 * @return list of all Stations
	 */
	List<Station> findAllStations();

	void setTicketDao(TicketDao ticketDao);

	void setRouteService(RouteService routeService);

	void setTripService(TripService tripService);

	void setStationService(StationService stationService);

	void setPassengerService(PassengerService passengerService);
}
