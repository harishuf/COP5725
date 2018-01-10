package com.autoseconds.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.stereotype.Service;

import com.autoseconds.db.ConnectionManager;
import com.autoseconds.model.Insight;
import com.autoseconds.model.Listing;
import com.autoseconds.model.Manufacturer;
import com.autoseconds.model.Model;
import com.autoseconds.model.UsedCar;

@Service
public class DAOService {

	private static final String TITLE = "TITLE";
	private static final String DATE_CREATED = "DATE_CREATED";
	private static final String STATUS = "STATUS";
	private static final String EMAIL_ID = "EMAIL_ID";

	private static final String REGISTRATION_NUMBER = "REGISTRATION_NUMBER";
	private static final String LISTING_ID = "LISTING_ID";

	private static final String MODEL_ID = "MODEL_ID";
	private static final String REGISTRATION_YEAR = "REGISTRATION_YEAR";
	private static final String REGISTRATION_MONTH = "REGISTRATION_MONTH";
	private static final String PRICE = "PRICE";
	private static final String CONDITION = "CONDITION";
	private static final String KILOMETERS_RUN = "KILOMETERS_RUN";
	private static final String COLOR = "COLOR";
	private static final String ACCIDENT_HISTORY = "ACCIDENT_HISTORY";
	private static final String HORSEPOWER = "HORSEPOWER";

	private static final String MANUFACTURER_ID = "MANUFACTURER_ID";
	private static final String MANUFACTURER_NAME = "MANUFACTURER_NAME";

	private static final String MODEL_NAME = "MODEL_NAME";
	private static final String FUEL_TYPE = "FUEL_TYPE";
	private static final String VEHICLE_TYPE = "VEHICLE_TYPE";
	private static final String GEARBOX_TYPE = "GEARBOX_TYPE";

	private static final String CNT = "CNT";
	private static final String AVG_PRICE = "AVG_PRICE";

	private static final String STATE = "STATE";

	private static final String FIRST_NAME = "FIRST_NAME";
	private static final String LAST_NAME = "LAST_NAME";
	private static final String MAX_PRICE = "MAX_PRICE";
	private static final String MIN_PRICE = "MIN_PRICE";

	private static final String PRICE_WITH_ACCIDENTS = "PRICE_WITH_ACCIDENTS";
	private static final String PRICE_WITHOUT_ACCIDENTS = "PRICE_WITHOUT_ACCIDENTS";

	private static final String AVG_DAYS_ON_WEBSITE = "AVG_DAYS_ON_WEBSITE";

	private ConnectionManager connectionManager;

	public DAOService() {
		connectionManager = ConnectionManager.getInstance();
	}

