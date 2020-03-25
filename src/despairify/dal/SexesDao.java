package despairify.dal;

import despairify.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Data access object (DAO) class to interact with the underlying Sexes table in your MySQL
 * instance. This is used to store {@link Sexes} into your MySQL instance and retrieve 
 * {@link Sexes} from MySQL instance.
 */
public class SexesDao {
	protected ConnectionManager connectionManager;
	
	// Single pattern: instantiation is limited to one object.
	private static SexesDao instance = null;
	protected SexesDao() {
		connectionManager = new ConnectionManager();
	}
	public static SexesDao getInstance() {
		if(instance == null) {
			instance = new SexesDao();
		}
		return instance;
	}

	public Sexes create(Sexes sex) throws SQLException {
		String insertSex = "INSERT INTO Sexes(Sex) VALUES(?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();

			insertStmt = connection.prepareStatement(insertSex,
					Statement.RETURN_GENERATED_KEYS);

			insertStmt.setString(1, sex.getSex());
			insertStmt.executeUpdate();
			
			
			resultKey = insertStmt.getGeneratedKeys();
			int sexId = -1;
			if(resultKey.next()) {
				sexId = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			sex.setSexId(sexId);
			
			return sex;

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
	
	public Sexes updateSex(Sexes sex, String newSex) throws SQLException {
		String updateSex = "UPDATE Sexes SET Sex=? WHERE SexId=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateSex);
			
			updateStmt.setString(1, newSex);
			updateStmt.setInt(2, sex.getSexId());
			
			updateStmt.executeUpdate();
			
			sex.setSex(newSex);
			
			return sex;
			
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

	public Sexes delete(Sexes sex) throws SQLException {
		String deleteSex = "DELETE FROM Sexes WHERE SexId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteSex);
			
			deleteStmt.setInt(1, sex.getSexId());
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

	public Sexes getSexBySexId(int sexId) throws SQLException {
		String selectSex = "SELECT * FROM Sexes WHERE SexId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectSex);
			selectStmt.setInt(1, sexId);

			results = selectStmt.executeQuery();

			if(results.next()) {
				int resultSexId = results.getInt("SexId");
				String resultSex = results.getString("Sex");

				Sexes sex = new Sexes(resultSexId, resultSex);
				return sex;
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
	
	public Sexes getSexBySex(String sex) throws SQLException {
		String selectSex = "SELECT * FROM Sexes WHERE Sex=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectSex);
			selectStmt.setString(1, sex);

			results = selectStmt.executeQuery();

			if(results.next()) {
				int resultSexId = results.getInt("SexId");
				String resultSex = results.getString("Sex");

				Sexes resultSexes = new Sexes(resultSexId, resultSex);
				return resultSexes;
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
