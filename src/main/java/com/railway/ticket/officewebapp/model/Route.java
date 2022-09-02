package com.railway.ticket.officewebapp.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class Route implements Serializable {
    private int id;
    private Station startingStation;
    private Station finalStation;
    private Timestamp departureTime;
    private Timestamp arrivalTime;
    private Train train;


    public Route() {
    }

    public Route(int id, Timestamp departureTime, Station startingStation,
                 Timestamp arrivalTime, Station finalStation) {
        this.id = id;
        this.departureTime = departureTime;
        this.startingStation = startingStation;
        this.arrivalTime = arrivalTime;
        this.finalStation = finalStation;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (id < 0) {
            throw new IllegalArgumentException("ID cannot be < 0");
        }
        this.id = id;
    }

    public Timestamp getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Timestamp departureTime) {
        if (departureTime == null) {
            throw new IllegalArgumentException("Departure time cannot be null");
        }
        if(departureTime.before(Timestamp.valueOf(String.valueOf(System.currentTimeMillis())))){
            throw new IllegalArgumentException("Departure time cannot be set in the past");
        }
        this.departureTime = departureTime;
    }

    public Station getStartingStation() {
        return startingStation;
    }

    public void setStartingStation(Station startingStation) {
        if (startingStation == null) {
            throw new IllegalArgumentException("Starting station cannot be null");
        }
        this.startingStation = startingStation;
    }

    public Timestamp getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(Timestamp arrivalTime) {
        if (arrivalTime == null) {
            throw new IllegalArgumentException("Arrival time cannot be null");
        }
        if(arrivalTime.before(Timestamp.valueOf(String.valueOf(System.currentTimeMillis())))){
            throw new IllegalArgumentException("Arrival time cannot be set in the past");
        }
        this.arrivalTime = arrivalTime;
    }

    public Station getFinalStation() {
        return finalStation;
    }

    public void setFinalStation(Station finalStation) {
        if (finalStation == null) {
            throw new IllegalArgumentException("Final station cannot be null");
        }
        if (finalStation.equals(startingStation)) {
            throw new IllegalArgumentException("Final station cannot be the same as the starting station");
        }

        this.finalStation = finalStation;
    }

    public Train getTrain() {
        return train;
    }

    public void setTrain(Train train) {
        if(train==null){
            throw new IllegalArgumentException("Train cannot be null");
        }
        this.train = train;
    }



    @Override
    public String toString() {
        return "Route{" +
                "id=" + id +
                ", startStation=" + startingStation +
                ", finalStation=" + finalStation +
                ", departureTime=" + departureTime +
                ", arrivalTime=" + arrivalTime +
                ", train=" + train +
                '}';
    }
}
