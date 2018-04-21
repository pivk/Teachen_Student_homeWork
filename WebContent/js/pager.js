var pages=0;
var page=1;
var perPage=20;
function calPage(new_page){
	if (pages > 0) {
		if (new_page > pages) {
			if (pages > 0) {
				page = pages;
			} else {
				page = 1;
			}
		} else if (new_page < 1)
			page = 1;
		else
			page = new_page;

	}
	$("#page").text(page);
}
function showPage(total){
	pages=Math.ceil(total/perPage);
	$("#pages").text(pages);
	$("#total").text(total);
}
