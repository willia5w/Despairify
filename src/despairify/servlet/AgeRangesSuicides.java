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


@WebServlet("/agerangesuicides")
public class AgeRangesSuicides extends HttpServlet {
	
	protected SuicidesDao suicidesDao;
	
	@Override
	public void init() throws ServletException {
		suicidesDao = SuicidesDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

		// Retrieve and validate UserName.
        String ageRangeId = req.getParameter("agerangeid");
        
        AgeRangesDao ageRangesDao = AgeRangesDao.getInstance();
        
        
        if (ageRangeId == null || ageRangeId.trim().isEmpty()) {
            messages.put("title", "Invalid age range id.");
        } else {
        	AgeRanges ageRange;
			try {
				ageRange = ageRangesDao.getAgeRangeByAgeRangeId(Integer.parseInt(ageRangeId));
				messages.put("title", "Suicide data for age " + ageRange.getAgeRange());
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	
        }
        
        // Retrieve BlogUsers, and store in the request.
        List<Suicides> suicides = new ArrayList<Suicides>();
        try {
        	suicides = suicidesDao.getSuicidesByAgeRangeId(Integer.parseInt(ageRangeId));
        } catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
        }
        req.setAttribute("suicides", suicides);
        req.getRequestDispatcher("/AgeRangeSuicides.jsp").forward(req, resp);
	}
}
