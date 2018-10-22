$.ajaxSetup({
	cache : false
});
function getFrameId(){
	return window.frameElement && window.frameElement.id || '';
}