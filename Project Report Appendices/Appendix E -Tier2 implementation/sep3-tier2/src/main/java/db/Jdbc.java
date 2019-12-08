package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;

public class Jdbc {

	private Connection connection = null;
	private final String user = "postgres";
	private final String password = "12345678";
	private final String database = "postgres";
	private final String driver = "org.postgresql.Driver";
	private final String url = "jdbc:postgresql://localhost:5432/";

	public Jdbc() {
		try {
			Class.forName(driver);
			connection = DriverManager.getConnection(url + database, user, password);
			connection.setSchema("justwork");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	public ArrayList<Object[]> query(String sql, Object... statementElements) throws SQLException {
		openDatabase();
		PreparedStatement statement = null;
		ArrayList<Object[]> list = null;
		ResultSet resultSet = null;
		if (sql != null && statement == null) {
			statement = connection.prepareStatement(sql);
			if (statementElements != null) {
				for (int i = 0; i < statementElements.length; i++)
					statement.setObject(i + 1, statementElements[i]);
			}
		}
		resultSet = statement.executeQuery();
		list = new ArrayList<Object[]>();
		while (resultSet.next()) {
			Object[] row = new Object[resultSet.getMetaData().getColumnCount()];
			for (int i = 0; i < row.length; i++) {
				row[i] = resultSet.getObject(i + 1);
			}
			list.add(row);
		}
		if (resultSet != null)
			resultSet.close();
		if (statement != null)
			statement.close();
		closeDatabase();
		return list;
	}
	public String getSchema() throws SQLException {
		return connection.getSchema();
	}

	private void openDatabase() throws SQLException {
		connection = DriverManager.getConnection(url, user, password);
	}

	private void closeDatabase() throws SQLException {
		connection.close();
	}

	public int update(String sql, Object... statementElements) throws SQLException {
		openDatabase();
		PreparedStatement statement = connection.prepareStatement(sql);
		if (statementElements != null) {
			for (int i = 0; i < statementElements.length; i++)
				statement.setObject(i + 1, statementElements[i]);
		}

		int result = statement.executeUpdate();

		closeDatabase();
		return result;
	}
	

}
