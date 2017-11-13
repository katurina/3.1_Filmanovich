package by.epam.myfist.dao.impl;

import by.epam.myfist.dao.DAOAppliance;
import by.epam.myfist.dao.exception.DAOException;
import by.epam.myfist.entity.Person;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DAOApplianceImpl implements DAOAppliance {

    private static final String DRIVER_DB = "com.mysql.jdbc.Driver";
    private static final String URL_DB = "jdbc:mysql://localhost:3306/persons";
    private static final String USER_DB = "root";
    private static final String PASSWORD_DB = "root";
    private static final String SELECT_PERSON_BY_NAME_SURNAME =
            "SELECT name,surname,number,email FROM users WHERE name = ? AND surname = ?";

    private volatile static boolean isInitialized = false;


    @Override
    public Person getPerson(String name, String surname) throws DAOException {
        registerDriver();
        try (Connection connection = DriverManager.getConnection(URL_DB, USER_DB, PASSWORD_DB)) {
            PreparedStatement statement = connection.prepareStatement(SELECT_PERSON_BY_NAME_SURNAME);
            statement.setString(1, name);
            statement.setString(2, surname);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return createPerson(resultSet);
                }
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return null;
    }

    private Person createPerson(ResultSet resultSet) throws SQLException {
        Person person = new Person();
        person.setName(resultSet.getString(1));
        person.setSurname(resultSet.getString(2));
        person.setNumber(resultSet.getString(3));
        person.setEmail(resultSet.getString(4));
        return person;
    }


    private synchronized static void registerDriver() throws DAOException {
        try {
            if (!isInitialized) {
                Class.forName(DRIVER_DB);
                isInitialized = true;
            }
        } catch (ClassNotFoundException e) {
            throw new DAOException(e);
        }
    }

}
