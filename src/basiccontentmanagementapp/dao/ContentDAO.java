package basiccontentmanagementapp.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import basiccontentmanagementapp.model.Content;

public class ContentDAO {
	
	private String jdbcURL = "jdbc:mysql://localhost/bccmapp";//?useSSL=false";
	private String jdbcUsername = "root";
	private String jdbcPassword = "12345678";

	private static final String INSERT_CONTENT_SQL = "INSERT INTO content (title, content, created) VALUES (?, ?, ?);";
	private static final String SELECT_CONTENT_BY_ID = "SELECT id, title, content, created from content WHERE id=?";
	private static final String SELECT_ALL_CONTENT = "SELECT * from content";
	private static final String DELETE_CONTENT_SQL = "DELETE from content WHERE id = ?";
	private static final String UPDATE_CONTENT_SQL = "UPDATE content SET title = ?, content = ?, created = ? WHERE id = ?";

	protected Connection getConnection() {
		Connection connection = null;		
		try {
			Class.forName("com.mysql.jdbc.Driver"); // ONLY NEEDED FOR TOMCAT
			connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return connection;
	}
	
	public String dateTime() {

		java.util.Date dt = new java.util.Date();
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss");

        String currentTime = sdf.format(dt);
        
        return currentTime;
	}
	
	//Create user
	public void insertContent(Content content) {
		try (Connection connection = getConnection();
		PreparedStatement preparedStatment = connection.prepareStatement(INSERT_CONTENT_SQL)) {
			
			String currentTime = dateTime();
			Timestamp TimeStamp = new Timestamp(0);
			
			preparedStatment.setString(1, content.getTitle() );
			preparedStatment.setString(2, content.getContent() );
			preparedStatment.setTimestamp(3, TimeStamp.valueOf(currentTime));
			preparedStatment.executeUpdate();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	//update content
	public boolean updateContent(Content content) throws SQLException {
		boolean rowUpdated;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_CONTENT_SQL);) {
			
			String currentTime = dateTime();
			Timestamp TimeStamp = new Timestamp(0);
			
			statement.setString(1, content.getTitle());
			statement.setString(2, content.getContent());
			statement.setTimestamp(3, TimeStamp.valueOf(currentTime));
			statement.setInt(4, content.getId());
			
			rowUpdated = statement.executeUpdate() > 0;
		}
		return rowUpdated;
	}
	
	
	//select content by id
	public Content selectContent(int id) {
		
		Content content = null;
		
		try (Connection connection = getConnection();
				PreparedStatement preparedStatment = connection.prepareStatement(SELECT_CONTENT_BY_ID);) {
			preparedStatment.setInt(1, id);
			System.out.println(preparedStatment);
			
			ResultSet rs = preparedStatment.executeQuery();
			
			while(rs.next()) {
				String title = rs.getString("title");
				String contents = rs.getString("content");
				String created = rs.getString("created");
				content = new Content(id, title, contents, created);
			} 
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return content;
	}

	//select all users

	public List<Content> selectAllUsers() {
		
		List<Content> content = new ArrayList<>(); 
		
		try (Connection connection = getConnection();
				PreparedStatement preparedStatment = connection.prepareStatement(SELECT_ALL_CONTENT);) {
			System.out.println(preparedStatment);
			
			ResultSet rs = preparedStatment.executeQuery();
			
			while(rs.next()) {
				int id = rs.getInt("id");
				String title = rs.getString("title");
				String contents = rs.getString("content");
				String created = rs.getString("created");
				content.add(new Content(id, title, contents, created));
			} 
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return content;
	}
	
	//delete user
	public boolean deleteContent(int id) throws SQLException {
		boolean rowDeleted;
		
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_CONTENT_SQL)) {
			statement.setInt(1, id);
			rowDeleted = statement.executeUpdate() > 0;
		}
		
		return rowDeleted;
	}
}
