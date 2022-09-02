package com.railway.ticket.officewebapp.db;

public class MySQLConstants {
    public static final String URL = "jdbc:mysql://localhost:3306/railway_station_ticket";
    public static final String FULL_URL = "jdbc:mysql://localhost:3306/railway_station_ticket?user=root&password=Vova778ltym1";

    public static final String FIELDS_ROUTES_ID = "id";
    public static final String FIELDS_ROUTES_STARTING_STATION = "starting_station_id";
    public static final String FIELDS_ROUTES_FINAL_STATION = "final_station_id";
    public static final String FIELDS_ROUTES_DEPARTURE_TIME = "departure_time";
    public static final String FIELDS_ROUTES_ARRIVAL_TIME = "arrival_time";
    public static final String FIELDS_ROUTES_TRAIN_ID = "train_id";

    public static final String FIELDS_STATIONS_ID = "id";
    public static final String FIELDS_STATIONS_NAME = "name";

    public static final String FIELDS_TICKETS_ID = "id";
    public static final String FIELDS_TICKETS_FARE = "fare";
    public static final String FIELDS_TICKETS_USER_ID = "user_id";
    public static final String FIELDS_TICKETS_TRAIN_ID = "train_id";
    public static final String FIELDS_TICKETS_ROUTES_ID = "routes_id";
    public static final String FIELDS_TICKETS_STARTING_STATION_ID = "starting_station_id";
    public static final String FIELDS_TICKETS_FINAL_STATION_ID = "final_station_id";

    public static final String FIELDS_TRAINS_ID = "id";
    public static final String FIELDS_TRAINS_TOTAL_SEATS = "total_seats";
    public static final String FIELDS_TRAINS_AVAILABLE_SEATS = "available_seats";

    public static final String FIELDS_USERS_ID = "id";
    public static final String FIELDS_USERS_LOGIN = "login";
    public static final String FIELDS_USERS_PASSWORD = "password";
    public static final String FIELDS_USERS_FIRST_NAME = "first_name";
    public static final String FIELDS_USERS_LAST_NAME = "last_name";
    public static final String FIELDS_USERS_PHONE = "phone";
    public static final String FIELDS_USERS_ROLE_ID = "role_id";

    //////////////////////////////////////////////

    public static final String ROUTES_INSERT_ROUTE = "insert into routes values (default, ?,?,?,?,?)";
    public static final String ROUTES_DELETE_ROUTE = "delete from routes where id=?";
    public static final String ROUTES_UPDATE_ROUTE = "update routes set starting_station_id=?, final_station_id=?, departure_time=?, arrival_time=?, train_id=? where id=?";
    public static final String ROUTES_GET_ROUTE_BY_ID = "select route.id, starting_station_id, final_station_id, departure_time, arrival_time, available_seats, trains_id, name" +
            " from route" +
            "left join station on starting_station_id= station.id; where id=?";
    public static final String ROUTES_GET_ALL_ROUTES = "select * from routes";

    public static final String STATIONS_INSERT_STATION = "insert into stations values (default, ?)";
    public static final String STATIONS_DELETE_STATION = "delete from stations where id=?";
    public static final String STATIONS_UPDATE_STATION = "update stations set name=? where id=?";
    public static final String STATIONS_GET_STATION_BY_ID = "select * from stations where id=?";
    public static final String STATIONS_GET_STATION_BY_NAME = "select * from stations where name=?";
    public static final String STATIONS_GET_ALL_STATIONS = "select * from stations";

    public static final String TICKETS_INSERT_TICKET = "insert into tickets values (default, ?,?,?,?,?,?)";
    public static final String TICKETS_DELETE_TICKET = "delete from tickets where id=?";
    public static final String TICKETS_UPDATE_TICKET = "update tickets set fare=?, user_id=?, train_id=?, routes_id=?, starting_station_id=?, final_station_id=? where id=?";
    public static final String TICKETS_GET_TICKET_BY_ID = "select * from tickets where id=?";
    public static final String TICKETS_GET_ALL_TICKETS = "select * from tickets";

    public static final String TRAINS_INSERT_TRAIN = "insert into trains values (default, ?,?)";
    public static final String TRAINS_DELETE_TRAIN = "delete from trains where id=?";
    public static final String TRAINS_UPDATE_TRAIN = "update trains set total_seats=?, available_seats=? where id=?";
    public static final String TRAINS_GET_TRAIN_BY_ID = "select * from trains where id=?";
  // public static final String TRAINS_GET_TRAIN_BETWEEN_STATIONS="select * from routes where";
    public static final String TRAINS_GET_ALL_TRAINS = "select * from trains";

    public static final String USERS_INSERT_USER = "insert into users values (default, ?,?,?,?,?,?)";
    public static final String USERS_DELETE_USER = "delete from users where id=?";
    public static final String USERS_UPDATE_USER = "update users set login=?, password_id=?, first_name=?, last_name=?, phone=? where id=?";
    public static final String USERS_GET_USER_BY_ID = "select * from users where id=?";
    public static final String USERS_GET_USER_BY_LOGIN = "select * from users where login=?";
    public static final String USERS_GET_ALL_USERS = "select * from users";


    private MySQLConstants() {
    }
}
