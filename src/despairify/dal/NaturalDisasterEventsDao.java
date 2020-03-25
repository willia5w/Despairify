package despairify.dal;

import despairify.model.NaturalDisasterEvents;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class NaturalDisasterEventsDao {

    protected ConnectionManager connectionManager;

    private static NaturalDisasterEventsDao instance = null;

    public NaturalDisasterEventsDao() {
        connectionManager = new ConnectionManager();
    }

    public static NaturalDisasterEventsDao getInstance() {
        if (instance == null) {
            instance = new NaturalDisasterEventsDao();
        }
        return instance;
    }

    public NaturalDisasterEvents create(NaturalDisasterEvents naturalDisasterEvent)
        throws SQLException {
        String insertNaturalDisasterEvent =
            "INSERT INTO NaturalDisasterEvents(NaturalDisasterEvent) " +
                "VALUES(?, ?, ?, ?, ?, ?, ?);";
        Connection connection = null;
        PreparedStatement insertStmt = null;
        ResultSet resultKey = null;
        try {
            connection = connectionManager.getConnection();
            insertStmt = connection.prepareStatement(insertNaturalDisasterEvent,
                Statement.RETURN_GENERATED_KEYS);
            insertStmt.setString(1, naturalDisasterEvent.getCountryAlpha3Code());
            insertStmt.setInt(2, naturalDisasterEvent.getTotalDeaths());
            insertStmt.setInt(3, naturalDisasterEvent.getInjured());
            insertStmt.setInt(4, naturalDisasterEvent.getAffected());
            insertStmt.setInt(5, naturalDisasterEvent.getHomeless());
            insertStmt.setInt(6, naturalDisasterEvent.getDamageUSD());
            insertStmt.setString(7, naturalDisasterEvent.getDisasterType().name());
            insertStmt.executeUpdate();

            resultKey = insertStmt.getGeneratedKeys();
            int eventId = -1;
            if (resultKey.next()) {
                eventId = resultKey.getInt(1);
            } else {
                throw new SQLException("Unable to retrieve auto-generated key.");
            }
            naturalDisasterEvent.setEventId(eventId);
            return naturalDisasterEvent;

        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (connection != null) {
                connection.close();
            }
            if (insertStmt != null) {
                insertStmt.close();
            }
        }
    }

    public NaturalDisasterEvents getNaturalDisasterEventById(int eventId) throws SQLException {
        String selectEvent = "SELECT * FROM NaturalDisasterEvents WHERE EventId=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectEvent);
            selectStmt.setInt(1, eventId);

            results = selectStmt.executeQuery();

            if (results.next()) {
                int resultEventId = results.getInt("EventId");
                String countryAlpha3Code = results.getString("CountryAlpha3Code");
                int totalDeaths = results.getInt("TotalDeaths");
                int injured = results.getInt("Injured");
                int affected = results.getInt("Affected");
                int homeless = results.getInt("Homeless");
                int damageUSD = results.getInt("DamageUSD");
                NaturalDisasterEvents.DisasterType disasterType =
                    NaturalDisasterEvents.DisasterType.valueOf(results.getString("DisasterType"));

                return new NaturalDisasterEvents(resultEventId, countryAlpha3Code, totalDeaths,
                    injured, affected, homeless, damageUSD, disasterType);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (connection != null) {
                connection.close();
            }
            if (selectStmt != null) {
                selectStmt.close();
            }
            if (results != null) {
                results.close();
            }
        }
        return null;
    }
}
