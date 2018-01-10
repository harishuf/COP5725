package com.autoseconds.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.autoseconds.dao.DAOService;
import com.autoseconds.exception.AutoSecondsAppException;
import com.autoseconds.model.ErrorMessage;
import com.autoseconds.model.Insight;
import com.autoseconds.model.Listing;
import com.autoseconds.model.Manufacturer;
import com.autoseconds.model.Model;
import com.autoseconds.model.UsedCar;
import com.autoseconds.model.UserDetail;
import com.autoseconds.service.InsightService;
import com.autoseconds.service.ListingService;
import com.autoseconds.service.LoginService;

@RestController
public class WebController {

	@Autowired
	private ListingService listingService;

	@Autowired
	private InsightService insightService;

	@Autowired
	private LoginService loginService;

	@Autowired
	private DAOService daoService;

	@RequestMapping("/search")
	public List<Listing> search(@RequestParam("searchKey") String searchKey) {
		return listingService.getListings(searchKey);
	}

	@RequestMapping("/listing")
	public Listing getListingInfo(@RequestParam("listingId") String listingId) {
		return listingService.getListingInfo(listingId);
	}

	@RequestMapping(value = "/login", headers = "Content-Type=application/json", method = RequestMethod.POST)
	public @ResponseBody UserDetail login(@RequestBody UserDetail userdetail) {
		return loginService.login(userdetail);
	}

	@RequestMapping("/model")
	public Model getModel(@RequestParam("modelId") String modelId) {
		return daoService.getModelById(modelId);
	}
	
	@RequestMapping(value = "/listing" , method = RequestMethod.POST)
	public Listing createListing(@RequestBody Listing listing) {
		return daoService.createListing(listing);
	}
	
	@RequestMapping(value = "/models", headers = "Content-Type=application/json", method = RequestMethod.POST)
	public List<Model> getModels(@RequestBody String manufacturerID) {
		return daoService.getModelList(manufacturerID);
	}

	@RequestMapping("/manufacturer")
	public Manufacturer getManufacturer(@RequestParam("manufacturerId") String manufacturerId) {
		return daoService.getManufacturerById(manufacturerId);
	}
	
	@RequestMapping("/manufacturers")
	public List<Manufacturer> getManufacturers() {
		return daoService.getManufacturersList();
	}

	@RequestMapping("/usedcar")
	public UsedCar getUsedCar(@RequestParam("registrationNumber") String registrationNumber) {
		return daoService.getUsedCarByRegistrationNumber(registrationNumber);
	}

	@RequestMapping("/insights/manufacturer/bestseller")
	public List<Insight> getMfrBestSeller() {
		return insightService.getSoldCountForManufacturer();
	}
	
	@RequestMapping("/insights/model/bestseller")
	public List<Insight> getModelBestSeller() {
		return insightService.getBestSellingModels();
	}
	
	@RequestMapping("/insights/avgprice/mfrmodel")
	public List<Insight> getAvgPriceMfrModel() {
		return insightService.getAveragePriceForMfrModel();
	}
	
	@RequestMapping("/insights/cars/state")
	public List<Insight> getCarsCountByState() {
		return insightService.getCarsCountByState();
	}
	
	@RequestMapping("/insights/users/leastprice")
	public List<Insight> getUsersLeaseAvgPrice() {
		return insightService.getUsersLeaseAvgPrice();
	}
	
	@RequestMapping("/insights/accidentcomparison")
	public List<Insight> getAccidentComparison() {
		return insightService.getAccidentComparison();
	}
	
	@RequestMapping("/insights/modelssoldinaweek")
	public List<Insight> getModelsSoldInAWeek() {
		return insightService.getModelsSoldInAWeek();
	}
	
	@RequestMapping("/insights/pricevariance")
	public List<Insight> getPriceVarianceByMonthInAYear() {
		return insightService.getPriceVarianceByMonthInAYear();
	}
	
	@RequestMapping("/datacount")
	public Integer getDataCount() {
		return daoService.getTotalTupleCount();
	}
	

	@RequestMapping(value = "/register", headers = "Content-Type=application/json", method = RequestMethod.POST)
	public @ResponseBody Object getUser(@RequestBody UserDetail userdetail) {

		try {
			UserDetail detail = loginService.register(userdetail);
			return detail;
		} catch (AutoSecondsAppException e) {
			ErrorMessage message = new ErrorMessage();
			message.setErrorMessage(e.getMessage());
			return message;
		}

	}

}
