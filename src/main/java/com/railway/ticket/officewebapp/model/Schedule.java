package com.railway.ticket.officewebapp.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Schedule implements Serializable {
    private int id;
    private Date date;
    private List<Route> routes;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (id < 0) {
            throw new IllegalArgumentException("ID cannot be < 0");
        }
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        if (date==null) {
            throw new IllegalArgumentException("Data cannot be null");
        }
        this.date = date;
    }

    public List<Route> getRoutes() {
        return routes;
    }

    public void setRoutes(List<Route> routes) {
        if (routes==null) {
            throw new IllegalArgumentException("Routes cannot be null");
        }
        this.routes = routes;
    }
}
