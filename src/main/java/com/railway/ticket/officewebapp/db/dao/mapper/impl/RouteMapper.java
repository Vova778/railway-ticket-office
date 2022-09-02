package com.railway.ticket.officewebapp.db.dao.mapper.impl;

import com.railway.ticket.officewebapp.db.MySQLConstants;
import com.railway.ticket.officewebapp.db.dao.mapper.ObjectMapper;
import com.railway.ticket.officewebapp.model.Route;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class RouteMapper implements ObjectMapper<Route> {

    @Override
    public Route extractFromResultSet(ResultSet resultSet) throws SQLException {
        Map<String,Route> routeMap = new HashMap<>();
        Route route = new Route();
        route.setArrivalTime(resultSet.getTimestamp(MySQLConstants.FIELDS_ROUTES_ARRIVAL_TIME));
        route.setDepartureTime(resultSet.getTimestamp(MySQLConstants.FIELDS_ROUTES_DEPARTURE_TIME));
        return null;
    }

    @Override
    public Route toUnique(Map<String, Route> cache, Route route) {
        return null;
    }
}
