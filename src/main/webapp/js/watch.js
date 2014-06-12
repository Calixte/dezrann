document.addEventListener("DOMContentLoaded", function(event) {
	var toggleRecording = document.getElementById('toggleRecording');
	var startRecording = function(){
		toggleRecording.textContent = 'Stop recording';
		toggleRecording.onclick = stopRecording;
		ws.send("enrollan");
	};
	var stopRecording = function(){
		toggleRecording.textContent = 'Start recording';
		toggleRecording.onclick = startRecording;
		ws.send("dihanan");
	};
	toggleRecording.onclick = startRecording;
});