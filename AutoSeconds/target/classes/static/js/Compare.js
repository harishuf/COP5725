var leftCompareItem;
var rightCompareItem;

var compareViewTemplate = "#compareViewTemplate";
var compareDiv = "#compare";

var compareButton = "#idBtnCompare";
var listingName1 = "#listingName1";
var listingName2 = "#listingName2";
var compareYear1 = "#compareYear1";
var compareYear2 = "#compareYear2";
var comparePrice1 = "#comparePrice1";
var comparePrice2 = "#comparePrice2";
var compareCondition1 = "#compareCondition1";
var compareCondition2 = "#compareCondition2";
var compareKm1 = "#compareKm1";
var compareKm2 = "#compareKm2";
var compareColor1 = "#compareColor1";
var compareColor2 = "#compareColor2";
var compareHistory1 = "#compareHistory1";
var compareHistory2 = "#compareHistory2";
var compareHP1 = "#compareHP1";
var compareHP2 = "#compareHP2";
var compareManufacturer1 = "#compareManufacturer1";
var compareManufacturer2 = "#compareManufacturer2";
var compareModel1 = "#compareModel1";
var compareModel2 = "#compareModel2";

function buildCompareScreen() {
	// Integrate code to call comparision screen

	// Clear all table entries
	clearCompareTableFields();

	var leftListing;
	var rightListing;

	//Left Table
	$.ajax({
		url : "/listing",
		type : "get",
		data : {
			listingId : getLeftCompareItem()
		},
		success : function(result) {
			if (result) {
				leftListing = result;
				$.ajax({
					url : "/listing",
					type : "get",
					data : {
						listingId : getRightCompareItem()
					},
					success : function(result) {
						if (result) {
							rightLisiting = result;
							populateCompareTable(leftListing, rightLisiting);

							//Hide all other divs and show only the compare div - Utils.js
							openComparePage();
							
							//Reset the compare ids
							resetCompareItems();
						}
					},
					failure : function() {
						console.log("Failed");
					}
				});
			}

		},
		failure : function() {
			console.log("Failed");
		}
	});

}

function populateCompareTable(leftItem, rightItem) {
	var items = [];
	items.push({
		left : leftItem,
		right : rightItem
	});
	
	emptyDivByTagName(compareDiv);
	$(compareViewTemplate).tmpl(items).appendTo(compareDiv);

}

function setLeftCompareItem(leftId) {
	leftCompareItem = leftId;
}

function setRightCompareItem(rightId) {
	rightCompareItem = rightId;
}

function getLeftCompareItem() {
	return leftCompareItem;
}

function getRightCompareItem() {
	return rightCompareItem;
}

function checkToCompare() {
	if (leftCompareItem != undefined && rightCompareItem != undefined) {
		return true;
	} else {
		return false;
	}
}

function resetCompareItems() {
	leftCompareItem = undefined;
	rightCompareItem = undefined;
}

function clearCompareTableFields() {
	emptyDivByTagName(listingName1);
	emptyDivByTagName(listingName2);
	emptyDivByTagName(compareYear2);
	emptyDivByTagName(comparePrice1);
	emptyDivByTagName(comparePrice2);
	emptyDivByTagName(compareCondition1);
	emptyDivByTagName(compareCondition2);
	emptyDivByTagName(compareKm1);
	emptyDivByTagName(compareKm2);
	emptyDivByTagName(compareColor1);
	emptyDivByTagName(compareColor2);
	emptyDivByTagName(compareHistory1);
	emptyDivByTagName(compareHistory2);
	emptyDivByTagName(compareHP1);
	emptyDivByTagName(compareHP2);
	emptyDivByTagName(compareManufacturer1);
	emptyDivByTagName(compareManufacturer2);
	emptyDivByTagName(compareModel1);
	emptyDivByTagName(compareModel2);
}

