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


/**
 * FindUsers is the primary entry point into the application.
 * 
 * Note the logic for doGet() and doPost() are almost identical. However, there is a difference:
 * doGet() handles the http GET request. This method is called when you put in the /findusers
 * URL in the browser.
 * doPost() handles the http POST request. This method is called after you click the submit button.
 * 
 * To run:
 * 1. Run the SQL script to recreate your database schema: http://goo.gl/86a11H.
 * 2. Insert test data. You can do this by running blog.tools.Inserter (right click,
 *    Run As > JavaApplication.
 *    Notice that this is similar to Runner.java in our JDBC example.
 * 3. Run the Tomcat server at localhost.
 * 4. Point your browser to http://localhost:8080/BlogApplication/findusers.
 */
@WebServlet("/findageranges")
public class FindAgeRanges extends HttpServlet {
	
	protected AgeRangesDao ageRangesDao;
	
	@Override
	public void init() throws ServletException {
		ageRangesDao = AgeRangesDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        List<AgeRanges> ageRanges = new ArrayList<AgeRanges>();
        
        // Retrieve and validate name.
        // firstname is retrieved from the URL query string.
        String ageRange = req.getParameter("agerange");
        
        if (ageRange == null || ageRange.trim().isEmpty()) {
            messages.put("success", "Please enter a valid age range.");
        } else {
        	// Retrieve BlogUsers, and store as a message.
        	try {
            	AgeRanges returnedAgeRange = ageRangesDao.getAgeRangeByAgeRange(ageRange);
            	ageRanges.add(returnedAgeRange);
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	messages.put("success", "Displaying result for " + ageRange);
        	// Save the previous search term, so it can be used as the default
        	// in the input box when rendering FindUsers.jsp.
        	messages.put("previousAgeRange", ageRange);
        }
        req.setAttribute("ageRanges", ageRanges);
        
        req.getRequestDispatcher("/FindAgeRanges.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {

        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        List<AgeRanges> ageRanges = new ArrayList<AgeRanges>();
        
        // Retrieve and validate name.
        // firstname is retrieved from the form POST submission. By default, it
        // is populated by the URL query string (in FindUsers.jsp).
        String ageRange = req.getParameter("agerange");
        if (ageRange == null || ageRange.trim().isEmpty()) {
            messages.put("success", "Please enter a valid age range.");
        } else {
        	// Retrieve AgeRanges, and store as a message.
        	try {
        		AgeRanges returnedAgeRange = ageRangesDao.getAgeRangeByAgeRange(ageRange);
        		if (returnedAgeRange != null) {
        			ageRanges.add(returnedAgeRange);
        		}
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	if (ageRanges.size() < 1) {
        		messages.put("success", "Age range " + ageRange + " doesn't exist.");
        	} else {
        		messages.put("success", "Displaying results for " + ageRange);
        	}
        }
        req.setAttribute("ageRanges", ageRanges);
        
        req.getRequestDispatcher("/FindAgeRanges.jsp").forward(req, resp);

    }
}
