package com.example.database.DbFunctions;

import com.example.database.Models.Singleton;
import com.example.database.Models.Tickets;
import com.example.database.Models.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DbFunctions {

    public static Connection connect_to_db() {
        Connection connection = null;
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/" + "remont", "postgres", "123");
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

    public void createTickets(String date, String equipment, String tip, String priority, String ispolnitel, String problem, String status){
        try{
            String query = String.format("insert into requests(date, equipment, tip, priority, ispolnitel, problem, status) values('%s','%s','%s','%s','%s','%s','%s');", date, equipment, tip, priority, ispolnitel, problem, status);
            Statement statement = connect_to_db().createStatement();
            statement.executeUpdate(query);
            System.out.println("Ticket created");
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void createEquipment(String name, String price) {
        try {
            String query = String.format("INSERT INTO equipments(name, price) VALUES('%s', '%s');",
                    name, price);
            Statement statement = connect_to_db().createStatement();
            statement.executeUpdate(query);
            System.out.println("Equipment created");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void createMaterial(String name, String price) {
        try {
            String query = String.format("INSERT INTO materials(name, price) VALUES('%s', '%s');",
                    name, price);
            Statement statement = connect_to_db().createStatement();
            statement.executeUpdate(query);
            System.out.println("Material created");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }



    public void updateDataTickets(String date, String equipment, String tip, String priority, String ispolnitel, String problem, String status, String remont, String time, String materials, String colmaterials, String cost, String comment, String id) {
        try {
            String query = String.format("update requests set date='%s', equipment='%s', tip='%s',priority='%s',ispolnitel='%s',problem='%s',status='%s' ,remont='%s' ,time='%s' ,materials='%s' ,colmaterials='%s' ,cost='%s' ,comment='%s' where id='%s'", date, equipment, tip, priority, ispolnitel, problem, status, remont, time, materials, colmaterials, cost, comment, id);
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
            ResultSet resultSet = connect_to_db().createStatement().executeQuery("select * from requests");
            while (resultSet.next()) {
                tickets.add(new Tickets(
                        resultSet.getString("id"),
                        resultSet.getString("date"),
                        resultSet.getString("equipment"),
                        resultSet.getString("tip"),
                        resultSet.getString("priority"),
                        resultSet.getString("ispolnitel"),
                        resultSet.getString("problem"),
                        resultSet.getString("status"),
                        resultSet.getString("remont"),
                        resultSet.getString("time"),
                        resultSet.getString("materials"),
                        resultSet.getString("colmaterials"),
                        resultSet.getString("cost"),
                        resultSet.getString("comment")
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
            String query = String.format("delete from requests where id='%s'",id);
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


    public ObservableList<String> getAllRoles() {
        ObservableList<String> roles = FXCollections.observableArrayList();
        try {
            String query = "SELECT name FROM roles";
            Statement statement = connect_to_db().createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                String role = resultSet.getString("name");
                roles.add(role);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return roles;
    }

    public ObservableList<String> getAllPrioritet() {
        ObservableList<String> prioritet = FXCollections.observableArrayList();
        try {
            String query = "SELECT name FROM prioritet";
            Statement statement = connect_to_db().createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                String priority = resultSet.getString("name");
                prioritet.add(priority);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return prioritet;
    }

    public List<String> getIspolniteli() {
        List<String> ispolniteli = new ArrayList<>();

        // Получение соединения к базе данных
        Connection connection = DbFunctions.connect_to_db();
        if (connection == null) {
            System.out.println("Ошибка получения соединения с базой данных");
            return ispolniteli;
        }

        // Запрос к базе данных для получения данных исполнителей
        String query = "SELECT firstname, secondname, lastname FROM users WHERE role = 'Исполнитель'";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String firstname = resultSet.getString("firstname");
                String secondname = resultSet.getString("secondname");
                String lastname = resultSet.getString("lastname");
                String ispolnitel = firstname + " " + secondname + " " + lastname;
                ispolniteli.add(ispolnitel);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ispolniteli;
    }

    public ObservableList<String> getAllStatus() {
        ObservableList<String> status = FXCollections.observableArrayList();
        try {
            String query = "SELECT name FROM status";
            Statement statement = connect_to_db().createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                String stat = resultSet.getString("name");
                status.add(stat);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return status;
    }

    public ObservableList<String> getEquipments() {
        ObservableList<String> eqipment = FXCollections.observableArrayList();
        try {
            String query = "SELECT name FROM equipments";
            Statement statement = connect_to_db().createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                String stat = resultSet.getString("name");
                eqipment.add(stat);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return eqipment;
    }

    public ObservableList<String> getTip() {
        ObservableList<String> tip = FXCollections.observableArrayList();
        try {
            String query = "SELECT name FROM tip";
            Statement statement = connect_to_db().createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                String stat = resultSet.getString("name");
                tip.add(stat);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return tip;
    }

    public ObservableList<String> getComment() {
        ObservableList<String> comment = FXCollections.observableArrayList();
        try {
            String query = "SELECT equipment_repair FROM execution_request";
            Statement statement = connect_to_db().createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                String stat = resultSet.getString("equipment_repair");
                comment.add(stat);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return comment;
    }

    public ObservableList<String> getMaterials() {
        ObservableList<String> materials = FXCollections.observableArrayList();
        try {
            String query = "SELECT name FROM materials";
            Statement statement = connect_to_db().createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                String stat = resultSet.getString("name");
                materials.add(stat);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return materials;
    }

    public ObservableMap<String, Integer> getFaultStatistics() {
        ObservableMap<String, Integer> statistics = FXCollections.observableHashMap();
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/remont", "postgres", "123");
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT tip, COUNT(*) AS count FROM requests GROUP BY tip")) {
            while (resultSet.next()) {
                String fault = resultSet.getString("tip");
                int count = resultSet.getInt("count");
                statistics.put(fault, count);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return statistics;
    }
    public int getCompletedRequestCount() {
        int completedCount = 0;
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/remont", "postgres", "123");
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) AS count FROM requests WHERE status = 'Выполнено'")) {
            if (resultSet.next()) {
                completedCount = resultSet.getInt("count");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return completedCount;
    }
    public String getAverageRequestTime() {
        try {
            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/remont", "postgres", "123");
            String query = "SELECT AVG(CAST(time AS DECIMAL)) FROM requests";
            PreparedStatement statement = conn.prepareStatement(query);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                double averageTime = rs.getDouble(1);
                return String.valueOf(averageTime);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "0"; // возвращаем "0", если произошла ошибка или таблица пустая
    }
}