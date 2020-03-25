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


@WebServlet("/terroristeventsbyweapontype")
public class TerroristEventsByWeaponType extends HttpServlet {
	
	protected TerroristEventsDao terroristEventsDao;
	
	@Override
	public void init() throws ServletException {
		terroristEventsDao = TerroristEventsDao.getInstance();
	}
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
		
        List<TerroristEvents> events = new ArrayList<TerroristEvents>();
        
		// Retrieve BlogComments depending on valid PostId or UserName.
        String weaponTypeId = req.getParameter("weapontypeid");
        
        try {
	        if (weaponTypeId != null && !weaponTypeId.trim().isEmpty()) {
	        	events = terroristEventsDao.getTerroristEventsByWeaponTypeId(Integer.parseInt(weaponTypeId));
	        	messages.put("title", "Terrorist Events for Weapon Type Id " + weaponTypeId);
	        } else {
	        	messages.put("title", "Invalid Weapon Type Id.");
	        }
        } catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
        }
        
        req.setAttribute("events", events);
        req.getRequestDispatcher("/AccessWeaponTypes.jsp").forward(req, resp);
	}
}
