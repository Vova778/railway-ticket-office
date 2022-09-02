package com.railway.ticket.officewebapp.db.dao.impl;

import com.railway.ticket.officewebapp.db.DBException;
import com.railway.ticket.officewebapp.db.MySQLConstants;
import com.railway.ticket.officewebapp.db.dao.RouteDAO;
import com.railway.ticket.officewebapp.model.Route;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.sql.*;
import java.util.List;
import java.util.Optional;

public class RouteDAOImpl implements RouteDAO {

    private final Connection con;
    private static final Logger LOGGER = LogManager.getLogger(RouteDAOImpl.class);

    public RouteDAOImpl(Connection connection){
        this.con=connection;
    }

    @Override
    public void insertRoute(Route route) throws DBException {
        try(PreparedStatement preparedStatement =
                    con.prepareStatement(MySQLConstants.ROUTES_INSERT_ROUTE)) {
            int k = 1;

            preparedStatement.setTimestamp(k++,route.getDepartureTime());
            preparedStatement.setTimestamp(k++,route.getArrivalTime());
            preparedStatement.setInt(k++,route.getStartingStation().getId());
            preparedStatement.setInt(k++,route.getFinalStation().getId());
            preparedStatement.setInt(k,route.getTrain().getId());

            preparedStatement.executeUpdate();
            LOGGER.info("Route : {} was inserted successfully", route);
        } catch (SQLException e) {
            LOGGER.error("Route : [{}] was not inserted. An exception occurs : {}",
                    route, e.getMessage());
            throw new DBException("[RouteDAO] exception while creating Route" + e.getMessage(), e);
        }
    }

    @Override
    public void deleteRoute(int routeId) throws DBException {
        try(PreparedStatement preparedStatement =
                    con.prepareStatement(MySQLConstants.ROUTES_DELETE_ROUTE)) {
            preparedStatement.setInt(1,routeId);
            int removedRow = preparedStatement.executeUpdate();

            if(removedRow>0){
                LOGGER.info("Route with ID : {} was removed successfully", routeId);
            }

        } catch (SQLException e) {
            LOGGER.error("Route with ID : [{}] was not removed. An exception occurs : {}",
                    routeId, e.getMessage());
            throw new DBException("[RouteDAO] exception while removing Route" + e.getMessage(), e);
        }
    }

    @Override
    public void updateRoute(int routeId, Route route) throws DBException {
        try(PreparedStatement preparedStatement =
                    con.prepareStatement(MySQLConstants.ROUTES_UPDATE_ROUTE)) {

            int k = 1;

            preparedStatement.setInt(k++,route.getStartingStation().getId());
            preparedStatement.setInt(k++,route.getFinalStation().getId());
            preparedStatement.setTimestamp(k++,route.getDepartureTime());
            preparedStatement.setTimestamp(k++,route.getArrivalTime());
            preparedStatement.setInt(k++,route.getTrain().getId());
            preparedStatement.setInt(k,routeId);

            int updatedRow = preparedStatement.executeUpdate();
            if(updatedRow>0){
                LOGGER.info("Route with ID : {} was updated successfully", routeId);
            }

        } catch (SQLException e) {
            LOGGER.error("Route with ID : [{}] was not updated. An exception occurs : {}",
                    routeId, e.getMessage());
            throw new DBException("[RouteDAO] exception while updating Route" + e.getMessage(), e);
        }
    }

    @Override
    public Route getRouteById(int routeId) throws DBException {
        Optional<Route> route = Optional.empty();

        try(PreparedStatement preparedStatement = con.prepareStatement(MySQLConstants.ROUTES_GET_ROUTE_BY_ID)) {
            preparedStatement.setInt(1,routeId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){

            }
        }
        catch (SQLException e) {
            LOGGER.error("Route with ID : [{}] was not found. An exception occurs : {}",
                    routeId, e.getMessage());
            throw new DBException("[RouteDAO] exception while loading Route" + e.getMessage(), e);
        }
        return route.get();
    }

    @Override
    public List<Route> findAllRoute() throws DBException {
        return null;
    }
}
