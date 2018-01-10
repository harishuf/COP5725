var insightsButtton = "#idBtnInsights";
var insightsDiv = "#insights";

var yearChart = "yearChart";
var modelChart = "modelChart";
var bestSellingMfr = "bestSellingMfr";
var bestSellingModel = "bestSellingModel";
var avgPriceMfrModel = "avgPriceMfrModel";
var carsCountByState = "carsCountByState";
var leastAveragePriceUsersTmpl = "#leastAveragePriceUsersTmpl";
var fastMovingModels = "fastMovingModels";
var priceVariance = "priceVariance";

var accidentComparison = "accidentComparison";

var canvasWidth = Math.floor($(window).width() / 1.75);
var canvasHeight = Math.floor($(window).height() / 2.25);

function createCanvas(id) {
	var canvas = document.createElement("canvas");
	canvas.id = id;
	canvas.setAttribute('width', canvasWidth);
	canvas.setAttribute('height', canvasHeight);

	return canvas;
}

function appendSeparator() {
	$(insightsDiv).append("<hr class=\"my-4\">");
}

function getRandomColor() {
	var letters = '0123456789ABCDEF';
	var color = '#';
	for (var i = 0; i < 6; i++) {
		color += letters[Math.floor(Math.random() * 16)];
	}
	return color;
}

$(insightsButtton).click(function() {
	// Clear the previous insights div
	emptyDivByTagName(insightsDiv);

	// Hide all other divs and show only the insights div - Utils.js
	openInsightsPage();

	// Prepare all the Charts
	// prepareYearChart();
	// appendSeparator();

	// prepareModelChart();
	// appendSeparator();

	
	 prepareChartForBestSellingMfr(); appendSeparator();
	 prepareChartForBestSellingModel(); appendSeparator();
	 prepareChartForAvgPriceMfrModel(); appendSeparator(); 
	 
	 prepareTableForLeastAvgPriceUsers();appendSeparator();
	 prepareChartForAccidentComparison();appendSeparator();
	 prepareChartForFastMovingModels();appendSeparator();
	 
	 prepareChartForCarCountByState(); appendSeparator();
	 
	 prepareChartForPriceVariance(); appendSeparator();
	 
});

function prepareChartForBestSellingMfr() {

	var mSoldChart = createCanvas(bestSellingMfr);
	$(insightsDiv).append(mSoldChart);

	var ctx = document.getElementById(bestSellingMfr);

	$.ajax({
		url : "/insights/manufacturer/bestseller",
		type : "get",
		success : function(result) {
			var data = [];
			var labels = [];
			var borderColor = [];
			var backgroundColor = [];
			var red, green, blue;

			if (result) {

				for (var i = 0; i < result.length; i++) {
					if (result[i] && result[i].count) {
						data[i] = result[i].count;
						labels[i] = result[i].manufacturerName;
					}
					red = Math.floor((Math.random() * 255) + 1);
					green = Math.floor((Math.random() * 255) + 1);
					blue = Math.floor((Math.random() * 255) + 1);

					backgroundColor[i] = 'rgba(' + red + ',' + green + ','
							+ blue + ', 0.2)';
					borderColor[i] = 'rgba(' + red + ',' + green + ',' + blue
							+ ', 0.2)';
				}
			}

			var chart = new Chart(ctx, {
				type : 'bar',
				data : {
					labels : labels,
					datasets : [ {
						data : data,
						backgroundColor : backgroundColor,
						borderColor : borderColor,
						borderWidth : 1
					} ]
				},
				options : {
					scales : {
						yAxes : [ {
							ticks : {
								beginAtZero : true
							}
						} ]
					},
					responsive : false,
					title : {
						display : true,
						text : "Best Selling Manufacturers"
					},
					legend: {
				        display: false
				    }
				}
			});

		},
		failure : function() {
			console.log("Failed");
		}
	});

}

function prepareChartForBestSellingModel() {

	var mSoldChart = createCanvas(bestSellingModel);
	$(insightsDiv).append(mSoldChart);

	var ctx = document.getElementById(bestSellingModel);

	$.ajax({
		url : "/insights/model/bestseller",
		type : "get",
		success : function(result) {
			var data = [];
			var labels = [];
			var borderColor = [];
			var backgroundColor = [];
			var red, green, blue;

			if (result) {

				for (var i = 0; i < result.length; i++) {
					if (result[i] && result[i].count) {
						data[i] = result[i].count;
						labels[i] = result[i].name;
					}
					red = Math.floor((Math.random() * 255) + 1);
					green = Math.floor((Math.random() * 255) + 1);
					blue = Math.floor((Math.random() * 255) + 1);

					backgroundColor[i] = 'rgba(' + red + ',' + green + ','
							+ blue + ', 0.2)';
					borderColor[i] = 'rgba(' + red + ',' + green + ',' + blue
							+ ', 0.2)';
				}
			}

			var chart = new Chart(ctx, {
				type : 'bar',
				data : {
					labels : labels,
					datasets : [ {
						label : 'Best Selling model',
						data : data,
						backgroundColor : backgroundColor,
						borderColor : borderColor,
						borderWidth : 1
					} ]
				},
				options : {
					scales : {
						yAxes : [ {
							ticks : {
								beginAtZero : true
							}
						} ]
					},
					responsive : false,
					title : {
						display : true,
						text : "Best Selling Models"
					},
					legend: {
				        display: false
				    }
				}
			});

		},
		failure : function() {
			console.log("Failed");
		}
	});

}

