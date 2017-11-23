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
    private static final int PARAMETER_INDEX_FIRST = 1;
    private static final int PARAMETER_INDEX_SECOND = 2;
    private static final int FIRST_COLUMN = 1;
    private static final int SECOND_COLUMN = 2;
    private static final int THIRD_COLUMN = 3;
    private static final int FORTH_COLUMN = 4;

    private volatile static boolean isInitialized = false;


    @Override
    public Person getPerson(String name, String surname) throws DAOException {
        registerDriver();
        try (Connection connection = DriverManager.getConnection(URL_DB, USER_DB, PASSWORD_DB)) {
            PreparedStatement statement = connection.prepareStatement(SELECT_PERSON_BY_NAME_SURNAME);
            statement.setString(PARAMETER_INDEX_FIRST, name);
            statement.setString(PARAMETER_INDEX_SECOND, surname);
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
        person.setName(resultSet.getString(FIRST_COLUMN));
        person.setSurname(resultSet.getString(SECOND_COLUMN));
        person.setNumber(resultSet.getString(THIRD_COLUMN));
        person.setEmail(resultSet.getString(FORTH_COLUMN));
        return person;
    }


    private synchronized static void registerDriver() throws DAOException {
        try {// прикольно), в будущем для такой инициализации можно использовать возможности сервета или листенеров
            if (!isInitialized) {
                Class.forName(DRIVER_DB);
                isInitialized = true;
            }
        } catch (ClassNotFoundException e) {
            throw new DAOException(e);// если выбрасываешь свое исключение, то на это есть причина и ее надо указать в сообщении 
            // throw new DAOException("my message", e);
        }
    }

}
