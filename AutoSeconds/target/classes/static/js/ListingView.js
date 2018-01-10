var listingViewDiv = "#idViewListingInfo";
var listingViewTemplate = "#listingsViewTemplate";

function buildListingView(listingId) {
	$.ajax({
		url : "/listing",
		type : "get",
		data : {
			listingId : listingId
		},
		success : function(listing) {
			populateListingView(listing);
		},
		failure : function() {
			console.log("Failed");
		}
	});
}

function populateListingView(listing) {
	var listings = [];
	listings.push(listing);

	emptyDivByTagName(listingViewDiv);
	$(listingViewTemplate).tmpl(listings).appendTo(listingViewDiv);

	openListingView();

}
