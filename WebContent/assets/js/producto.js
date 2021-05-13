$(document).ready(function() {
	var $rows = $('table tbody tr');
	$('#search').keyup(function() {
		var val = $.trim($(this).val()).replace(/ +/g, ' ').toLowerCase();

		$rows.show().filter(function() {
			var text = $(this).text().replace(/\s+/g, ' ').toLowerCase();
			return !~text.indexOf(val);
		}).hide();
	});

	$('#addModal').on('shown.bs.modal', function() {
		$('.add').focus();
	});
	
	$('#productosDT\\:editProduct').on('click', function() {
		$('#editModal').modal('show');
	});

	function openModal(index) {
		switch (index) {
		case 1:
			$('#editModal').modal('show');
			break;
		case 2:
			$('#deleteModal').modal('show');
			break;
		default:
			break;
		}
	}
});