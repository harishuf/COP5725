var createButton = "#idUserCreateListings";
var createListingForm = "#createListingForm";
var createListing = "#createListing";

var inputCreateListingTitle = "#inputCreateListingTitle";
var createListingManu = "#createListingManu";
var createListingManuUl = "#createListingManuUl";
var createListingModel = "#createListingModel";
var createListingModelUl = "#createListingModelUl";
var inputCreateListingPrice = "#inputCreateListingPrice";
var inputCreateListingKm = "#inputCreateListingKm";
var inputCreateListingCondition = "#inputCreateListingCondition";
var inputCreateListingColor = "#inputCreateListingColor";
var inputCreateListingRegNo = "#inputCreateListingRegNo";
var inputCreateListingHp = "#inputCreateListingHp";
var createListingMonthDrop = "#createListingMonthDrop";
var inputCreateListingYear = "#inputCreateListingYear";
var inputCreateListingAcc = "#inputCreateListingAcc";

var idBtnCreateListingCreate = "#idBtnCreateListingCreate";
var idBtnCreateListingBack = "#idBtnCreateListingBack";

var currModelID;

var listing = {
	title : "",
	price : "",
	dateCreated : "",
	registrationNumber : "",
	dateSold : "",
	status : "",
	emailId : "",
	usedCar : void 0
};

var usedCar = {
	modelId : "",
	registrationNumber : "",
	registrationMonth : "",
	registrationYear : "",
	condition : "",
	kilometersRun : "",
	color : "",
	accidentHistory : "",
	horsepower : ""
};




function attachComboChangeEvent(){
	$(".modelComboAnchor").click(function() {
		 var text =  $(this)[0].text;
		 var values = text.split(",");
		 var modelId = values[values.length-1];
		 
		 currModelID = modelId;
		 
		 $(createListingModel).text( $(this)[0].text);
	});
}

function detachComboChangeEvent(){
	$("#createListingModel").unbind("change");
}

function manufacturerChanged(id) {

	$.ajax({
		url : "/models",
		type : 'POST',
		contentType : 'application/json',
		data : id,
		success : function(result) {
			emptyDivByTagName(createListingModelUl);
			for (var i in result) {
				//setManufacturerComboValues(result);
				var txt = result[i].modelName + ","+ result[i].fuelType + ","+ result[i].vehicleType + ","+ result[i].gearboxType + ","+result[i].modelId;
				$(createListingModelUl).append("<li><a class =\"modelComboAnchor\"> "+ txt +"</a></li>");
	
			}
			attachComboChangeEvent();
		},
		failure : function() {
			console.log("Failed");
		}
	});
}



$(createButton).click(
		function() {
			showDivByTagName(createListing);
			$.ajax({
				url : "/manufacturers",
				type : 'GET',
				contentType : 'application/json',
				success : function(result) {
					//console.log(result);
					emptyDivByTagName(createListingManuUl);
					for ( var i in result) {
						$(createListingManuUl).append(
								"<li><a onClick=\"$(createListingManu).text('"
										+ result[i].manufacturerName
										+ "'); manufacturerChanged('"
										+ result[i].manufacturerId + "');\">"
										+ result[i].manufacturerName
										+ "</a></li>");
					}
				},
				failure : function() {
					console.log("Get Manufacturers Failed");
				}
			});
		});

function fetchLoggedInUser(){
	getLoggedInUser();
}

$(idBtnCreateListingCreate).click(function() {
	$(createListingForm).validate();
	if ($(createListingForm).valid() && currModelID) {

		listing.title = $(inputCreateListingTitle).val();
		listing.price = $(inputCreateListingPrice).val();
		listing.dateCreated = new Date().toLocaleString();
		listing.registrationNumber = $(inputCreateListingRegNo).val();
		listing.status = "unsold";
		listing.emailId = getLoggedInUser().emailId;

		usedCar.modelId = currModelID;
		usedCar.registrationNumber = $(inputCreateListingRegNo).val();
		usedCar.registrationMonth = $(createListingMonthDrop).text();
		usedCar.registrationYear = $(inputCreateListingYear).val();
		usedCar.condition = $(inputCreateListingCondition).val();
		usedCar.kilometersRun = $(inputCreateListingKm).val();
		usedCar.color = $(inputCreateListingColor).val();
		usedCar.accidentHistory = $(inputCreateListingAcc).val();
		usedCar.horsepower = $(inputCreateListingHp).val();
		
		listing.usedCar = usedCar;

		$.ajax({
			url : "/listing",
			type : 'POST',
			contentType : 'application/json',
			data : JSON.stringify(listing),
			success : function(result) {
				
				var loggedInUser = fetchLoggedInUser();
				if(loggedInUser){
					showUserLogin(loggedInUser);
				}
				
				if(result && result.id){
					buildListingView(result.id);
				}
				
				hideDivByTagName(createListing);
				
			},
			failure : function() {
				console.log("Create Listing Failed");
			}
		});

	} else {
		console.log("Form Invalid")
	}

});