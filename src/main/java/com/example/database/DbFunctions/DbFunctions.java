package com.example.database.DbFunctions;

import com.example.database.Models.Singleton;
import com.example.database.Models.Tickets;
import com.example.database.Models.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;

import java.sql.*;
import java.time.LocalDate;

public class DbFunctions {
    public Connection connect_to_db() {
        Connection connection = null;
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/" + "zxc", "postgres", "123");
            if (connection != null) {
                System.out.println("Connection successful");
            } else {
                System.out.println("Connection failed");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return connection;
    }


    public void createUser(String FirstName, String SecondName,String LastName, String Passport, String Phone, LocalDate datee, String image) {
        try {
            String query = String.format("insert into users(firstname, secondname, lastName, passport, phone, datee, image) values('%s','%s','%s','%s','%s','%s','%s');", FirstName, SecondName, LastName, Passport, Phone, datee, image);
            Statement statement = connect_to_db().createStatement();
            statement.executeUpdate(query);
            System.out.println("User created");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public int check_login(String login) {
        try {
            String query = String.format("select * from users where phone = '%s'", login);
            Statement statement = connect_to_db().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet resultSet = statement.executeQuery(query);
            resultSet.last();
            if (resultSet.getRow() >= 1) {
                return 0;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return 404;
        }
        return 201;
    }



    public int signIn(String login, String password) {
        try {
            String query = String.format("select * from users where login = '%s' and password = '%s'", login, password);
            Statement statement = connect_to_db().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            if (!resultSet.next()) {
                return 0;
            }
            Variables.ROLE_USER = resultSet.getString("role");
            Variables.ACTIVE_USER = resultSet.getString("login");

        } catch(SQLException e) {
            System.out.println(e.getMessage());
            return 404;
        }
        return 201;
    }

    public void createTickets(String firstName, String secondname, String lastname, String phone, String dayS, String timee, String services, String statusTickets){
        try{
            String query = String.format("insert into Tickets(firstName, secondname, lastname, phone, dayS, timee, services, statusTickets) values('%s','%s','%s','%s','%s','%s','%s','%s');", firstName, secondname, lastname, phone, dayS,timee,services,statusTickets);
            Statement statement = connect_to_db().createStatement();
            statement.executeUpdate(query);
            System.out.println("Ticket created");
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void updateDataTickets(String firstName, String secondname, String lastname, String phone,String dayS,String timee,String services,String statusTickets, String id) {
        try {
            String query = String.format("update Tickets set firstName='%s', secondname='%s', lastname='%s',phone='%s',dayS='%s',timee='%s',services='%s',statusTickets='%s' where id='%s'", firstName, secondname, lastname,phone,dayS,timee,services,statusTickets, id);
            Statement statement = connect_to_db().createStatement();
            statement.executeUpdate(query);
            System.out.println("Data updated");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public ObservableList<Tickets> getAllTickets() {
        ObservableList<Tickets> tickets = FXCollections.observableArrayList();
        try {
            ResultSet resultSet = connect_to_db().createStatement().executeQuery("select * from Tickets");
            while (resultSet.next()) {
                tickets.add(new Tickets(
                        resultSet.getString("id"),
                        resultSet.getString("firstName"),
                        resultSet.getString("secondname"),
                        resultSet.getString("lastname"),
                        resultSet.getString("phone"),
                        resultSet.getString("dayS"),
                        resultSet.getString("timee"),
                        resultSet.getString("services"),
                        resultSet.getString("statusTickets")
                ));
            }
            return tickets;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return tickets;
        }
    }

    public void update_status(String status, String login) {
        try {
            String query = String.format("update users set status = '%s' where login = '%s'", status, login);
            Statement statement = connect_to_db().createStatement();
            statement.executeUpdate(query);
            System.out.println("Data updated");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }



    public ObservableList<User> getAllUsers() {
        ObservableList<User> users = FXCollections.observableArrayList();
        try {
            ResultSet resultSet = connect_to_db().createStatement().executeQuery("select * from users");
            while (resultSet.next()) {
                users.add(new User(
                        resultSet.getString("id"),
                        resultSet.getString("login"),
                        resultSet.getString("password"),
                        resultSet.getString("firstname"),
                        resultSet.getString("secondname"),
                        resultSet.getString("lastname"),
                        resultSet.getString("role"),
                        resultSet.getString("status")
                ));
            }
             return users;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return users;
        }
    }

    public void updateDataUser(String login, String password, String role, String id) {
        try {
            String query = String.format("update users set login='%s', password='%s', role='%s', status='Оффлайн' where id='%s'", login, password, role, id);
            Statement statement = connect_to_db().createStatement();
            statement.executeUpdate(query);
            System.out.println("Data updated");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public void deleteDataUser(String id) {
        try {
            String query = String.format("delete from users where id='%s'",id);
            connect_to_db().createStatement().executeUpdate(query);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteDataTicket(String id) {
        try {
            String query = String.format("delete from Tickets where id='%s'",id);
            connect_to_db().createStatement().executeUpdate(query);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void setData(String login) {
        try {
            String query = String.format("select firstname,secondname,lastname,phone, passport, image from users where login='%s'",login);
            Statement statement = connect_to_db().createStatement();

            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                Singleton.getInstance().setFirstName(resultSet.getString("firstname"));
                Singleton.getInstance().setSecondName(resultSet.getString("secondname"));
                Singleton.getInstance().setLastName(resultSet.getString("lastname"));
                Singleton.getInstance().setPhone(resultSet.getString("phone"));
                Singleton.getInstance().setPassport(resultSet.getString("passport"));
                Singleton.getInstance().setImg(resultSet.getString("image"));


            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }




}