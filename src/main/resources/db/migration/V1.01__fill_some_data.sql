INSERT INTO public.stations (name) VALUES ('Москва Октябрьская');
INSERT INTO public.stations (name) VALUES ('Москва, Ярославский в.');
INSERT INTO public.stations (name) VALUES ('Москва, Казанский в.');
INSERT INTO public.stations (name) VALUES ('Санкт-Петербург-Главн.');
INSERT INTO public.stations (name) VALUES ('Тосно');
INSERT INTO public.stations (name) VALUES ('Чудово');
INSERT INTO public.stations (name) VALUES ('Окуловка');
INSERT INTO public.stations (name) VALUES ('Угловка');
INSERT INTO public.stations (name) VALUES ('Бологое');
INSERT INTO public.stations (name) VALUES ('Тверь');
INSERT INTO public.stations (name) VALUES ('Решетниково');
INSERT INTO public.stations (name) VALUES ('Поварово');
INSERT INTO public.stations (name) VALUES ('Вышний Волочёк');

INSERT INTO public.routes (route_number, name, schedule_pattern) VALUES ('279А', 'Санкт-Петербург → Москва', 'WEEK;1;5');
INSERT INTO public.routes (route_number, name, schedule_pattern) VALUES ('128А', 'Москва → Санкт-Петербург', 'WEEK;2;4');

INSERT INTO public.route_station (route_id, station_id, arrival_time, departure_time, index_in_route) VALUES (1, 4, null, 2400, 1);
INSERT INTO public.route_station (route_id, station_id, arrival_time, departure_time, index_in_route) VALUES (1, 9, 15120, 17220, 2);
INSERT INTO public.route_station (route_id, station_id, arrival_time, departure_time, index_in_route) VALUES (1, 13, 19320, 19380, 3);
INSERT INTO public.route_station (route_id, station_id, arrival_time, departure_time, index_in_route) VALUES (1, 10, 27000, 27120, 4);
INSERT INTO public.route_station (route_id, station_id, arrival_time, departure_time, index_in_route) VALUES (1, 1, 36420, null, 5);
INSERT INTO public.route_station (route_id, station_id, arrival_time, departure_time, index_in_route) VALUES (2, 1, null, 5100, 1);
INSERT INTO public.route_station (route_id, station_id, arrival_time, departure_time, index_in_route) VALUES (2, 10, 11460, 11580, 2);
INSERT INTO public.route_station (route_id, station_id, arrival_time, departure_time, index_in_route) VALUES (2, 13, 15900, 16020, 3);
INSERT INTO public.route_station (route_id, station_id, arrival_time, departure_time, index_in_route) VALUES (2, 9, 17820, 18840, 4);
INSERT INTO public.route_station (route_id, station_id, arrival_time, departure_time, index_in_route) VALUES (2, 7, 21720, 21840, 5);
INSERT INTO public.route_station (route_id, station_id, arrival_time, departure_time, index_in_route) VALUES (2, 4, 40260, null, 6);

INSERT INTO public.trips (start_date, canceled, route_id) VALUES ('2019-11-22', false, 1);
INSERT INTO public.trips (start_date, canceled, route_id) VALUES ('2019-11-22', false, 2);
INSERT INTO public.trips (start_date, canceled, route_id) VALUES ('2019-11-23', false, 1);
INSERT INTO public.trips (start_date, canceled, route_id) VALUES ('2019-11-23', false, 2);
INSERT INTO public.trips (start_date, canceled, route_id) VALUES ('2019-11-24', false, 1);
INSERT INTO public.trips (start_date, canceled, route_id) VALUES ('2019-11-24', false, 2);
INSERT INTO public.trips (start_date, canceled, route_id) VALUES ('2019-11-25', false, 1);
INSERT INTO public.trips (start_date, canceled, route_id) VALUES ('2019-11-25', false, 2);

INSERT INTO public.trip_station (trip_id, station_id, arrival_time, departure_time, index_in_trip) VALUES (1, 4, null, '2019-10-22 00:40:00.000000', 1);
INSERT INTO public.trip_station (trip_id, station_id, arrival_time, departure_time, index_in_trip) VALUES (1, 9, '2019-10-22 04:12:00.000000', '2019-10-22 04:47:00.000000', 2);
INSERT INTO public.trip_station (trip_id, station_id, arrival_time, departure_time, index_in_trip) VALUES (1, 13, '2019-10-22 05:22:00.000000', '2019-10-22 05:23:00.000000', 3);
INSERT INTO public.trip_station (trip_id, station_id, arrival_time, departure_time, index_in_trip) VALUES (1, 10, '2019-10-22 07:30:00.000000', '2019-10-22 07:32:00.000000', 4);
INSERT INTO public.trip_station (trip_id, station_id, arrival_time, departure_time, index_in_trip) VALUES (1, 1, '2019-10-22 10:07:00.000000', null, 5);
INSERT INTO public.trip_station (trip_id, station_id, arrival_time, departure_time, index_in_trip) VALUES (2, 1, null, '2019-10-22 01:25:00.000000', 1);
INSERT INTO public.trip_station (trip_id, station_id, arrival_time, departure_time, index_in_trip) VALUES (2, 10, '2019-10-22 03:11:00.000000', '2019-10-22 03:13:00.000000', 2);
INSERT INTO public.trip_station (trip_id, station_id, arrival_time, departure_time, index_in_trip) VALUES (2, 13, '2019-10-22 04:25:00.000000', '2019-10-22 04:27:00.000000', 3);
INSERT INTO public.trip_station (trip_id, station_id, arrival_time, departure_time, index_in_trip) VALUES (2, 9, '2019-10-22 04:57:00.000000', '2019-10-22 05:14:00.000000', 4);
INSERT INTO public.trip_station (trip_id, station_id, arrival_time, departure_time, index_in_trip) VALUES (2, 7, '2019-10-22 06:02:00.000000', '2019-10-22 06:04:00.000000', 5);
INSERT INTO public.trip_station (trip_id, station_id, arrival_time, departure_time, index_in_trip) VALUES (2, 4, '2019-10-22 11:11:00.000000', null, 6);

INSERT INTO public.passengers (first_name, last_name, birth_date, gender) VALUES ('Петр', 'Иванов', '1950-01-01', 'M');
INSERT INTO public.passengers (first_name, last_name, birth_date, gender) VALUES ('Иван', 'Петров', '1950-01-01', 'M');
INSERT INTO public.passengers (first_name, last_name, birth_date, gender) VALUES ('Александр', 'Жданов', '1950-01-01', 'M');
INSERT INTO public.passengers (first_name, last_name, birth_date, gender) VALUES ('Василий', 'Теркин', '1950-01-01', 'M');
INSERT INTO public.passengers (first_name, last_name, birth_date, gender) VALUES ('Александр', 'Цекало', '1950-01-01', 'M');
INSERT INTO public.passengers (first_name, last_name, birth_date, gender) VALUES ('Олег', 'Газманов', '1950-01-01', 'M');

INSERT INTO public.tickets (passenger_id, trip_id, start_station_id, end_station_id, wagon_number, seat_number, booked, price) VALUES (1, 1, 4, 1, 1, 1, true, 5000);
INSERT INTO public.tickets (passenger_id, trip_id, start_station_id, end_station_id, wagon_number, seat_number, booked, price) VALUES (2, 1, 4, 1, 1, 2, true, 5000);