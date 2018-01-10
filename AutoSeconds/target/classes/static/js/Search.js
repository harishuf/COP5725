$("#idBtnSearch").click(function() {
	var searchKey = $("#idSearchText").val();
	console.log("Search key : " + searchKey);
	$.ajax({
		url : "/search",
		type : "get",
		data : {
			searchKey : searchKey
		},
		success : function(result) {
			populateSearchResults(result);
		},
		failure : function() {
			console.log("Failed");
		}
	});
});

function populateSearchResults(listings) {
	var searchResultsDiv = "#idSearchResults";
	var listingsTemplate = "#listingsTemplate";
	
	detachViewInfoHandlers();
	
	emptyDivByTagName(searchResultsDiv);
	$(listingsTemplate).tmpl(listings).appendTo(searchResultsDiv);
	
	attachViewInfoHandlers();
	
	openSearchResultsPage();
	
	//Set the compare buttons
	if(getLeftCompareItem()){
		$('button.leftCompare').prop("disabled", true);
	}
	if(getRightCompareItem()){
		$('button.rightCompare').prop("disabled", true);
	}
}