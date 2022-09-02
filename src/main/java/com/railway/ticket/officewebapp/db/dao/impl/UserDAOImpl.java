package com.railway.ticket.officewebapp.db.dao.impl;

import com.railway.ticket.officewebapp.db.DBException;
import com.railway.ticket.officewebapp.db.MySQLConstants;
import com.railway.ticket.officewebapp.db.dao.UserDAO;
import com.railway.ticket.officewebapp.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class UserDAOImpl implements UserDAO {

    private final Connection con;
    private static final Logger LOGGER = LogManager.getLogger(UserDAOImpl.class);

    public UserDAOImpl(Connection con) {
        this.con = con;
    }


    @Override
    public void insertUser(final User user) throws DBException {
        try(PreparedStatement preparedStatement =
                    con.prepareStatement(MySQLConstants.USERS_INSERT_USER)) {

            int k = 1;

            preparedStatement.setString(k++, user.getLogin());
            preparedStatement.setString(k++, user.getPassword());
            preparedStatement.setString(k++, user.getFirstName());
            preparedStatement.setString(k++, user.getLastName());
            preparedStatement.setString(k++, user.getPhone());
            preparedStatement.setInt(k,user.getAdmin());


            preparedStatement.executeUpdate();
            LOGGER.info("User : {} was inserted successfully", user);
        } catch (SQLException e) {
            LOGGER.error("User : [{}] was not inserted. An exception occurs : {}",
                    user, e.getMessage());
            throw new DBException("[UserDAO] exception while creating User" + e.getMessage(), e);
        }
    }

    @Override
    public void deleteUser(int userId) throws DBException {
        try(PreparedStatement preparedStatement =
                    con.prepareStatement(MySQLConstants.USERS_DELETE_USER)) {

            preparedStatement.setInt(1, userId);

            int removedRow = preparedStatement.executeUpdate();

            if(removedRow>0){
                LOGGER.info("User with ID : {} was removed successfully", userId);
            }

        } catch (SQLException e) {
            LOGGER.error("User with ID : [{}] was not removed. An exception occurs : {}",
                    userId, e.getMessage());
            throw new DBException("[UserDAO] exception while removing User" + e.getMessage(), e);
        }
    }

    @Override
    public void updateUser(int userId, User user) throws DBException {
        try(PreparedStatement preparedStatement =
                    con.prepareStatement(MySQLConstants.USERS_UPDATE_USER)) {

            int k = 1;

            preparedStatement.setString(k++, user.getLogin());
            preparedStatement.setString(k++, user.getPassword());
            preparedStatement.setString(k++, user.getFirstName());
            preparedStatement.setString(k++, user.getLastName());
            preparedStatement.setString(k++, user.getPhone());
            preparedStatement.setInt(k,userId);


            int updatedRow = preparedStatement.executeUpdate();
            if(updatedRow>0){
                LOGGER.info("User with ID : {} was updated successfully", userId);
            }

        } catch (SQLException e) {
            LOGGER.error("User with ID : [{}] was not updated. An exception occurs : {}",
                    userId, e.getMessage());
            throw new DBException("[UserDAO] exception while updating User" + e.getMessage(), e);
        }
    }

    @Override
    public User getUserById(int userId) throws DBException {
        return null;
    }

    @Override
    public User getUserByLogin(String login) throws DBException {
        return null;
    }

    @Override
    public List<User> findAllUsers() throws DBException {
        return null;
    }
}