function prepareChartForAvgPriceMfrModel() {

	var mSoldChart = createCanvas(avgPriceMfrModel);
	$(insightsDiv).append(mSoldChart);

	var ctx = document.getElementById(avgPriceMfrModel);

	$.ajax({
		url : "/insights/avgprice/mfrmodel",
		type : "get",
		success : function(result) {
			var data = [];
			var labels = [];
			var borderColor = [];
			var backgroundColor = [];
			var red, green, blue;

			if (result) {

				for (var i = 0; i < result.length; i++) {
					if (result[i] && result[i].count) {
						data[i] = result[i].count;
						labels[i] = result[i].name;
					}
					red = Math.floor((Math.random() * 255) + 1);
					green = Math.floor((Math.random() * 255) + 1);
					blue = Math.floor((Math.random() * 255) + 1);

					backgroundColor[i] = 'rgba(' + red + ',' + green + ','
							+ blue + ', 0.2)';
					borderColor[i] = 'rgba(' + red + ',' + green + ',' + blue
							+ ', 0.2)';
				}
			}

			var chart = new Chart(ctx, {
				type : 'bar',
				data : {
					labels : labels,
					datasets : [ {
						label : '',
						data : data,
						backgroundColor : backgroundColor,
						borderColor : borderColor,
						borderWidth : 1
					} ]
				},
				options : {
					scales : {
						yAxes : [ {
							ticks : {
								beginAtZero : true
							}
						} ]
					},
					responsive : false,
					title : {
						display : true,
						text : "Average price for manufacturer/model combo"
					},
					legend: {
				        display: false
				    }
				}
			});

		},
		failure : function() {
			console.log("Failed");
		}
	});

}

function prepareChartForCarCountByState() {

	var mSoldChart = createCanvas(carsCountByState);
	$(insightsDiv).append(mSoldChart);
	mSoldChart.setAttribute('height', Math.floor($(window).height() / 2.5));
	var ctx = document.getElementById(carsCountByState);

	$.ajax({
		url : "/insights/cars/state",
		type : "get",
		success : function(result) {
			var data = [];
			var labels = [];
			var borderColor = [];
			var backgroundColor = [];
			var hoverBackgroundColor = [];
			var red, green, blue;

			if (result) {

				for (var i = 0; i < result.length; i++) {
					if (result[i] && result[i].count) {
						data[i] = result[i].count;
						labels[i] = result[i].name;
					}
					red = Math.floor((Math.random() * 255) + 1);
					green = Math.floor((Math.random() * 255) + 1);
					blue = Math.floor((Math.random() * 255) + 1);

					backgroundColor[i] = 'rgba(' + red + ',' + green + ','
							+ blue + ', 0.2)';
					borderColor[i] = 'rgba(' + red + ',' + green + ',' + blue
							+ ', 0.2)';
					hoverBackgroundColor[i] = getRandomColor();
				}
			}

			var chart = new Chart(ctx, {
				type : 'pie',
				data : {
					labels : labels,
					datasets : [ {
						label : 'Best Selling model',
						data : data,
						backgroundColor : backgroundColor,
						hoverBackgroundColor : hoverBackgroundColor
					} ]
				},
				options : {
					animation : {
						animateScale : true
					},
					title : {
						display : true,
						text : "Unsold cars per state"
					},
					legend: {
				        display: true
				    }
				}
			});

		},
		failure : function() {
			console.log("Failed");
		}
	});

}

function prepareTableForLeastAvgPriceUsers() {
	$
			.ajax({
				url : "/insights/users/leastprice",
				type : "get",
				success : function(result) {
					if (result) {
						var tDiv = $("<div id=\"leastAvgPrice\" class=\"container-fluid jumbotron table-responsive\"></div>");
						var aDiv = $("<b><h4>Users who have listings with least average price</h4></b>");
						var bDiv = $("<table class=\"table table-bordered table-hover\"><thead class=\"thead-inverse\"><tr><th>First Name</th><th>Last Name</th><th>Email Id</th><th>Max Priced Item</th><th>Min Priced Item</th><th>Average Price</th></tr></thead><tbody id = \"idBodyLeastAvgUsers\"></tbody></table>");

						$(tDiv).append(aDiv);
						$(aDiv).append(bDiv);

						$(insightsDiv).append(tDiv);
						for (var i = 0; i < result.length; i++) {
							var data = result[i];
							var tr = "<tr><td>" + data.firstName
									+ "</td><td>" + data.lastName
									+ "</td><td>" + data.emailId
									+ "</td><td>" + data.maxPrice
									+ "</td><td>" + data.minPrice
									+ "</td><td>" + data.avgPrice + "</td>";
							
							bDiv.append(tr);
						}
					}

				},
				failure : function() {
					console.log("Failed");
				}
			});

}

