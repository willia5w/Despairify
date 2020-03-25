package despairify.servlet;

import despairify.dal.*;
import despairify.model.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/findcountries")
public class FindCountries extends HttpServlet {

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

        List<AgeRanges> countries = new ArrayList<Countries>();

        String countryalpha3code = req.getParameter("countryalpha3code");

        if (countryalpha3code == null || countryalpha3code.trim().isEmpty()) {
            messages.put("success", "Please enter a valid country code.");
        } else {
            try {
                Countries returnedCountry = countriesDao.getCountryByAlpha3Code(countryAlpha3Code);
                countries.add(returnedCountry);
            } catch (SQLException e) {
                e.printStackTrace();
                throw new IOException(e);
            }
            messages.put("success", "Displaying result for " + countryalpha3code);
            // Save the previous search term, so it can be used as the default
            // in the input box when rendering FindUsers.jsp.
            messages.put("previousCode", countryalpha3code);
        }
        req.setAttribute("countries", countryalpha3code);

        req.getRequestDispatcher("/FindCountries.jsp").forward(req, resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {

        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        List<AgeRanges> countries = new ArrayList<Countries>();

        String countryalpha3code = req.getParameter("countryalpha3code");
        if (countryalpha3code == null || countryalpha3code.trim().isEmpty()) {
            messages.put("success", "Please enter a valid country code.");
        } else {

            try {
                Countries returnedCountry = countriesDao.getCountryByAlpha3Code(countryAlpha3Code);
                if (returnedCountry != null) {
                    countries.add(returnedCountry);
                }
            } catch (SQLException e) {
                e.printStackTrace();
                throw new IOException(e);
            }
            if (countries.size() < 1) {
                messages.put("success", "Country " + countryalpha3code + " doesn't exist.");
            } else {
                messages.put("success", "Displaying results for " + countryalpha3code);
            }
        }
        req.setAttribute("countries", countries);

        req.getRequestDispatcher("/Countries.jsp").forward(req, resp);

    }
}