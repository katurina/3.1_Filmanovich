package by.epam.myfist.controller;

import by.epam.myfist.entity.Person;
import by.epam.myfist.service.ServiceAppliance;
import by.epam.myfist.service.ServiceFactory;
import by.epam.myfist.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Controller extends HttpServlet {

    private static final String PERSON = "person";
    private static final String PARAM_NAME = "name";
    private static final String PARAM_SURNAME = "surname";
    private static final String MAIN_JSP = "main.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            ServiceAppliance serviceAppliance = ServiceFactory.getInstance().getServiceAppliance();
            String name = request.getParameter(PARAM_NAME);
            String surname = request.getParameter(PARAM_SURNAME);
            Person person = serviceAppliance.find(name, surname);
            request.setAttribute(PERSON, person);

            request.getRequestDispatcher(MAIN_JSP).forward(request, response);

        } catch (ServiceException e) {
            e.printStackTrace();
            throw new RuntimeException(e);//?????????? силы не хватило додумать?
            // а переправить пользователя на страницу ошибок?
            // зачем runtime в виртуальную машину кидать, да еще добровольно
        }
    }
}
