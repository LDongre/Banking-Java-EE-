function validate() {
	if(document.getElementById("pass").value.length >= 8) {
		if(document.getElementById("pass").value == document.getElementById("confirmPass").value) {
	
		}
		else {
			alert("Both password fields should be same");
			return false;
		}
	}
	else {
		alert("Password should be of minimum 8 characters");
		return false;
	}
}

