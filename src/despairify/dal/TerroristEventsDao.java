package despairify.dal;

import despairify.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Data access object (DAO) class to interact with the underlying WeaponTypes table in your MySQL
 * instance. This is used to store {@link Companies} into your MySQL instance and retrieve 
 * {@link Companies} from MySQL instance.
 */
public class TerroristEventsDao {
	protected ConnectionManager connectionManager;
	
	// Single pattern: instantiation is limited to one object.
	private static TerroristEventsDao instance = null;
	protected TerroristEventsDao() {
		connectionManager = new ConnectionManager();
	}
	public static TerroristEventsDao getInstance() {
		if(instance == null) {
			instance = new TerroristEventsDao();
		}
		return instance;
	}

	public TerroristEvents create(TerroristEvents terroristEvent) throws SQLException {
		String insertTerroristEvent = "INSERT INTO TerroristEvents(terroristEvent) VALUES(?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();

			insertStmt = connection.prepareStatement(insertTerroristEvent,
					Statement.RETURN_GENERATED_KEYS);

			insertStmt.setInt(1, terroristEvent.getYear());
			insertStmt.setInt(2, terroristEvent.getMonth());
			insertStmt.setInt(3, terroristEvent.getDay());
			insertStmt.setString(4, terroristEvent.getCountryName());
			insertStmt.setString(5, terroristEvent.getRegion());
			insertStmt.setString(6, terroristEvent.getProvState());
			insertStmt.setString(7, terroristEvent.getCity());
			insertStmt.setString(8, terroristEvent.getLatitude());
			insertStmt.setString(9, terroristEvent.getLongitude());
			insertStmt.setInt(10, terroristEvent.getSuicide());
			insertStmt.setInt(11, terroristEvent.getAttackTypeId());
			insertStmt.setInt(12, terroristEvent.getTargetTypeId());
			insertStmt.setString(13, terroristEvent.getTarget());
			insertStmt.setString(14, terroristEvent.getNationality());
			insertStmt.setInt(15, terroristEvent.getWeaponTypeId());
			insertStmt.executeUpdate();
			
			
			resultKey = insertStmt.getGeneratedKeys();
			int eventId = -1;
			if(resultKey.next()) {
				eventId = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			terroristEvent.setEventId(eventId);
			
			return terroristEvent;

		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(insertStmt != null) {
				insertStmt.close();
			}
		}
	}
	
	public TerroristEvents updateTerroristEventWeaponType(TerroristEvents terroristEvent, int newWeaponTypeId) throws SQLException {
		String updateTerroristEventWeaponType = "UPDATE TerroristEvents SET WeaponTypeId=? WHERE EventId=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateTerroristEventWeaponType);
			
			updateStmt.setInt(1, newWeaponTypeId);
			updateStmt.setInt(2, terroristEvent.getEventId());
			
			updateStmt.executeUpdate();
			
			terroristEvent.setWeaponTypeId(newWeaponTypeId);
			
			return terroristEvent;
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(updateStmt != null) {
				updateStmt.close();
			}
		}
	}

	public TerroristEvents delete(TerroristEvents terroristEvent) throws SQLException {
		String deleteTerroristEvent = "DELETE FROM TerroristEvents WHERE EventId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteTerroristEvent);
			
			deleteStmt.setInt(1, terroristEvent.getEventId());
			deleteStmt.executeUpdate();

			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(deleteStmt != null) {
				deleteStmt.close();
			}
		}
	}
	
	
	public TerroristEvents getTerroristEventByEventId(int eventId) throws SQLException {
		String selectTerroristEvent = "SELECT * FROM TerroristEvents WHERE EventId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectTerroristEvent);
			selectStmt.setInt(1, eventId);

			results = selectStmt.executeQuery();

			if(results.next()) {
				int resultEventId = results.getInt("EventId");
				int resultYear = results.getInt("Year");
				int resultMonth = results.getInt("Month");
				int resultDay = results.getInt("Day");
				String resultCountryName = results.getString("CountryName");
				String resultRegion = results.getString("Region");
				String resultProvState = results.getString("ProvState");
				String resultCity = results.getString("City");
				String resultLatitude = results.getString("Latitude");
				String resultLongitude = results.getString("Longitude");
				int resultSuicide = results.getInt("Suicide");
				int resultAttackTypeId = results.getInt("AttackTypeId");
				int resultTargetTypeId = results.getInt("TargetTypeId");
				String resultTarget = results.getString("Target");
				String resultNationality = results.getString("Nationality");
				int resultWeaponTypeId = results.getInt("WeaponTypeId");

				TerroristEvents terroristEvent = new TerroristEvents(
						resultEventId,
						resultYear,
						resultMonth,
						resultDay,
						resultCountryName,
						resultRegion,
						resultProvState,
						resultCity,
						resultLatitude,
						resultLongitude,
						resultSuicide,
						resultAttackTypeId,
						resultTargetTypeId,
						resultTarget,
						resultNationality,
						resultWeaponTypeId);
				return terroristEvent;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(selectStmt != null) {
				selectStmt.close();
			}
			if(results != null) {
				results.close();
			}
		}
		return null;
	}
	
	
	public List<TerroristEvents> getTerroristEventsByWeaponTypeId(int weaponTypeId) throws SQLException {
		List<TerroristEvents> terroristEvents = new ArrayList<TerroristEvents>();
		
		String selectTerroristEvent = "SELECT * FROM TerroristEvents WHERE WeaponTypeId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectTerroristEvent);
			selectStmt.setInt(1, weaponTypeId);

			results = selectStmt.executeQuery();

			while(results.next()) {
				int resultEventId = results.getInt("EventId");
				int resultYear = results.getInt("Year");
				int resultMonth = results.getInt("Month");
				int resultDay = results.getInt("Day");
				String resultCountryName = results.getString("CountryName");
				String resultRegion = results.getString("Region");
				String resultProvState = results.getString("ProvState");
				String resultCity = results.getString("City");
				String resultLatitude = results.getString("Latitude");
				String resultLongitude = results.getString("Longitude");
				int resultSuicide = results.getInt("Suicide");
				int resultAttackTypeId = results.getInt("AttackTypeId");
				int resultTargetTypeId = results.getInt("TargetTypeId");
				String resultTarget = results.getString("Target");
				String resultNationality = results.getString("Nationality");
				int resultWeaponTypeId = results.getInt("WeaponTypeId");

				TerroristEvents terroristEvent = new TerroristEvents(
						resultEventId,
						resultYear,
						resultMonth,
						resultDay,
						resultCountryName,
						resultRegion,
						resultProvState,
						resultCity,
						resultLatitude,
						resultLongitude,
						resultSuicide,
						resultAttackTypeId,
						resultTargetTypeId,
						resultTarget,
						resultNationality,
						resultWeaponTypeId);
				terroristEvents.add(terroristEvent);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(selectStmt != null) {
				selectStmt.close();
			}
			if(results != null) {
				results.close();
			}
		}
		return terroristEvents;
	}
}
