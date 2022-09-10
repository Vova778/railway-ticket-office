package com.railway.ticket.officewebapp.db.dao.impl;

import com.railway.ticket.officewebapp.db.Constants;
import com.railway.ticket.officewebapp.db.DBException;
import com.railway.ticket.officewebapp.db.dao.StationDAO;
import com.railway.ticket.officewebapp.db.dao.mapper.impl.StationMapper;
import com.railway.ticket.officewebapp.model.Station;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StationDAOImpl implements StationDAO {

    private final Connection con;
    private static final Logger LOGGER = LogManager.getLogger(StationDAOImpl.class);

    public StationDAOImpl(Connection con) {
        this.con = con;
    }


    @Override
    public void insertStation(Station station) throws DBException {
        try(PreparedStatement preparedStatement =
                    con.prepareStatement(Constants.STATIONS_INSERT_STATION)) {
            int k = 1;

            preparedStatement.setString(k,station.getName());

            preparedStatement.executeUpdate();
            LOGGER.info("Station : {} was inserted successfully", station);
        } catch (SQLException e) {
            LOGGER.error("Station : [{}] was not inserted. An exception occurs : {}",
                    station, e.getMessage());
            throw new DBException("[StationDAO] exception while creating Station" + e.getMessage(), e);
        }
    }

    @Override
    public void deleteStation(int stationId) throws DBException {
        try(PreparedStatement preparedStatement =
                    con.prepareStatement(Constants.STATIONS_DELETE_STATION)) {

            preparedStatement.setInt(1,stationId);

            int removedRow = preparedStatement.executeUpdate();

            if(removedRow>0){
                LOGGER.info("Station with ID : {} was removed successfully", stationId);
            }

        } catch (SQLException e) {
            LOGGER.error("Station with ID : [{}] was not removed. An exception occurs : {}",
                    stationId, e.getMessage());
            throw new DBException("[StationDAO] exception while removing Station" + e.getMessage(), e);
        }
    }

    @Override
    public void updateStation(int stationId, Station station) throws DBException {
        try(PreparedStatement preparedStatement =
                    con.prepareStatement(Constants.STATIONS_UPDATE_STATION)) {

            int k = 1;

            preparedStatement.setString(k++,station.getName());
            preparedStatement.setInt(k,stationId);

            int updatedRow = preparedStatement.executeUpdate();
            if(updatedRow>0){
                LOGGER.info("Station with ID : {} was updated successfully", stationId);
            }

        } catch (SQLException e) {
            LOGGER.error("Station with ID : [{}] was not updated. An exception occurs : {}",
                    stationId, e.getMessage());
            throw new DBException("[StationDAO] exception while updating Station" + e.getMessage(), e);
        }
    }

    @Override
    public Station getStationById(int stationId) throws DBException {
        Optional<Station> station = Optional.empty();

        try(PreparedStatement preparedStatement
                    = con.prepareStatement(Constants.STATIONS_GET_STATION_BY_ID)) {

            preparedStatement.setInt(1,stationId);

            try(ResultSet resultSet = preparedStatement.executeQuery();){
                while (resultSet.next()){
                    station = Optional.ofNullable(new StationMapper()
                            .extractFromResultSet(resultSet));
                }
            }

        }
        catch (SQLException e) {
            LOGGER.error("Station with ID : [{}] was not found. An exception occurs : {}",
                    stationId, e.getMessage());
            throw new DBException("[StationDAO] exception while loading Station by ID" + e.getMessage(), e);
        }
        return station.get();
    }

    @Override
    public Station getStationByName(String stationName) throws DBException {
        Optional<Station> station = Optional.empty();

        try(PreparedStatement preparedStatement
                    = con.prepareStatement(Constants.STATIONS_GET_STATION_BY_NAME)) {

            preparedStatement.setString(1,stationName);

            try(ResultSet resultSet = preparedStatement.executeQuery();){
                while (resultSet.next()){
                    station = Optional.ofNullable(new StationMapper()
                            .extractFromResultSet(resultSet));
                }
            }

        }
        catch (SQLException e) {
            LOGGER.error("Station with name : [{}] was not found. An exception occurs : {}",
                    stationName, e.getMessage());
            throw new DBException("[StationDAO] exception while loading Station by name" + e.getMessage(), e);
        }
        return station.get();
    }

    @Override
    public List<Station> findAllStations() throws DBException {
        List<Station> stations = new ArrayList<>();


        try(Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(Constants.STATIONS_GET_ALL_STATIONS)
        ) {

            StationMapper stationMapper = new StationMapper();
            while (resultSet.next()){
                stations.add(stationMapper.extractFromResultSet(resultSet));
            }
        }
        catch (SQLException e) {
            LOGGER.error("Stations were not found. An exception occurs : {}", e.getMessage());
            throw new DBException("[StationDAO] exception while reading all stations" + e.getMessage(), e);
        }

        return stations;
    }
}
