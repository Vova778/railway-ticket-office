package com.railway.ticket.officewebapp.db.dao.impl;

import com.railway.ticket.officewebapp.db.DBException;
import com.railway.ticket.officewebapp.db.Constants;
import com.railway.ticket.officewebapp.db.dao.RouteDAO;
import com.railway.ticket.officewebapp.db.dao.mapper.impl.RouteMapper;
import com.railway.ticket.officewebapp.model.Route;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.sql.*;
import java.util.ArrayList;
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
                    con.prepareStatement(Constants.ROUTES_INSERT_ROUTE,
                            Statement.RETURN_GENERATED_KEYS)) {

            setRouteParameters(route, preparedStatement);

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
                    con.prepareStatement(Constants.ROUTES_DELETE_ROUTE)) {
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
                    con.prepareStatement(Constants.ROUTES_UPDATE_ROUTE)) {


            setRouteParameters(route, preparedStatement);

            preparedStatement.setInt(11,routeId);

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

    private void setRouteParameters(Route route, PreparedStatement preparedStatement) throws SQLException {
        int k = 1;

        preparedStatement.setInt(k++, route.getStoppageNumber());
        preparedStatement.setInt(k++,route.getStartingStationId());
        preparedStatement.setInt(k++,route.getFinalStationId());
        preparedStatement.setTime(k++,route.getDepartureTime());
        preparedStatement.setTime(k++,route.getArrivalTime());
        preparedStatement.setInt(k++,route.getAvailableSeats());
        preparedStatement.setInt(k++,route.getDay());
        preparedStatement.setInt(k++,route.getScheduleId());
        preparedStatement.setInt(k++,route.getTrainId());
        preparedStatement.setDouble(k,route.getPrice());
    }

    @Override
    public Route getRouteById(int routeId) throws DBException {
        Optional<Route> route = Optional.empty();

        try(PreparedStatement preparedStatement = con.prepareStatement(Constants.ROUTES_GET_ROUTE_BY_ID)) {

            preparedStatement.setInt(1,routeId);

            try(ResultSet resultSet = preparedStatement.executeQuery();){
                while (resultSet.next()){
                    route = Optional.ofNullable(new RouteMapper()
                            .extractFromResultSet(resultSet));
                }
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
        List<Route> routes = new ArrayList<>();


        try(Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(Constants.ROUTES_GET_ALL_ROUTES)
        ) {

            RouteMapper routeMapper = new RouteMapper();
            while (resultSet.next()){
                routes.add(routeMapper.extractFromResultSet(resultSet));
            }
        }
        catch (SQLException e) {
            LOGGER.error("Routes were not found. An exception occurs : {}", e.getMessage());
            throw new DBException("[RouteDAO] exception while reading all routes" + e.getMessage(), e);
        }

        return routes;
    }
}
