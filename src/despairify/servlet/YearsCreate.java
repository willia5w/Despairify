package despairify.servlet;

import despairify.dal.*;
import despairify.model.*;

import java.io.IOException;
import java.sql.SQLException;
import java.time.Year;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/yearcreate")
public class YearsCreate extends HttpServlet {
	
	protected YearsDao yearsDao;
	
	@Override
	public void init() throws ServletException {
		yearsDao = YearsDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        //Just render the JSP.   
        req.getRequestDispatcher("/Years.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve and validate name.
        String year = req.getParameter("createdyear");
        if (year == null || year.trim().isEmpty()) {
            messages.put("success", "Invalid country code");
        } else {
        	// Create the Year.
	        try {
	        	// Exercise: parse the input for StatusLevel.
	        	
	        	Year resultYearConverted = Year.of(Integer.parseInt(year));
	        	
				Years newYear = new Years(resultYearConverted);
				
	        	newYear = yearsDao.create(newYear);
	        	
	        	messages.put("success", "Successfully created " + newYear.getYear());
	        } catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/Years.jsp").forward(req, resp);
    }
}
