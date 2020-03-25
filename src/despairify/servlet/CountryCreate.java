package despairify.servlet;

@WebServlet("/countrycreate")
public class CountryCreate extends HttpServlet {

    protected CountriesDao countriesDao;

    @Override
    public void init() throws ServletException {
        countriesDao = CountriesDao.getInstance();
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        //Just render the JSP.
        req.getRequestDispatcher("/Country.jsp").forward(req, resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve and validate name.
        String countryCode = req.getParameter("countryalpha3code");
        if (countryCode == null || countryCode.trim().isEmpty()) {
            messages.put("success", "Invalid country code");
        } else {
            try {
                String countryName = req.getParameter("countryname")
                Countries country = new Countries(countryCode, countryName);
                country = countriesDao.create(country);
                messages.put("success", "Successfully created " + countryCode);
            } catch (SQLException e) {
                e.printStackTrace();
                throw new IOException(e);
            }
        }
        req.getRequestDispatcher("/Country.jsp").forward(req, resp);
    }
}