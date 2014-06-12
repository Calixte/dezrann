document.addEventListener("DOMContentLoaded", function(event) {
	var json = document.getElementById('jsonContainer').innerHTML;
	var records = JSON.parse(json);
	for (var i = 0; i < records.length; i++) {
		records[i] = JSON.parse(records[i]);
	}
	var previousDate = records[0].date;
	var waitAndDo = function(i) {
		setTimeout(function() {
			renderAction(records[i]);
			previousDate = records[i].date;
			if (++i < records.length) {
				waitAndDo(i);
			} else {
				waitAndDo(0);
			}
		}, records[i].date - previousDate)
	};
	waitAndDo(0);
});