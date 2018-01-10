var searchResultsDiv = "#idSearchResults";
var listingsTemplate = "#listingsTemplate";
var compareCarDiv = "#compare";
var insights = "#insights";
var listingViewDiv = "#idViewListingInfo";
var createListingView = "#createListing";


function hideAll(){
	hideDivByTagName(searchResultsDiv);
	hideDivByTagName(insights);
	hideDivByTagName(listingViewDiv);
	hideDivByTagName(createListingView);
	hideDivByTagName(compareCarDiv);
}

function openComparePage() {
	hideDivByTagName(searchResultsDiv);
	hideDivByTagName(insights);
	hideDivByTagName(listingViewDiv);
	hideDivByTagName(createListingView);

	showDivByTagName(compareCarDiv);
}

function openInsightsPage() {
	hideDivByTagName(searchResultsDiv);
	hideDivByTagName(compareCarDiv);
	hideDivByTagName(listingViewDiv);
	hideDivByTagName(createListingView);

	showDivByTagName(insights);
}

function openSearchResultsPage() {
	hideDivByTagName(insights);
	hideDivByTagName(compareCarDiv);
	hideDivByTagName(listingViewDiv);
	hideDivByTagName(createListingView);

	showDivByTagName(searchResultsDiv);
}

function openListingView() {
	hideDivByTagName(insights);
	hideDivByTagName(compareCarDiv);
	hideDivByTagName(searchResultsDiv);
	hideDivByTagName(createListingView);

	showDivByTagName(listingViewDiv);

}

function openCreateListingPage() {
	hideDivByTagName(insights);
	hideDivByTagName(compareCarDiv);
	hideDivByTagName(searchResultsDiv);
	hideDivByTagName(listingViewDiv);

	showDivByTagName(createListingView);

}