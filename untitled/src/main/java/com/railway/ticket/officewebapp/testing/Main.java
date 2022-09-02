package com.railway.ticket.officewebapp.testing;


import com.railway.ticket.officewebapp.db.DBException;
import com.railway.ticket.officewebapp.db.dao.StationDAO;
import com.railway.ticket.officewebapp.db.dao.TrainDAO;
import com.railway.ticket.officewebapp.db.dao.UserDAO;
import com.railway.ticket.officewebapp.db.dao.factory.DAOFactory;
import com.railway.ticket.officewebapp.model.Station;
import com.railway.ticket.officewebapp.model.Train;
import com.railway.ticket.officewebapp.model.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static DAOFactory daoFactory = DAOFactory.getInstance();

    public static void main(String[] args) {
        GenerateUsers();
        GenerateStations();


    }

    public static void GenerateUsers(){
        try {
            UserDAO userDAO = daoFactory.getUserDAO();
            int k = 0;
            List<User> userList = new ArrayList<>();

            userList.add(new User(k++,
                    "Vova1","1111"
                    ,"Vova", "Kor","1111111111"));
            userList.add(new User(k++,
                    "Vova2","1234"
                    ,"Vova2", "Kor","1111111112"));
            userList.add(new User(k,
                    "Vova3","1564"
                    ,"Vova3", "Kor","1111111113"));


            for (User user : userList) {
                userDAO.insertUser(user);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public static void GenerateStations(){
        try {
            StationDAO stationDAO = daoFactory.getStationDAO();
            List<Station> stations = new ArrayList<>();
            int k = 0;

            stations.add(new Station(k++,"Olenivka"));
            stations.add(new Station(k++,"Kalunovka"));
            stations.add(new Station(k++,"Mukolaivka"));
            stations.add(new Station(k++,"Chornobaivka"));
            stations.add(new Station(k++,"Shalene"));
            stations.add(new Station(k++,"Vesele"));
            stations.add(new Station(k,"Veres"));


            stations.forEach(station -> {
                try {
                    stationDAO.insertStation(station);
                } catch (DBException throwables) {
                    throwables.printStackTrace();
                }
            });

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
    public static void GenerateTrain(){
        try {
            TrainDAO trainDAO = daoFactory.getTrainDAO();
            List<Train> trains = new ArrayList<>();
            int k = 0;

            trains.add(new Train(k++,10,10));
            trains.add(new Train(k++,11,11));
            trains.add(new Train(k,12,12));


            trains.forEach(train -> {
                try {
                    trainDAO.insertTrain(train);
                } catch (DBException throwables) {
                    throwables.printStackTrace();
                }
            });

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


}
