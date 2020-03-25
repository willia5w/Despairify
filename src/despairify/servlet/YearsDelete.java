package despairify.servlet;

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

import despairify.dal.YearsDao;
import despairify.model.Years;


@WebServlet("/yeardelete")
public class YearsDelete extends HttpServlet {
	
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
        // Provide a title and render the JSP.
        messages.put("title", "Delete Year");        
        req.getRequestDispatcher("/Years.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve and validate name.
        int yearVal = Integer.parseInt(req.getParameter("year"));
        if (yearVal < 0) {
            messages.put("success", "Please enter a valid year.");
        } else {
        	// Delete the Year.
        	Year resultYearConverted = Year.of(yearVal);
	        Years year = new Years(resultYearConverted);
	        
	        try {
	        	year = yearsDao.delete(year);
	        	// Update the message.
		        if (year == null) {
		            messages.put("title", "Successfully deleted " + yearVal);
		            messages.put("disableSubmit", "true");
		        } else {
		        	messages.put("title", "Failed to delete " + yearVal);
		        	messages.put("disableSubmit", "false");
		        }
	        } catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/Years.jsp").forward(req, resp);
    }
}