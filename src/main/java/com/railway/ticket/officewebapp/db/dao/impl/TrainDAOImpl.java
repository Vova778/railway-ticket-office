package com.railway.ticket.officewebapp.db.dao.impl;

import com.railway.ticket.officewebapp.db.DBException;
import com.railway.ticket.officewebapp.db.MySQLConstants;
import com.railway.ticket.officewebapp.db.dao.TrainDAO;
import com.railway.ticket.officewebapp.model.Station;
import com.railway.ticket.officewebapp.model.Train;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class TrainDAOImpl implements TrainDAO {


    private final Connection con;
    private static final Logger LOGGER = LogManager.getLogger(TrainDAOImpl.class);

    public TrainDAOImpl(Connection con) {
        this.con = con;
    }

    @Override
    public void insertTrain(Train train) throws DBException {
        try(PreparedStatement preparedStatement =
                    con.prepareStatement(MySQLConstants.TRAINS_INSERT_TRAIN)) {
            int k = 1;

            preparedStatement.setInt(k++,train.getSeats());
            preparedStatement.setInt(k,train.getAvailableSeats());


            preparedStatement.executeUpdate();
            LOGGER.info("Train : {} was inserted successfully", train);
        } catch (SQLException e) {
            LOGGER.error("Train : [{}] was not inserted. An exception occurs : {}",
                    train, e.getMessage());
            throw new DBException("[TrainDAO] exception while creating Train" + e.getMessage(), e);
        }
    }

    @Override
    public void deleteTrain(int trainId) throws DBException {
        try(PreparedStatement preparedStatement =
                    con.prepareStatement(MySQLConstants.TRAINS_DELETE_TRAIN)) {

            preparedStatement.setInt(1,trainId);

            int removedRow = preparedStatement.executeUpdate();

            if(removedRow>0){
                LOGGER.info("Train with ID : {} was removed successfully", trainId);
            }

        } catch (SQLException e) {
            LOGGER.error("Train with ID : [{}] was not removed. An exception occurs : {}",
                    trainId, e.getMessage());
            throw new DBException("[TrainDAO] exception while removing Train" + e.getMessage(), e);
        }
    }

    @Override
    public void updateTrain(int trainId, Train train) throws DBException {
        try(PreparedStatement preparedStatement =
                    con.prepareStatement(MySQLConstants.TRAINS_UPDATE_TRAIN)) {

            int k = 1;

            preparedStatement.setInt(k++,train.getSeats());
            preparedStatement.setInt(k++,train.getAvailableSeats());
            preparedStatement.setInt(k,trainId);


            int updatedRow = preparedStatement.executeUpdate();
            if(updatedRow>0){
                LOGGER.info("Train with ID : {} was updated successfully", trainId);
            }

        } catch (SQLException e) {
            LOGGER.error("Train with ID : [{}] was not updated. An exception occurs : {}",
                    trainId, e.getMessage());
            throw new DBException("[TrainDAO] exception while updating Train" + e.getMessage(), e);
        }
    }

    @Override
    public Train getTrainById(int trainId) throws DBException {
        return null;
    }

    @Override
    public List<Train> getTrainBetweenStations(Station startStation, Station endStation) throws DBException {
        return null;
    }

    @Override
    public List<Train> findAllTrains() throws DBException {
        return null;
    }
}
