package despairify.servlet;

import despairify.dal.*;
import despairify.model.*;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/agerangecreate")
public class AgeRangeCreate extends HttpServlet {
	
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
        //Just render the JSP.   
        req.getRequestDispatcher("/AgeRangeCreate.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve and validate name.
        String agerange = req.getParameter("agerange");
        if (agerange == null || agerange.trim().isEmpty()) {
            messages.put("success", "Invalid age range");
        } else {
	        try {

	        	AgeRanges ageRange = new AgeRanges(agerange);
	        	ageRange = ageRangesDao.create(ageRange);
	        	messages.put("success", "Successfully created " + agerange);
	        } catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/AgeRangeCreate.jsp").forward(req, resp);
    }
}
