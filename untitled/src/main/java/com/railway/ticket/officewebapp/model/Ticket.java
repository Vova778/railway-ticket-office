package com.railway.ticket.officewebapp.model;

import java.io.Serializable;

public class Ticket implements Serializable {
    private int id;
    private int userId;
    private int trainId;
    private double fare;
    private Route route;

    public Ticket() {
    }

    public Ticket(int id, int userId, int trainId, double fare, Route route) {
        this.id = id;
        this.userId = userId;
        this.trainId = trainId;
        this.fare = fare;
        this.route = route;
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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        if (userId < 0) {
            throw new IllegalArgumentException("User_ID cannot be < 0");
        }
        this.userId = userId;
    }

    public int getTrainId() {
        return trainId;
    }

    public void setTrainId(int trainId) {
        if (trainId < 0) {
            throw new IllegalArgumentException("Train_ID cannot be < 0");
        }
        this.trainId = trainId;
    }

    public double getFare() {
        return fare;
    }

    public void setFare(double fare) {
        if (fare < 0 ) {
            throw new IllegalArgumentException("fare cannot be < 0 ");
        }
        this.fare = fare;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        if (route == null) {
            throw new IllegalArgumentException("Route cannot be null");
        }
        this.route = route;
    }
}
