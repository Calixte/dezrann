document.addEventListener("DOMContentLoaded", function(event) {
	var json = document.getElementById('jsonContainer').innerHTML;
	var records = JSON.parse(json);
	console.log(records);
	for (var i = 0; i < records.length; i++) {
		console.log(records);
	}
});