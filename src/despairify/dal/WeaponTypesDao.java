package despairify.dal;

import despairify.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Data access object (DAO) class to interact with the underlying WeaponTypes table in your MySQL
 * instance. This is used to store {@link Companies} into your MySQL instance and retrieve 
 * {@link Companies} from MySQL instance.
 */
public class WeaponTypesDao {
	protected ConnectionManager connectionManager;
	
	// Single pattern: instantiation is limited to one object.
	private static WeaponTypesDao instance = null;
	protected WeaponTypesDao() {
		connectionManager = new ConnectionManager();
	}
	public static WeaponTypesDao getInstance() {
		if(instance == null) {
			instance = new WeaponTypesDao();
		}
		return instance;
	}

	public WeaponTypes create(WeaponTypes weaponType) throws SQLException {
		String insertWeaponType = "INSERT INTO WeaponTypes(WeaponType) VALUES(?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();

			insertStmt = connection.prepareStatement(insertWeaponType,
					Statement.RETURN_GENERATED_KEYS);

			insertStmt.setString(1, weaponType.getWeaponType());
			insertStmt.executeUpdate();
			
			
			resultKey = insertStmt.getGeneratedKeys();
			int weaponTypeId = -1;
			if(resultKey.next()) {
				weaponTypeId = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			weaponType.setWeaponTypeId(weaponTypeId);
			
			return weaponType;

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
	
	public WeaponTypes updateWeaponType(WeaponTypes weaponType, String newWeaponType) throws SQLException {
		String updateWeaponType = "UPDATE WeaponTypes SET WeaponType=? WHERE WeaponTypeId=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateWeaponType);
			
			updateStmt.setString(1, newWeaponType);
			updateStmt.setInt(2, weaponType.getWeaponTypeId());
			
			updateStmt.executeUpdate();
			
			weaponType.setWeaponType(newWeaponType);
			
			return weaponType;
			
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

	public WeaponTypes delete(WeaponTypes weaponType) throws SQLException {
		String deleteWeaponType = "DELETE FROM WeaponTypes WHERE WeaponTypeId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteWeaponType);
			
			deleteStmt.setInt(1, weaponType.getWeaponTypeId());
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

	public WeaponTypes getWeaponTypeByWeaponTypeId(int weaponTypeId) throws SQLException {
		String selectWeaponType = "SELECT * FROM WeaponTypes WHERE WeaponTypeId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectWeaponType);
			selectStmt.setInt(1, weaponTypeId);

			results = selectStmt.executeQuery();

			if(results.next()) {
				int resultWeaponTypeId = results.getInt("WeaponTypeId");
				String resultWeaponType = results.getString("WeaponType");

				WeaponTypes weaponType = new WeaponTypes(resultWeaponTypeId, resultWeaponType);
				return weaponType;
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
}
