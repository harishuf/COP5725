var insightsDiv = "#insights";


// USAGE : #idSearchResults
function emptyDivByTagName(divTagName){
	$(divTagName).empty();
}

//USAGE : #idSearchResults
function showDivByTagName(divTagName){
	$(divTagName).show();
}

//USAGE : #idSearchResults
function hideDivByTagName(divTagName){
	$(divTagName).hide();
}

//USAGE : #idSearchResults
function emptyInsightsDiv(){
	var insights = $(insightsDiv);
	var children = insights.children();
	
	for(var i = 0 ; i < children.length; i++){
		var canvas = children[i];
		var ctx=canvas.getContext("2d");
		ctx.clearRect(0,0,canvas.width,canvas.height);
	}
}

function attachViewInfoHandlers(){
	console.log('attaching handlers to view info buttons');
	$('button.idViewInfo').click(function() {
		var listingId = $(this).parent()[0].id;
		buildListingView(listingId);
	});
	
	$('button.leftCompare').click(function() {
		var listingId = $(this).parent()[0].id;
		setLeftCompareItem(listingId);
		
		$('button.leftCompare').prop("disabled", true);
		
		if(checkToCompare()){
			buildCompareScreen();
		}

	});
	
	$('button.rightCompare').click(function() {
		var listingId = $(this).parent()[0].id;
		setRightCompareItem(listingId);
		
		$('button.rightCompare').prop("disabled", true);
		
		if(checkToCompare()){
			detachViewInfoHandlers();
			buildCompareScreen();
		}
	});
	
	
}

function detachViewInfoHandlers(){
	console.log('detaching handlers from view info buttons');
	
	$( "button.idViewInfo").unbind("click").remove();
	$('button.leftCompare').unbind("click").remove();
	$('button.rightCompare').unbind("click").remove();
}

function enableCompareButtons(){
	$('button.leftCompare').click(function() {
		var listingId = $(this).parent()[0].id;
		setLeftCompareItem(listingId);
		
		if(checkToCompare()){
			buildCompareScreen();
		}
	});
}