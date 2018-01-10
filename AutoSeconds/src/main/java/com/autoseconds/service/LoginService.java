package com.autoseconds.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;

import org.springframework.stereotype.Service;

import com.autoseconds.db.ConnectionManager;
import com.autoseconds.exception.AutoSecondsAppException;
import com.autoseconds.model.UserDetail;

@Service
public class LoginService {

	private static final String EMAIL_ID = "EMAIL_ID";
	private static final String PASSWORD = "PASSWORD";
	private static final String FIRST_NAME = "FIRST_NAME";
	private static final String LAST_NAME = "LAST_NAME";
	private static final String CONTACT_NUMBER = "CONTACT_NUMBER";
	private static final String ADDRESS_LINE1 = "ADDRESS_LINE1";
	private static final String ADDRESS_LINE2 = "ADDRESS_LINE2";
	private static final String CITY = "CITY";
	private static final String ZIPCODE = "ZIPCODE";
	private static final String COUNTRY = "COUNTRY";
	private static final String STATE = "STATE";

	private ConnectionManager connectionManager;

	public LoginService() {
		connectionManager = ConnectionManager.getInstance();
	}

	public UserDetail login(final UserDetail userDetail) {
		Connection connection = null;
		UserDetail loggedInUser = null;
		try {

			// DataSource dataSource = connectionManager.getC();
			connection = connectionManager.getConnection();
			Statement statement = connection.createStatement();
			String queryString = "SELECT * FROM USER_DETAIL WHERE EMAIL_ID='" + userDetail.getEmailId() + "'";
			System.out.println(queryString);
			ResultSet rs = statement.executeQuery(queryString);

			while (loggedInUser == null && rs.next()) {
				if (rs.getString(PASSWORD).equals(userDetail.getPassword())) {
					loggedInUser = new UserDetail();
					loggedInUser.setEmailId(rs.getString(EMAIL_ID));
					loggedInUser.setFirstName(rs.getString(FIRST_NAME));
					loggedInUser.setLastName(rs.getString(LAST_NAME));
					loggedInUser.setContactNumber(rs.getString(CONTACT_NUMBER));
					loggedInUser.setAddressLine1(rs.getString(ADDRESS_LINE1));
					loggedInUser.setAddressLine2(rs.getString(ADDRESS_LINE2));
					loggedInUser.setCity(rs.getString(CITY));
					loggedInUser.setZipcode(rs.getString(ZIPCODE));
					loggedInUser.setCountry(rs.getString(COUNTRY));
					loggedInUser.setState(rs.getString(STATE));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				connectionManager.closeConnection(connection);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return loggedInUser;
	}
	
	public UserDetail register(final UserDetail userDetail) throws AutoSecondsAppException {
		Connection connection = null;
		UserDetail loggedInUser = null;
		Boolean userExists = false;
		try {

			// DataSource dataSource = connectionManager.getC();
			connection = connectionManager.getConnection();
			Statement statement = connection.createStatement();
			String queryString = "Insert into USER_DETAIL (EMAIL_ID,PASSWORD,FIRST_NAME,LAST_NAME,CONTACT_NUMBER,ADDRESS_LINE1,ADDRESS_LINE2,CITY,ZIPCODE,COUNTRY,STATE) values ('"
					+userDetail.getEmailId()+ "','"+userDetail.getPassword()+ "','"+userDetail.getFirstName()+"','"+userDetail.getLastName()+"','"+userDetail.getContactNumber()
					+"','"+userDetail.getAddressLine1()+ "','"+userDetail.getAddressLine2()+"','"+userDetail.getCity()
					+"','"+userDetail.getZipcode()+"','"+userDetail.getCountry()+"','"+userDetail.getState()+"')";
			System.out.println(queryString);
			int rs = statement.executeUpdate(queryString);
			if(rs==1) {
				loggedInUser = login(userDetail);
			}
			
		} catch (SQLException e) {
			if(e instanceof SQLIntegrityConstraintViolationException){
				throw new AutoSecondsAppException("User already exists", e.getLocalizedMessage());
			}
			
		}  finally {
			try {
				connectionManager.closeConnection(connection);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return loggedInUser;
	}
}