function prepareChartForAccidentComparison(){
	
	var mSoldChart = createCanvas(accidentComparison);
	$(insightsDiv).append(mSoldChart);
	
	var ctx = document.getElementById(accidentComparison);

	$.ajax({
		url : "/insights/accidentcomparison",
		type : "get",
		success : function(result) {
			var data1 = [];
			var data2 = [];
			var labels = [];
			
			var borderColor = [];
			var backgroundColor = [];
			
			var red, green, blue;

			if (result) {

				for (var i = 0; i < result.length; i++) {
					if (result[i] && result[i].manufacturerName) {
						data1[i] = result[i].priceWithAccidents;
						data2[i] = result[i].priceWithoutAccidents;
						
						labels[i] = result[i].manufacturerName;
					}
					red = Math.floor((Math.random() * 255) + 1);
					green = Math.floor((Math.random() * 255) + 1);
					blue = Math.floor((Math.random() * 255) + 1);

					backgroundColor[i] = 'rgba(' + red + ',' + green + ','
							+ blue + ', 0.2)';
					borderColor[i] = 'rgba(' + red + ',' + green + ',' + blue
							+ ', 0.2)';
					
				}
			}

			var chart = new Chart(ctx, {
				type : 'bar',
				data : {
					labels : labels,
					datasets : [ {
						label : '',
						data : data1,
						backgroundColor : backgroundColor
					}, {
					      label: '',
					      data: data2,
					      backgroundColor : backgroundColor
					    }]
				},
				options : {
					animation : {
						animateScale : true
					},
					title : {
						display : true,
						text : "Price comparison based on accident(Left - With accident, Right - Without accident)"
					},
					legend: {
				        display: false
				    }
				}
			});

		},
		failure : function() {
			console.log("Failed");
		}
	});
}

function prepareChartForFastMovingModels(){
	var mSoldChart = createCanvas(fastMovingModels);
	$(insightsDiv).append(mSoldChart);
	
	var ctx = document.getElementById(fastMovingModels);
	
	mSoldChart.setAttribute('height',  Math.floor($(window).height() / 2.5));

	$.ajax({
		url : "/insights/modelssoldinaweek",
		type : "get",
		success : function(result) {
			var data = [];
			var labels = [];
			
			var borderColor = [];
			var backgroundColor = [];
			
			var red, green, blue;

			if (result) {

				for (var i = 0; i < result.length; i++) {
					if (result[i] && result[i].name) {
						
						data[i] = result[i].avgPrice;
						labels[i] = result[i].name
						
					}
					red = Math.floor((Math.random() * 255) + 1);
					green = Math.floor((Math.random() * 255) + 1);
					blue = Math.floor((Math.random() * 255) + 1);

					backgroundColor[i] = 'rgba(' + red + ',' + green + ','
							+ blue + ', 0.2)';
					borderColor[i] = 'rgba(' + red + ',' + green + ',' + blue
							+ ', 0.2)';
					
				}
			}

			var chart = new Chart(ctx, {
				type : 'polarArea',
				data : {
					labels : labels,
					datasets : [{
						data : data,
						backgroundColor : backgroundColor
					}]
				},
				options : {
					animation : {
						animateScale : true
					},
					title : {
						display : true,
						text : "Fast moving models(Models sold within a week)"
					},
					legend: {
				        display: true
				    }
				}
			});

		},
		failure : function() {
			console.log("Failed");
		}
	});
}

function prepareChartForPriceVariance(){
	var mSoldChart = createCanvas(priceVariance);
	$(insightsDiv).append(mSoldChart);
	
	var ctx = document.getElementById(priceVariance);
	
	$.ajax({
		url : "/insights/pricevariance",
		type : "get",
		success : function(result) {
			var data = [];
			var labels = [];
			
			var borderColor = [];
			var backgroundColor = [];
			
			var red, green, blue;

			if (result) {

				for (var i = 0; i < result.length; i++) {
					if (result[i] && result[i].name) {
						
						data[i] = result[i].avgPrice;
						labels[i] = result[i].name;
						
					}
					red = Math.floor((Math.random() * 255) + 1);
					green = Math.floor((Math.random() * 255) + 1);
					blue = Math.floor((Math.random() * 255) + 1);

					backgroundColor[i] = 'rgba(' + red + ',' + green + ','
							+ blue + ', 0.2)';
					borderColor[i] = 'rgba(' + red + ',' + green + ',' + blue
							+ ', 0.2)';
					
				}
			}

			var chart = new Chart(ctx, {
				type : 'line',
				data : {
					labels : labels,
					datasets : [{
						data : data,
						backgroundColor : backgroundColor
					}]
				},
				options : {
					animation : {
						animateScale : true
					},
					title : {
						display : true,
						text : "Price Variance in 2015 for Volkswagen Golf"
					},
					legend: {
				        display: false
				    }
				}
			});

		},
		failure : function() {
			console.log("Failed");
		}
	});
}