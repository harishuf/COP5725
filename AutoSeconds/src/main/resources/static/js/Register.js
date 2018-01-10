var registerButtonNavBar = "#idBtnSignUp";
var registerModal = "#idRegisterModal";
var registerForm = "#registerForm";

var idBtnModalRegister = "#idBtnModalRegister";

var inputUserName = "#inputRegisterUserName";
var inputPassword = "#inpuRegistertPassword";
var inputRegisterFName = "#inputRegisterFName";
var inputRegisterLName = "#inputRegisterLName";
var inputRegistertPhone = "#inputRegistertPhone";
var inputRegistertAddr1 = "#inputRegistertAddr1";
var inputRegistertAddr2 = "#inputRegistertAddr2";
var inputRegistertCity = "#inputRegistertCity";
var inputRegistertZip = "#inputRegistertZip";
var inputRegistertState = "#inputRegistertState";
var inputRegistertCountry = "#inputRegistertCountry";

$(registerButtonNavBar).click(function() {
	$(registerModal).modal();
});

var userOptionsDiv = "#idUserOptions";
var userInfoDiv = "#idUserInfo";

var user = {
	emailId : "",
	password : "",
	firstName : "",
	lastName : "",
	contactNumber : "",
	addressLine1 : "",
	addressLine2 : "",
	city : "",
	zipcode : "",
	state : "",
	country : ""
};

$(registerForm).submit(function() {

	user.emailId = $(inputUserName).val();
	user.password = $(inputPassword).val();
	user.firstName = $(inputRegisterFName).val();
	user.lastName = $(inputRegisterLName).val();
	user.contactNumber = $(inputRegistertPhone).val();
	user.addressLine1 = $(inputRegistertAddr1).val();
	user.addressLine2 = $(inputRegistertAddr2).val();
	user.city = $(inputRegistertCity).val();
	user.zipcode = $(inputRegistertZip).val();
	user.state = $(inputRegistertState).val();
	user.country = $(inputRegistertCountry).val();

	$(registerForm).validate();

	if ($(registerForm).valid()) {
		$.ajax({
			url : "/register",
			type : 'POST',
			contentType : 'application/json',
			data : JSON.stringify(user),
			success : function(result) {
				if (result && result.errorMessage) {
					// show error
					console.log("alert error message");
				} else {
					if (result != void 0) {
						console.log("Registration successful");
						showUserLogin(result);
					}
				}
			},
			failure : function() {
				console.log("Registration Failed");
			}
		});
	} else {
		console.log("Form Invalid");
	}
});