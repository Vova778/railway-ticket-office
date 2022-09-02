package com.railway.ticket.officewebapp.db.dao.impl;

import com.railway.ticket.officewebapp.db.DBException;
import com.railway.ticket.officewebapp.db.MySQLConstants;
import com.railway.ticket.officewebapp.db.dao.TicketDAO;
import com.railway.ticket.officewebapp.model.Ticket;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class TicketDAOImpl implements TicketDAO {

    private final Connection con;
    private static final Logger LOGGER = LogManager.getLogger(TicketDAOImpl.class);

    public TicketDAOImpl(Connection con) {
        this.con = con;
    }

    @Override
    public void insertTicket(Ticket ticket) throws DBException {
        try(PreparedStatement preparedStatement =
                    con.prepareStatement(MySQLConstants.TICKETS_INSERT_TICKET)) {
            int k = 1;

            preparedStatement.setInt(k++,ticket.getRoute().getId());
            preparedStatement.setInt(k++,ticket.getRoute().getStartingStation().getId());
            preparedStatement.setInt(k++,ticket.getRoute().getFinalStation().getId());
            preparedStatement.setInt(k++,ticket.getTrainId());
            preparedStatement.setInt(k,ticket.getUserId());


            preparedStatement.executeUpdate();
            LOGGER.info("Ticket : {} was inserted successfully", ticket);
        } catch (SQLException e) {
            LOGGER.error("Ticket : [{}] was not inserted. An exception occurs : {}",
                    ticket, e.getMessage());
            throw new DBException("[TicketDAO] exception while creating Ticket" + e.getMessage(), e);
        }
    }

    @Override
    public void deleteTicket(int ticketId) throws DBException {
        try(PreparedStatement preparedStatement =
                    con.prepareStatement(MySQLConstants.TICKETS_DELETE_TICKET)) {

            preparedStatement.setInt(1,ticketId);

            int removedRow = preparedStatement.executeUpdate();

            if(removedRow>0){
                LOGGER.info("Ticket with ID : {} was removed successfully", ticketId);
            }

        } catch (SQLException e) {
            LOGGER.error("Ticket with ID : [{}] was not removed. An exception occurs : {}",
                    ticketId, e.getMessage());
            throw new DBException("[TicketDAO] exception while removing Ticket" + e.getMessage(), e);
        }
    }

    @Override
    public void updateTicket(int ticketId, Ticket ticket) throws DBException {
        try(PreparedStatement preparedStatement =
                    con.prepareStatement(MySQLConstants.TICKETS_UPDATE_TICKET)) {

            int k = 1;

            preparedStatement.setDouble(k++,ticket.getFare());
            preparedStatement.setInt(k++,ticket.getUserId());
            preparedStatement.setInt(k++,ticket.getTrainId());
            preparedStatement.setInt(k++,ticket.getRoute().getId());
            preparedStatement.setInt(k++,ticket.getRoute().getStartingStation().getId());
            preparedStatement.setInt(k++,ticket.getRoute().getFinalStation().getId());
            preparedStatement.setInt(k,ticketId);


            int updatedRow = preparedStatement.executeUpdate();
            if(updatedRow>0){
                LOGGER.info("Ticket with ID : {} was updated successfully", ticketId);
            }

        } catch (SQLException e) {
            LOGGER.error("Ticket with ID : [{}] was not updated. An exception occurs : {}",
                    ticketId, e.getMessage());
            throw new DBException("[TicketDAO] exception while updating Ticket" + e.getMessage(), e);
        }
    }

    @Override
    public Ticket getTicketById(int ticketId) throws DBException {
        return null;
    }

    @Override
    public List<Ticket> findAllTickets() throws DBException {
        return null;
    }
}
