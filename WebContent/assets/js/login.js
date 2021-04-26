$(document).on('ready', function() {

	$('#hide').on('click', function() {
		if ($('#loginForm\\:password').attr('type') == 'password') {
			$('#loginForm\\:password').attr('type', 'text');
			$('#hide').removeClass('fas fa-eye');
			$('#hide').addClass('fas fa-eye-slash');
		} else {
			$('#loginForm\\:password').attr('type', 'password');
			$('#hide').removeClass('fas fa-eye-slash');
			$('#hide').addClass('fas fa-eye');
		}
	});
	
	$('#loginForm\\:ingresar').on('click', function() {
		var user = $("#loginForm\\:usuario").val();
		var pass = $("#loginForm\\:password").val();
	
		if ((user == '' || user == undefined) && (pass == '' || pass == undefined)) {
			$("#loginForm\\:usuario").focus();
			$('#loginForm\\:userMessage').show();
			$('#loginForm\\:passMessage').show();
			return false;
		} 
		
		if (user == '' || user == undefined) {
			$("#loginForm\\:password").focus();
			$('#loginForm\\:passMessage').show();
			return false;
		}
		
		if (pass == '' || pass == undefined) {
			$("#loginForm\\:password").focus();
			$('#loginForm\\:passMessage').show();
			return false;
		}
	});
	
	$('#loginForm\\:usuario').on('input', function(){
		$('#loginForm\\:userMessage').hide();
	});
	
	$('#loginForm\\:password').on('input', function(){
		$('#loginForm\\:passMessage').hide();
	});
	
});
