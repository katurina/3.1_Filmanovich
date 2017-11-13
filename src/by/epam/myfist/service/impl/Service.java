package by.epam.myfist.service.impl;

import by.epam.myfist.dao.DAOFactory;
import by.epam.myfist.dao.exception.DAOException;
import by.epam.myfist.entity.Person;
import by.epam.myfist.service.ServiceAppliance;
import by.epam.myfist.service.exception.ServiceException;

public class Service implements ServiceAppliance {

    public Person find(String name, String surname) throws ServiceException {
        try {
            return DAOFactory.getInstance().getDaoAppliance().getPerson(name, surname);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }
}
