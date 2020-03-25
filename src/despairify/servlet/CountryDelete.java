package despairify.servlet;

import despairify.dal.*;
import despairify.model.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/countrydelete")
public class CountryDelete extends HttpServlet {

    protected CountriesDao countriesDao;

    @Override
    public void init() throws ServletException {
        countriesDao = CountriesDao.getInstance();
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        // Provide a title and render the JSP.
        messages.put("title", "Delete Country");
        req.getRequestDispatcher("/Country.jsp").forward(req, resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve and validate name.
        String countryalpha3code = req.getParameter("countryalpha3code");
        if (countryalpha3code == null || countryalpha3code.trim().isEmpty()) {
            messages.put("title", "Invalid Country");
            messages.put("disableSubmit", "true");
        } else {
            try {
                Countries country = countriesDao.getCountryByAlpha3Code(countryalpha3code);
                country = countriesDao.delete(country);
                // Update the message.
                if (country == null) {
                    messages.put("title", "Successfully deleted " + countryalpha3code);
                    messages.put("disableSubmit", "true");
                } else {
                    messages.put("title", "Failed to delete " + countryalpha3code);
                    messages.put("disableSubmit", "false");
                }
            } catch (SQLException e) {
                e.printStackTrace();
                throw new IOException(e);
            }
        }

        req.getRequestDispatcher("/Country.jsp").forward(req, resp);
    }
}
