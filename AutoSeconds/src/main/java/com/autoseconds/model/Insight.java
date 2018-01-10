package com.autoseconds.model;

public class Insight {

	private int count;
	private String name;
	private int registrationYear;
	private String modelName;
	private String manufacturerName;
	private String firstName;
	private String lastName;
	private String emailId;
	private String maxPrice;
	private String minPrice;
	private String avgPrice;

	private String priceWithAccidents;
	private String priceWithoutAccidents;

	public Insight() {

	}

	public String getPriceWithAccidents() {
		return priceWithAccidents;
	}

	public void setPriceWithAccidents(String priceWithAccidents) {
		this.priceWithAccidents = priceWithAccidents;
	}

	public String getPriceWithoutAccidents() {
		return priceWithoutAccidents;
	}

	public void setPriceWithoutAccidents(String priceWithoutAccidents) {
		this.priceWithoutAccidents = priceWithoutAccidents;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getMaxPrice() {
		return maxPrice;
	}

	public void setMaxPrice(String maxPrice) {
		this.maxPrice = maxPrice;
	}

	public String getMinPrice() {
		return minPrice;
	}

	public void setMinPrice(String minPrice) {
		this.minPrice = minPrice;
	}

	public String getAvgPrice() {
		return avgPrice;
	}

	public void setAvgPrice(String avgPrice) {
		this.avgPrice = avgPrice;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	public int getRegistrationYear() {
		return registrationYear;
	}

	public void setRegistrationYear(int registrationYear) {
		this.registrationYear = registrationYear;
	}

	public String getManufacturerName() {
		return manufacturerName;
	}

	public void setManufacturerName(String manufacturerName) {
		this.manufacturerName = manufacturerName;
	}

}
