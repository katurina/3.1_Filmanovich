package by.epam.myfist.service;

import by.epam.myfist.entity.Person;
import by.epam.myfist.service.exception.ServiceException;

public interface ServiceAppliance {
    public Person find(String name, String surname) throws ServiceException;
}
