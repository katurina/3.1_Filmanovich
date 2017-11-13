package by.epam.myfist.dao;

import by.epam.myfist.dao.impl.DAOApplianceImpl;

public class DAOFactory {
    private final DAOAppliance daoAppliance = new DAOApplianceImpl();
    private static final DAOFactory instance = new DAOFactory();

    private DAOFactory(){}

    public static DAOFactory getInstance(){
        return instance;
    }

    public DAOAppliance getDaoAppliance() {
        return daoAppliance;
    }
}
