package by.epam.myfist.service;

import by.epam.myfist.service.impl.Service;

public class ServiceFactory {

    private final ServiceAppliance serviceAppliance = new Service();

    private static final ServiceFactory instance = new ServiceFactory();

    private ServiceFactory() {
    }

    public static ServiceFactory getInstance() {
        return instance;
    }

    public ServiceAppliance getServiceAppliance() {
        return serviceAppliance;
    }
}
