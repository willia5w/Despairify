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


@WebServlet("/agerangedelete")
public class AgeRangeDelete extends HttpServlet {
	
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
        // Provide a title and render the JSP.
        messages.put("title", "Delete Age Range");        
        req.getRequestDispatcher("/AgeRangeDelete.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve and validate name.
        String ageRangeId = req.getParameter("agerangeid");
        if (ageRangeId == null || ageRangeId.trim().isEmpty()) {
            messages.put("title", "Invalid AgeRange");
            messages.put("disableSubmit", "true");
        } else {
	        try {
	        	AgeRanges ageRange = ageRangesDao.getAgeRangeByAgeRangeId(Integer.parseInt(ageRangeId));
	        	ageRange = ageRangesDao.delete(ageRange);
	        	// Update the message.
		        if (ageRange == null) {
		            messages.put("title", "Successfully deleted " + ageRangeId);
		            messages.put("disableSubmit", "true");
		        } else {
		        	messages.put("title", "Failed to delete " + ageRangeId);
		        	messages.put("disableSubmit", "false");
		        }
	        } catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/AgeRangeDelete.jsp").forward(req, resp);
    }
}
