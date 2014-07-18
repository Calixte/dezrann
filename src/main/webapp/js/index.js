document.addEventListener('DOMContentLoaded', function(){
	var links = document.querySelectorAll(".delete-link");
	Array.prototype.forEach.call(links, function(link, index){
		link.onclick = function(event){
			event.preventDefault();
			var recordingId = link.dataset.id;
			var request = new XMLHttpRequest();
			request.open('DELETE', '/dezrann/replay?id=' + recordingId, true);
			request.onload = function() {
				if (this.status >= 200 && this.status < 400){
					window.location = "/dezrann";
				}
			};
			request.send();
		}
	});
});