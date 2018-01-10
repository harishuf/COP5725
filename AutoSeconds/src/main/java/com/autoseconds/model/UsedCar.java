package com.autoseconds.model;

public class UsedCar {
	private String modelId;
	private String registrationNumber;
	private String registrationMonth;
	private String registrationYear;
	private String price;
	private String condition;
	private String kilometersRun;
	private String color;
	private String accidentHistory;
	private String horsepower;

	private Model model;

	public String getModelId() {
		return modelId;
	}

	public void setModelId(String modelId) {
		this.modelId = modelId;
	}

	public Model getModel() {
		return model;
	}

	public void setModel(Model model) {
		this.model = model;
	}

	public String getRegistrationNumber() {
		return registrationNumber;
	}

	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}

	public String getRegistrationMonth() {
		return registrationMonth;
	}

	public void setRegistrationMonth(String registrationMonth) {
		this.registrationMonth = registrationMonth;
	}

	public String getRegistrationYear() {
		return registrationYear;
	}

	public void setRegistrationYear(String registrationYear) {
		this.registrationYear = registrationYear;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public String getKilometersRun() {
		return kilometersRun;
	}

	public void setKilometersRun(String kilometersRun) {
		this.kilometersRun = kilometersRun;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getAccidentHistory() {
		return accidentHistory;
	}

	public void setAccidentHistory(String accidentHistory) {
		this.accidentHistory = accidentHistory;
	}

	public String getHorsepower() {
		return horsepower;
	}

	public void setHorsepower(String horsepower) {
		this.horsepower = horsepower;
	}

}
