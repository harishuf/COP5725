var dataCountBtn = "#idBtnDataCount";
var dataCountModal = "#idDataCountModal";
var dataCountTxt = "#idTxtDataCount";

$(dataCountBtn).click(function() {
	
	$.ajax({
		url : "/datacount",
		type : "get",
		success : function(result) {
			$(dataCountTxt).text(result);
			$(dataCountModal).modal();
		},
		failure : function() {
			console.log("Failed");
		}
	});
});