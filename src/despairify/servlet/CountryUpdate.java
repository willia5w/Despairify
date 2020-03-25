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


@WebServlet("/countryupdate")
public class CountryUpdate extends HttpServlet {

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

        // Retrieve user and validate.
        String countryAlpha3Code = req.getParameter("countryalpha3code");

        if (countryAlpha3Code == null || countryAlpha3Code.trim().isEmpty()) {
            messages.put("success", "Please enter a valid Country.");
        } else {
            try {
                Countries country = countriesDao.getCountryByAlpha3Code(countryAlpha3Code);
                if(country == null) {
                    messages.put("success", "Country does not exist.");
                }
                messages.put("title", "Country " + country.getCountryName());
                req.setAttribute("country", country);
            } catch (SQLException e) {
                e.printStackTrace();
                throw new IOException(e);
            }
        }

        req.getRequestDispatcher("/CountryUpdate.jsp").forward(req, resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve user and validate.
        String countryAlpha3Code = req.getParameter("countryalpha3code");

        if (countryAlpha3Code == null || countryAlpha3Code.trim().isEmpty()) {
            messages.put("success", "Please enter a valid Country.");
        } else {
            try {
                Countries country = countriesDao.getCountryByAlpha3Code(countryAlpha3Code);
                if(country == null) {
                    messages.put("success", "Country does not exist.");
                } else {
                    String newCountryName = req.getParameter("newCountryName");
                    if (newCountryName == null || newCountryName.trim().isEmpty()) {
                        messages.put("success", "Please enter a valid country name")
                    } else {
                        country = countriesDao.updateCountryName(country, newCountryName);
                        messages.put("success", "Successfully updated country with code " + country.getAlpha3Code());
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
                throw new IOException(e);
            }
        }

        req.getRequestDispatcher("/CountryUpdate.jsp").forward(req, resp);
    }
}
