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


@WebServlet("/agerangeupdate")
public class AgeRangeUpdate extends HttpServlet {
	
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

        // Retrieve user and validate.
        String ageRangeId = req.getParameter("agerangeid");
        
        if (ageRangeId == null || ageRangeId.trim().isEmpty()) {
            messages.put("success", "Please enter a valid Age Range.");
        } else {
        	try {
        		AgeRanges ageRange = ageRangesDao.getAgeRangeByAgeRangeId(Integer.parseInt(ageRangeId));
        		if(ageRange == null) {
        			messages.put("success", "Age Range does not exist.");
        		}
        		messages.put("title", "Age Range for " + ageRange.getAgeRange());
        		req.setAttribute("ageRange", ageRange);
        	} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/AgeRangeUpdate.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve user and validate.
        String ageRangeId = req.getParameter("agerangeid");
        if (ageRangeId == null || ageRangeId.trim().isEmpty()) {
            messages.put("success", "Please enter a valid Age Range.");
        } else {
        	try {
        		AgeRanges ageRange = ageRangesDao.getAgeRangeByAgeRangeId(Integer.parseInt(ageRangeId));
        		if(ageRange == null) {
        			messages.put("success", "Age Range does not exist. No update to perform.");
        		} else {
        			String newAgeRange = req.getParameter("newagerange");
        			if (newAgeRange == null || newAgeRange.trim().isEmpty()) {
        	            messages.put("success", "Please enter a valid LastName.");
        	        } else {
        	        	ageRange = ageRangesDao.updateAgeRange(ageRange, newAgeRange);
        	        	messages.put("success", "Successfully updated age range with id " + ageRange.getAgeRangeId());
        	        }
        		}
        		req.setAttribute("ageRange", ageRange);
        	} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/AgeRangeUpdate.jsp").forward(req, resp);
    }
}
