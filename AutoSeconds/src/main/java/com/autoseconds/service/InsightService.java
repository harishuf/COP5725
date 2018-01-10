package com.autoseconds.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.autoseconds.dao.DAOService;
import com.autoseconds.model.Insight;

@Service
public class InsightService {

	@Autowired
	private DAOService daoService;

	public InsightService() {
	}

	public List<Insight> getSoldCountForManufacturer() {
		return daoService.getSoldCountForManufacturer();
	}
	
	public List<Insight> getAccidentComparison() {
		return daoService.getAccidentComparison();
	}
	

	public List<Insight> getBestSellingManufacturers() {
		return daoService.getBestSellingManufacturers();
	}

	public List<Insight> getBestSellingModels() {
		return daoService.getBestSellingModels();
	}

	public List<Insight> getAveragePriceForMfrModel() {
		return daoService.getAveragePriceForMfrModel();
	}

	public List<Insight> getPreferredAttributeCombo() {
		return daoService.getPreferredAttrCombo();
	}

	public List<Insight> getCarsCountByState() {
		return daoService.getCarsCountByState();
	}

	public List<Insight> getUsersLeaseAvgPrice() {
		return daoService.getUsersLeaseAvgPrice();
	}
	
	public List<Insight> getModelsSoldInAWeek() {
		return daoService.getModelsSoldInAWeek();
	}
	
	public List<Insight> getPriceVarianceByMonthInAYear() {
		return daoService.getPriceVarianceByMonthInAYear();
	}

	/*
	 * 
	 * private static final String REGISTRATION_YEAR = "REGISTRATION_YEAR";
	 * private static final String NUMBER_OF_CARS = "NUMBER_OF_CARS"; private
	 * static final String MODEL_NAME = "MODEL_NAME";
	 * 
	 * public List<Insight> getCarsSold() { List<Insight> insights = new
	 * ArrayList<Insight>(); Connection connection = null; try {
	 * 
	 * // DataSource dataSource = connectionManager.getC(); connection =
	 * connectionManager.getConnection(); Statement statement =
	 * connection.createStatement(); String queryString =
	 * "SELECT REGISTRATION_YEAR, COUNT(*) AS NUMBER_OF_CARS FROM USED_CAR GROUP BY REGISTRATION_YEAR HAVING COUNT(*) > 1000 ORDER BY REGISTRATION_YEAR"
	 * ; ResultSet rs = statement.executeQuery(queryString);
	 * 
	 * while (rs.next()) {
	 * 
	 * Insight insight = new Insight();
	 * insight.setCount(Integer.valueOf(rs.getString(NUMBER_OF_CARS)));
	 * 
	 * String registrationYear = rs.getString(REGISTRATION_YEAR);
	 * insight.setRegistrationYear(Integer.valueOf(registrationYear));
	 * 
	 * // System.out.println(insight); insights.add(insight); } } catch
	 * (Exception e) { e.printStackTrace(); } finally { try {
	 * connectionManager.closeConnection(connection); } catch (SQLException e) {
	 * // TODO Auto-generated catch block e.printStackTrace(); } }
	 * 
	 * return insights; }
	 * 
	 * public List<Insight> getModelsCount() { List<Insight> insights = new
	 * ArrayList<Insight>(); Connection connection = null; try {
	 * 
	 * // DataSource dataSource = connectionManager.getC(); connection =
	 * connectionManager.getConnection(); Statement statement =
	 * connection.createStatement(); ResultSet rs = statement.executeQuery(
	 * "select MODEL_NAME, COUNT(*) AS NUMBER_OF_CARS FROM MODEL GROUP BY MODEL_NAME HAVING COUNT(*) > 25 ORDER BY MODEL_NAME"
	 * ); while (rs.next()) { Insight insight = new Insight();
	 * insight.setCount(Integer.valueOf(rs.getString(NUMBER_OF_CARS)));
	 * 
	 * String modelName = rs.getString(MODEL_NAME);
	 * insight.setModelName(modelName);
	 * 
	 * // System.out.println(insight); insights.add(insight); } } catch
	 * (Exception e) { e.printStackTrace(); } finally { try {
	 * connectionManager.closeConnection(connection); } catch (SQLException e) {
	 * // TODO Auto-generated catch block e.printStackTrace(); } }
	 * 
	 * return insights; }
	 */

}
