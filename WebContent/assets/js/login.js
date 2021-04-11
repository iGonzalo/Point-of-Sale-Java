function validateInfo() {
	var user = $("#usuario").val();
	var pass = $("#password").val();

	if (user == '' || user == undefined) {
		console.log("ingresa info");
		return false;
	}
}

function saludo() {
	alert("Hola");
}
