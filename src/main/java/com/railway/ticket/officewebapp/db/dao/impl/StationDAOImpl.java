package com.railway.ticket.officewebapp.db.dao.impl;

import com.railway.ticket.officewebapp.db.DBException;
import com.railway.ticket.officewebapp.db.MySQLConstants;
import com.railway.ticket.officewebapp.db.dao.StationDAO;
import com.railway.ticket.officewebapp.model.Station;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class StationDAOImpl implements StationDAO {

    private final Connection con;
    private static final Logger LOGGER = LogManager.getLogger(StationDAOImpl.class);

    public StationDAOImpl(Connection con) {
        this.con = con;
    }


    @Override
    public void insertStation(Station station) throws DBException {
        try(PreparedStatement preparedStatement =
                    con.prepareStatement(MySQLConstants.STATIONS_INSERT_STATION)) {
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
                    con.prepareStatement(MySQLConstants.STATIONS_DELETE_STATION)) {

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
                    con.prepareStatement(MySQLConstants.STATIONS_UPDATE_STATION)) {

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
        return null;
    }

    @Override
    public Station getStationByName(String stationName) throws DBException {
        return null;
    }

    @Override
    public List<Station> findAllStations() throws DBException {
        return null;
    }
}
