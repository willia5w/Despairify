package despairify.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import despairify.dal.*;
import despairify.model.*;


@WebServlet("/createweapontype")
public class CreateWeaponType extends HttpServlet {
	
	protected WeaponTypesDao weaponTypesDao;
	
	@Override
	public void init() throws ServletException {
		weaponTypesDao = WeaponTypesDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        //Just render the JSP.   
        req.getRequestDispatcher("/AccessWeaponTypes.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve and validate name.
        String weaponType = req.getParameter("weapontype");
        if (weaponType == null || weaponType.trim().isEmpty()) {
            messages.put("success", "Invalid Weapon Type");
        } else {
        	// Create the Weapon Type.
    
        
	        try {
	        	WeaponTypes newWeaponType = new WeaponTypes(weaponType);
	        	newWeaponType = weaponTypesDao.create(newWeaponType);
	        	messages.put("success", "Successfully created " + weaponType + " with Id: " + newWeaponType.getWeaponTypeId());
	        } catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/AccessWeaponTypes.jsp").forward(req, resp);
    }
}
