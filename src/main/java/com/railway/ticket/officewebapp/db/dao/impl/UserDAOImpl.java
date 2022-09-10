package com.railway.ticket.officewebapp.db.dao.impl;

import com.railway.ticket.officewebapp.db.Constants;
import com.railway.ticket.officewebapp.db.DBException;
import com.railway.ticket.officewebapp.db.dao.UserDAO;
import com.railway.ticket.officewebapp.db.dao.mapper.impl.UserMapper;
import com.railway.ticket.officewebapp.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDAOImpl implements UserDAO {

    private final Connection con;
    private static final Logger LOGGER = LogManager.getLogger(UserDAOImpl.class);

    public UserDAOImpl(Connection con) {
        this.con = con;
    }


    @Override
    public void insertUser(final User user) throws DBException {
        try(PreparedStatement preparedStatement =
                    con.prepareStatement(Constants.USERS_INSERT_USER)) {

            setUserParameters(user, preparedStatement);


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
                    con.prepareStatement(Constants.USERS_DELETE_USER)) {

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
                    con.prepareStatement(Constants.USERS_UPDATE_USER)) {

            setUserParameters(user, preparedStatement);

            preparedStatement.setInt(7,userId);


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

    private void setUserParameters(User user, PreparedStatement preparedStatement) throws SQLException {
        int k = 1;
        preparedStatement.setString(k++, user.getLogin());
        preparedStatement.setString(k++, user.getPassword());
        preparedStatement.setString(k++, user.getFirstName());
        preparedStatement.setString(k++, user.getLastName());
        preparedStatement.setString(k++, user.getPhone());
        int roleId = getRoleIdByName(user);
        preparedStatement.setInt(k,roleId);
    }

    private String getRoleNameById(User user, long roleId) throws SQLException, DBException {
        String roleName = null;
        try (PreparedStatement preparedStatement = con.prepareStatement(Constants.GET_ROLE_NAME_BY_ID)) {
            preparedStatement.setInt(1, user.getId());

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                roleName = resultSet.getString("name");
            }

        } catch (SQLException e) {
            LOGGER.error("Role : [{}] was not found. An exception occurs." +
                            " Transaction rolled back!!! : {}",
                    roleId, e.getMessage());
            con.rollback();
            throw new DBException("[UserDAO] exception while reading Role" + e.getMessage(), e);
        }
        return roleName;
    }

    private int getRoleIdByName(User user) throws SQLException, DBException {
        int roleId =0;
        try (PreparedStatement preparedStatement = con.prepareStatement(Constants.GET_ROLE_ID_BY_NAME)) {
            preparedStatement.setString(1, user.getRole().getRoleName());

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                roleId = resultSet.getInt("id");
            }

        } catch (SQLException e) {
            LOGGER.error("Role : [{}] was not found. An exception occurs." +
                            " Transaction rolled back!!! : {}",
                    user.getRole().getRoleName(), e.getMessage());
            con.rollback();
            throw new DBException("[UserDAO] exception while reading Role" + e.getMessage(), e);
        }
        return roleId;
    }


    @Override
    public User getUserById(int userId) throws DBException {
        Optional<User> user = Optional.empty();

        try(PreparedStatement preparedStatement
                    = con.prepareStatement(Constants.USERS_GET_USER_BY_ID)) {

            preparedStatement.setInt(1,userId);

            try(ResultSet resultSet = preparedStatement.executeQuery();){
                while (resultSet.next()){
                    user = Optional.ofNullable(new UserMapper()
                            .extractFromResultSet(resultSet));
                }
            }

        }
        catch (SQLException e) {
            LOGGER.error("User with ID : [{}] was not found. An exception occurs : {}",
                    userId, e.getMessage());
            throw new DBException("[UserDAO] exception while loading User by ID" + e.getMessage(), e);
        }
        return user.get();
    }

    @Override
    public User getUserByLogin(String login) throws DBException {
        Optional<User> user = Optional.empty();

        try(PreparedStatement preparedStatement
                    = con.prepareStatement(Constants.USERS_GET_USER_BY_LOGIN)) {

            preparedStatement.setString(1,login);

            try(ResultSet resultSet = preparedStatement.executeQuery();){
                while (resultSet.next()){
                    user = Optional.ofNullable(new UserMapper()
                            .extractFromResultSet(resultSet));
                }
            }

        }
        catch (SQLException e) {
            LOGGER.error("User with login : [{}] was not found. An exception occurs : {}",
                    login, e.getMessage());
            throw new DBException("[UserDAO] exception while loading User by login" + e.getMessage(), e);
        }
        return user.get();
    }

    @Override
    public List<User> findAllUsers() throws DBException {
        List<User> users = new ArrayList<>();


        try(Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(Constants.USERS_GET_ALL_USERS)
        ) {

            UserMapper ticketMapper = new UserMapper();
            while (resultSet.next()){
                users.add(ticketMapper.extractFromResultSet(resultSet));
            }
        }
        catch (SQLException e) {
            LOGGER.error("Users were not found. An exception occurs : {}", e.getMessage());
            throw new DBException("[UserDAO] exception while reading all users" + e.getMessage(), e);
        }

        return users;
    }
}
