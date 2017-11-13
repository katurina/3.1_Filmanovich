package by.epam.myfist.dao;

import by.epam.myfist.dao.exception.DAOException;
import by.epam.myfist.entity.Person;

public interface DAOAppliance {
    public Person getPerson(String name, String surname) throws DAOException;
}
