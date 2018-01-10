var loginButtonNavBar = "#idLoginBtnNavBar";
var loginModal = "#idLoginModal";
var loginButtonModal = "#idBtnModalLogin";
var userInfoDiv = "#idUserInfo";
var userOptionsDiv = "#idUserOptions";

var inputUserName = "#inputUserName";
var inputPassword = "#inputPassword";

var logoutBtn = "#idLogout";

$(logoutBtn).click(function() {
	performLogout();
});

var loggedInUser;

function setLoggedInUser(user){
	loggedInUser = user;
}

function getLoggedInUser(){
	return loggedInUser;
}

var user = {
	emailId : void 0,
	password : void 0
};

$(loginButtonNavBar).click(function() {
	$(loginModal).modal();
});

function showUserLogin(user){
	emptyDivByTagName(userInfoDiv);
	$(userInfoDiv).append("Hi, " + user.firstName + " " + user.lastName + "!");
	
	$(loginModal).modal('hide');
	$(userOptionsDiv).css("display", "block");
	
	setLoggedInUser(user);
	hideDivByTagName("#idLoginBtnNavBar");
	hideDivByTagName("#idBtnSignUp");
	
}

function performLogout(){
	setLoggedInUser(null);
	
	showDivByTagName("#idLoginBtnNavBar");
	showDivByTagName("#idBtnSignUp");
	
	hideDivByTagName(userOptionsDiv);
	
	hideAll();
	
}

$(loginButtonModal).click(function() {

	user.emailId = document.getElementById("inputUserName").value ;// $(inputUserName).val();
	user.password = document.getElementById("inputPassword").value ;// $(inputPassword).val();

	$.ajax({
		url : "/login",
		type: 'POST',
        contentType: 'application/json',
		data : JSON.stringify(user),
		success : function(user) {
			if (user != void 0) {
				console.log("Login successful");
				showUserLogin(user);
			}
		},
		failure : function() {
			console.log("Failed");
		}
	});
});
