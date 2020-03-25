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


@WebServlet("/deleteweapontypes")
public class DeleteWeaponType extends HttpServlet {
	
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
        // Provide a title and render the JSP.
        messages.put("title", "Delete Weapon Type");        
        req.getRequestDispatcher("/AccessWeaponTypes.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve and validate Id.
        String weaponTypeId = req.getParameter("weapontypeid");
        if (weaponTypeId == null || weaponTypeId.trim().isEmpty()) {
            messages.put("title", "Invalid Weapon Type Id");
            messages.put("disableSubmit", "true");
        } else {
        	// Delete the Weapon Type.
	        
	        try {
	        	WeaponTypes weaponType = weaponTypesDao.getWeaponTypeByWeaponTypeId(Integer.parseInt(weaponTypeId));
	        	weaponType = weaponTypesDao.delete(weaponType);
	        	// Update the message.
		        if (weaponType == null) {
		            messages.put("title", "Successfully deleted " + weaponTypeId);
		            messages.put("disableSubmit", "true");
		        } else {
		        	messages.put("title", "Failed to delete " + weaponTypeId);
		        	messages.put("disableSubmit", "false");
		        }
	        } catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/AccessWeaponTypes.jsp").forward(req, resp);
    }
}
