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


@WebServlet("/updateweapontypes")
public class UpdateWeaponType extends HttpServlet {
	
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

        // Retrieve weapon type and validate.
        String weaponTypeId = req.getParameter("weapontypeid");
        if (weaponTypeId == null || weaponTypeId.trim().isEmpty()) {
            messages.put("success", "Please enter a valid existing Weapon Type Id.");
        } else {
        	try {
        		WeaponTypes weaponType = weaponTypesDao.getWeaponTypeByWeaponTypeId(Integer.parseInt(weaponTypeId));
        		if(weaponType == null) {
        			messages.put("success", "Weapon Type does not exist.");
        		}
        		req.setAttribute("weaponType", weaponType);
        	} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/AccessWeaponTypes.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve Weapon Type and validate.
        String weaponTypeId = req.getParameter("weapontypeid");
        if (weaponTypeId == null || weaponTypeId.trim().isEmpty()) {
            messages.put("updatesuccess", "Please enter a valid Weapon Type Id.");
        } else {
        	try {
        		WeaponTypes weaponType = weaponTypesDao.getWeaponTypeByWeaponTypeId(Integer.parseInt(weaponTypeId));
        		if(weaponType == null) {
        			messages.put("updatesuccess", "Weapon Type does not exist. No update to perform.");
        		} else {
        			String newWeaponType = req.getParameter("newweapontype");
        			if (newWeaponType == null || newWeaponType.trim().isEmpty()) {
        	            messages.put("updatesuccess", "Please enter a valid Weapon Type.");
        	        } else {
        	        	weaponType = weaponTypesDao.updateWeaponType(weaponType, newWeaponType);
        	        	messages.put("updatesuccess", "Successfully updated Weapon Type Id: " + weaponTypeId);
        	        }
        		}
        		req.setAttribute("weaponType", weaponType);
        	} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/AccessWeaponTypes.jsp").forward(req, resp);
    }
}
