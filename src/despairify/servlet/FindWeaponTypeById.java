package despairify.servlet;

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

import despairify.dal.*;
import despairify.model.*;


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
@WebServlet("/findweapontypes")
public class FindWeaponTypeById extends HttpServlet {
	
	/**
	 * Satisfies warning.
	 */
//	private static final long serialVersionUID = 1L;
//	
	
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

        List<WeaponTypes> weaponTypes = new ArrayList<WeaponTypes>();
        
        // Retrieve and validate name.
        // user name is retrieved from the URL query string.
        String weaponTypeId = req.getParameter("weapontypeid");
        if (weaponTypeId == null || weaponTypeId.trim().isEmpty()) {
            messages.put("success", "Please enter a valid Weapon Type Id.");
        } else {
        	// Retrieve Users, and store as a message.
        	try {
        		weaponTypes.add(weaponTypesDao.getWeaponTypeByWeaponTypeId(Integer.parseInt(weaponTypeId)));
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	messages.put("success", "Displaying results for Weapon Type Id: " + weaponTypeId);
        	// Save the previous search term, so it can be used as the default
        	// in the input box when rendering FindUsers.jsp.
        	messages.put("previousUserName", weaponTypeId);
        }
        req.setAttribute("weapontypes", weaponTypes);
        
        req.getRequestDispatcher("/AccessWeaponTypes.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        List<WeaponTypes> weaponTypes = new ArrayList<WeaponTypes>();
        
        // Retrieve and validate name.
        // user name is retrieved from the form POST submission. By default, it
        // is populated by the URL query string (in FindUsers.jsp).
        String weaponTypeId = req.getParameter("weapontypeid");
        if (weaponTypeId == null || weaponTypeId.trim().isEmpty()) {
            messages.put("success", "Please enter a valid Weapon Type Id.");
        } else {
        	// Retrieve Users, and store as a message.
        	try {
        		weaponTypes.add(weaponTypesDao.getWeaponTypeByWeaponTypeId(Integer.parseInt(weaponTypeId)));
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	messages.put("success", "Displaying results for Weapon Type Id: " + weaponTypeId);
        }
        req.setAttribute("weapontypes", weaponTypes);
        
        req.getRequestDispatcher("/AccessWeaponTypes.jsp").forward(req, resp);
    }
}