	public Listing createListing(Listing listing) {
		Manufacturer manufacturer = null;
		Connection connection = null;
		try {
			connection = connectionManager.getConnection();
			UsedCar car = listing.getUsedCar();
			String qString = "INSERT INTO USED_CAR (MODEL_ID,REGISTRATION_NUMBER,REGISTRATION_YEAR,CONDITION,KILOMETERS_RUN,COLOR,ACCIDENT_HISTORY,HORSEPOWER,REGISTRATION_MONTH) values ('"
					+ car.getModelId() + "','" + car.getRegistrationNumber() + "','" + car.getRegistrationYear() + "','"
					+ car.getCondition() + "','" + car.getKilometersRun() + "','" + car.getColor() + "','"
					+ car.getAccidentHistory() + "','" + car.getHorsepower() + "','" + car.getRegistrationMonth()
					+ "')";
			Statement preparedStatement = connection.prepareStatement(qString);

			int count = preparedStatement.executeUpdate(qString);
			System.out.println(count);

			SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yy HH:mm:ss");
			Calendar calendar = new GregorianCalendar();
			
			preparedStatement = connection.createStatement();
			ResultSet rs = preparedStatement.executeQuery("select listingid_sequence.nextval from dual");
			Integer listingId = null;
			while(rs.next()){
				listingId = Integer.valueOf(rs.getString("NEXTVAL"));
			}
			listingId = listingId + 300000;

			String queryString = "INSERT INTO LISTING (LISTING_ID, REGISTRATION_NUMBER,EMAIL_ID,STATUS,DATE_CREATED,DATE_SOLD,TITLE,PRICE) values (" + listingId + ", '"
					+ listing.getRegistrationNumber() + "','" + listing.getEmailId() + "','" + listing.getStatus()
					+ "','" + sdf.format(calendar.getTime()) + "','" + "" + "','" + listing.getTitle() + "' , " + listing.getPrice() + ")";

			preparedStatement = connection.prepareStatement(queryString);

			count = preparedStatement.executeUpdate(queryString);
			System.out.println(count);
			
			listing.setId(String.valueOf(listingId));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				connectionManager.closeConnection(connection);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return listing;
	}

	public List<Model> getModelList(String manufacturerID) {
		List<Model> modelList = new ArrayList<>();
		Connection connection = null;
		try {

			// DataSource dataSource = connectionManager.getC();
			connection = connectionManager.getConnection();
			Statement statement = connection.createStatement();
			String queryString = "select * FROM MODEL WHERE MANUFACTURER_ID = '" + manufacturerID + "'";
			System.out.println(queryString);
			ResultSet rs = statement.executeQuery(queryString);
			while (rs.next()) {

				Model model = new Model();
				model.setModelId(rs.getString(MODEL_ID));
				model.setManufacturerId(rs.getString(MANUFACTURER_ID));
				model.setModelName(rs.getString(MODEL_NAME));
				model.setFuelType(rs.getString(FUEL_TYPE));
				model.setVehicleType(rs.getString(VEHICLE_TYPE));
				model.setGearboxType(rs.getString(GEARBOX_TYPE));

				modelList.add(model);
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

		return modelList;
	}

	public List<Manufacturer> getManufacturersList() {
		List<Manufacturer> manufacturersList = new ArrayList<>();
		Connection connection = null;
		try {

			// DataSource dataSource = connectionManager.getC();
			connection = connectionManager.getConnection();
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("select *  FROM MANUFACTURER");
			while (rs.next()) {

				Manufacturer manufacturer = new Manufacturer();
				manufacturer.setManufacturerId(rs.getString(MANUFACTURER_ID));
				manufacturer.setManufacturerName(rs.getString(MANUFACTURER_NAME));

				manufacturersList.add(manufacturer);
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

		return manufacturersList;
	}

	public List<Insight> getPriceVarianceByMonthInAYear() {
		Connection connection = null;
		List<Insight> insights = new ArrayList<Insight>();
		try {
			connection = connectionManager.getConnection();
			Statement statement = connection.createStatement();
			String queryString = "SELECT * FROM MODEL_AVG_PRICE_BY_YEAR";
			ResultSet rs = statement.executeQuery(queryString);
			while (rs.next()) {
				Insight insight = new Insight();
				insight.setName(rs.getString(REGISTRATION_MONTH));
				insight.setAvgPrice(rs.getString(AVG_PRICE));

				insights.add(insight);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				connectionManager.closeConnection(connection);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return insights;
	}

	public List<Insight> getModelsSoldInAWeek() {
		Connection connection = null;
		List<Insight> insights = new ArrayList<Insight>();
		try {
			connection = connectionManager.getConnection();
			Statement statement = connection.createStatement();
			String queryString = "SELECT * FROM FAST_MOVING_MODELS";
			ResultSet rs = statement.executeQuery(queryString);
			while (rs.next()) {
				Insight insight = new Insight();
				insight.setName(rs.getString(MANUFACTURER_NAME) + " " + rs.getString(MODEL_NAME));
				insight.setAvgPrice(rs.getString(AVG_DAYS_ON_WEBSITE));
				insights.add(insight);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				connectionManager.closeConnection(connection);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return insights;
	}

	public List<Insight> getAccidentComparison() {
		Connection connection = null;
		List<Insight> insights = new ArrayList<Insight>();
		try {
			connection = connectionManager.getConnection();
			Statement statement = connection.createStatement();
			String queryString = "SELECT * FROM ACCIDENT_VS_NONACCIDENT";
			ResultSet rs = statement.executeQuery(queryString);
			while (rs.next()) {
				Insight insight = new Insight();

				insight.setManufacturerName(rs.getString(MANUFACTURER_NAME));
				insight.setPriceWithAccidents(rs.getString(PRICE_WITH_ACCIDENTS));
				insight.setPriceWithoutAccidents(rs.getString(PRICE_WITHOUT_ACCIDENTS));

				insights.add(insight);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				connectionManager.closeConnection(connection);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return insights;
	}

	public List<Insight> getUsersLeaseAvgPrice() {
		Connection connection = null;
		List<Insight> insights = new ArrayList<Insight>();
		try {
			connection = connectionManager.getConnection();
			Statement statement = connection.createStatement();
			String queryString = "SELECT * FROM AFFORDABLE_USERS";
			ResultSet rs = statement.executeQuery(queryString);
			while (rs.next()) {
				Insight insight = new Insight();

				insight.setFirstName(rs.getString(FIRST_NAME));
				insight.setLastName(rs.getString(LAST_NAME));
				insight.setEmailId(rs.getString(EMAIL_ID));
				insight.setMaxPrice(rs.getString(MAX_PRICE));
				insight.setMinPrice(rs.getString(MIN_PRICE));
				insight.setAvgPrice(rs.getString(AVG_PRICE));

				insights.add(insight);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				connectionManager.closeConnection(connection);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return insights;
	}

	public List<Insight> getCarsCountByState() {
		Connection connection = null;
		List<Insight> insights = new ArrayList<Insight>();
		try {
			connection = connectionManager.getConnection();
			Statement statement = connection.createStatement();
			String queryString = "SELECT * FROM UNSOLD_PER_STATE";
			ResultSet rs = statement.executeQuery(queryString);
			while (rs.next()) {
				Insight insight = new Insight();
				insight.setCount(Integer.valueOf(rs.getString(CNT)));
				insight.setName(rs.getString(STATE));

				insights.add(insight);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				connectionManager.closeConnection(connection);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return insights;
	}

	public List<Insight> getBestSellingManufacturers() {
		Connection connection = null;
		List<Insight> insights = new ArrayList<Insight>();

		try {
			// connection = DriverManager.getConnection(JDBC_ORACLE_CONN,
			// USERNAME, PASSWORD);
			// DataSource dataSource = connectionManager.getConnection();
			connection = connectionManager.getConnection();// dataSource.getConnection();

			Statement statement = connection.createStatement();
			String queryString = "SELECT * FROM BEST_SELLING_MANUFACTURERS";
			ResultSet rs = statement.executeQuery(queryString);
			while (rs.next()) {
				Insight insight = new Insight();
				insight.setCount(Integer.valueOf(rs.getString(CNT)));
				insight.setManufacturerName(rs.getString(MANUFACTURER_NAME));

				insights.add(insight);
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

		return null;
	}

	public List<Insight> getBestSellingModels() {
		Connection connection = null;
		List<Insight> insights = new ArrayList<Insight>();

		try {

			connection = connectionManager.getConnection();

			Statement statement = connection.createStatement();
			String queryString = "SELECT * FROM BEST_SELLING_MODELS WHERE ROWNUM <= 20";
			ResultSet rs = statement.executeQuery(queryString);
			while (rs.next()) {
				Insight insight = new Insight();
				insight.setCount(Integer.valueOf(rs.getString(CNT)));
				insight.setName(rs.getString(MANUFACTURER_NAME) + " " + rs.getString(MODEL_NAME));

				insights.add(insight);
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

		return insights;
	}

	public List<Insight> getAveragePriceForMfrModel() {
		Connection connection = null;
		List<Insight> insights = new ArrayList<Insight>();

		try {
			connection = connectionManager.getConnection();

			Statement statement = connection.createStatement();
			String queryString = "SELECT * FROM AVERAGE_PRICE_PER_MODEL WHERE ROWNUM <= 20";
			ResultSet rs = statement.executeQuery(queryString);
			while (rs.next()) {
				Insight insight = new Insight();
				insight.setCount(Integer.valueOf(rs.getString(AVG_PRICE)));
				insight.setName(rs.getString(MANUFACTURER_NAME) + " " + rs.getString(MODEL_NAME));

				insights.add(insight);
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
		return insights;
	}

	public List<Insight> getPreferredAttrCombo() {

		return null;
	}

	public Integer getTotalTupleCount() {
		Connection connection = null;
		int tupleCount = 0;
		try {
			connection = connectionManager.getConnection();

			StringBuffer sb = new StringBuffer();
			sb.append("SELECT SUM(C) FROM( ");
			sb.append(" SELECT COUNT(*) AS C FROM LISTING UNION ALL");
			sb.append(" SELECT COUNT(*) FROM MODEL UNION ALL");
			sb.append(" SELECT COUNT(*) FROM USER_DETAIL UNION ALL");
			sb.append(" SELECT COUNT(*) FROM MANUFACTURER UNION ALL");
			sb.append(" SELECT COUNT(*) FROM USED_CAR )");

			String queryString = sb.toString();
			Statement statement = connection.createStatement();

			ResultSet rs = statement.executeQuery(queryString);
			while (rs.next()) {
				tupleCount = rs.getInt("SUM(C)");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				connectionManager.closeConnection(connection);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return tupleCount;
	}

	public Listing getListingById(final String listingId) {
		Listing listing = null;
		Connection connection = null;
		try {

			connection = connectionManager.getConnection();

			Statement statement = connection.createStatement();
			StringBuilder sb = new StringBuilder();
			sb.append("SELECT *");
			sb.append(" FROM  LISTING L, USED_CAR U, MANUFACTURER MAN, MODEL M");
			sb.append(" WHERE L.LISTING_ID = " + listingId);
			sb.append(" AND L.REGISTRATION_NUMBER = U.REGISTRATION_NUMBER");
			sb.append(" AND U.MODEL_ID = M.MODEL_ID");
			sb.append(" AND M.MANUFACTURER_ID = MAN.MANUFACTURER_ID");

			String queryString = sb.toString();
			ResultSet rs = statement.executeQuery(queryString);
			while (rs.next()) {

				listing = new Listing();
				listing.setId(rs.getString(LISTING_ID));
				listing.setTitle(rs.getString(TITLE));
				listing.setRegistrationNumber(rs.getString(REGISTRATION_NUMBER));
				listing.setDateCreated(rs.getString(DATE_CREATED));
				listing.setStatus(rs.getString(STATUS));
				listing.setEmailId(rs.getString(EMAIL_ID));
				listing.setPrice(rs.getString(PRICE));

				UsedCar car = new UsedCar();
				car.setAccidentHistory(rs.getString(ACCIDENT_HISTORY));
				car.setColor(rs.getString(COLOR));
				car.setCondition(rs.getString(CONDITION));
				car.setHorsepower(rs.getString(HORSEPOWER));
				car.setKilometersRun(rs.getString(KILOMETERS_RUN));
				car.setModelId(rs.getString(MODEL_ID));
				car.setPrice(rs.getString(PRICE));
				car.setRegistrationMonth(rs.getString(REGISTRATION_MONTH));
				car.setRegistrationNumber(rs.getString(REGISTRATION_NUMBER));
				car.setRegistrationYear(rs.getString(REGISTRATION_YEAR));

				Model model = new Model();
				model.setFuelType(rs.getString(FUEL_TYPE));
				model.setGearboxType(rs.getString(GEARBOX_TYPE));
				model.setFuelType(rs.getString(MANUFACTURER_ID));
				model.setModelId(rs.getString(MODEL_ID));
				model.setModelName(rs.getString(MODEL_NAME));
				model.setVehicleType(rs.getString(VEHICLE_TYPE));

				Manufacturer manufacturer = new Manufacturer();
				manufacturer.setManufacturerId(rs.getString(MANUFACTURER_ID));
				manufacturer.setManufacturerName(rs.getString(MANUFACTURER_NAME));

				model.setManufacturer(manufacturer);
				car.setModel(model);
				listing.setUsedCar(car);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				connectionManager.closeConnection(connection);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return listing;

	}

	public List<Listing> getMatchingListingsByKey(final String key) {
		List<Listing> listings = new ArrayList<Listing>();
		Connection connection = null;
		try {
			// connection = DriverManager.getConnection(JDBC_ORACLE_CONN,
			// USERNAME, PASSWORD);

			// DataSource dataSource = connectionManager.getConnection();
			connection = connectionManager.getConnection();// dataSource.getConnection();

			Statement statement = connection.createStatement();
			String queryString = "SELECT * FROM LISTING WHERE TITLE LIKE '%" + key + "%'";
			ResultSet rs = statement.executeQuery(queryString);
			while (rs.next()) {

				Listing listing = new Listing();
				listing.setId(rs.getString(LISTING_ID));
				listing.setTitle(rs.getString(TITLE));
				listing.setRegistrationNumber(rs.getString(REGISTRATION_NUMBER));
				listing.setDateCreated(rs.getString(DATE_CREATED));
				listing.setStatus(rs.getString(STATUS));
				listing.setEmailId(rs.getString(EMAIL_ID));
				listing.setPrice(rs.getString(PRICE));

				listings.add(listing);
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
		System.out.println("Returning " + listings.size() + " items");
		return listings;
	}

	public List<Insight> getSoldCountForManufacturer() {
		List<Insight> insights = new ArrayList<Insight>();
		Connection connection = null;

		try {

			// DataSource dataSource = connectionManager.getC();
			connection = connectionManager.getConnection();
			Statement statement = connection.createStatement();
			StringBuilder sb = new StringBuilder();
			sb.append("select man.manufacturer_name, count(*) as cnt from manufacturer man ");
			sb.append("inner join model m on man.MANUFACTURER_ID = m.MANUFACTURER_ID ");
			sb.append("inner join used_car u on m.model_id = u.model_id ");
			sb.append("inner join listing l on u.registration_number=l.registration_number and status='sold' ");
			sb.append("group by man.manufacturer_name ");
			sb.append("order by count(*) desc");

			String queryString = sb.toString();

			ResultSet rs = statement.executeQuery(queryString);
			while (rs.next()) {
				Insight insight = new Insight();
				insight.setManufacturerName(rs.getString(MANUFACTURER_NAME));
				insight.setCount(Integer.valueOf(rs.getString(CNT)));

				insights.add(insight);
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

		return insights;
	}

	public UsedCar getUsedCarByRegistrationNumber(final String registrationNumber) {
		UsedCar usedCar = null;
		Connection connection = null;
		try {
			// DataSource dataSource = connectionManager.getC();
			connection = connectionManager.getConnection();
			Statement statement = connection.createStatement();
			String queryString = "select *  FROM USED_CAR WHERE REGISTRATION_NUMBER = '" + registrationNumber + "'";
			ResultSet rs = statement.executeQuery(queryString);
			while (rs.next()) {
				usedCar = new UsedCar();

				usedCar.setModelId(rs.getString(MODEL_ID));
				usedCar.setRegistrationNumber(rs.getString(REGISTRATION_NUMBER));
				usedCar.setRegistrationYear(rs.getString(REGISTRATION_YEAR));
				usedCar.setCondition(rs.getString(CONDITION));
				usedCar.setKilometersRun(rs.getString(KILOMETERS_RUN));
				usedCar.setColor(rs.getString(COLOR));
				usedCar.setAccidentHistory(rs.getString(ACCIDENT_HISTORY));
				usedCar.setHorsepower(rs.getString(HORSEPOWER));
				usedCar.setRegistrationMonth(rs.getString(REGISTRATION_MONTH));
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

		return usedCar;
	}

	public Model getModelById(String modelID) {
		Model model = new Model();
		Connection connection = null;
		try {

			// DataSource dataSource = connectionManager.getC();
			connection = connectionManager.getConnection();
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("SELECT * FROM MODEL WHERE MODEL_ID = '" + modelID + "'");
			while (rs.next()) {
				model.setModelId(rs.getString(MODEL_ID));
				model.setManufacturerId(rs.getString(MANUFACTURER_ID));
				model.setModelName(rs.getString(MODEL_NAME));
				model.setFuelType(rs.getString(FUEL_TYPE));
				model.setVehicleType(rs.getString(VEHICLE_TYPE));
				model.setGearboxType(rs.getString(GEARBOX_TYPE));
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

		return model;
	}

	public Manufacturer getManufacturerById(String manufacturerId) {
		Manufacturer manufacturer = null;
		Connection connection = null;
		try {

			// DataSource dataSource = connectionManager.getC();
			connection = connectionManager.getConnection();
			Statement statement = connection.createStatement();
			String queryString = "SELECT *  FROM MANUFACTURER WHERE MANUFACTURER_ID = '" + manufacturerId + "'";
			ResultSet rs = statement.executeQuery(queryString);
			while (rs.next()) {
				manufacturer = new Manufacturer();

				manufacturer.setManufacturerId(rs.getString(MANUFACTURER_ID));
				manufacturer.setManufacturerName(rs.getString(MANUFACTURER_NAME));
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
		return manufacturer;
	}
}
