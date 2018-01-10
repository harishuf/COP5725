package com.autoseconds.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.autoseconds.dao.DAOService;
import com.autoseconds.model.Listing;

@Service
public class ListingService {

	@Autowired
	private DAOService daoService;

	public ListingService() {
	}

	public List<Listing> getListings(final String key) {
		return daoService.getMatchingListingsByKey(key);
	}

	public Listing getListingInfo(final String listingId) {
		return daoService.getListingById(listingId);
	}

}
