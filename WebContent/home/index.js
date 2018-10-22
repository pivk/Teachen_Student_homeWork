/** ************菜单******************* */
$(document).ready(function() {
	// 菜单
	$('#tree').treeview({
		data : myMenu,
		uiLibrary : 'bootstrap',
		levels : 5,
		onNodeSelected : function(event, data) {
			if (data.url != undefined && data.url != "#") {
				//location.href = data.url;
				openTab(data.id, data.text, data.url)
			}
		}
	});
});
function setAutoHeight(obj){
	$(obj).height($(document.body).height()-163);
	
}
function toggleMenu() {
	var menu = $("#admin-offcanvas");
	if (menu.is(":hidden")) {
		menu.show();
	} else {
		menu.hide();
	}
}

/** ****************标签页****************** */
var $tab = $('#mainTab');
var $nav = $tab.find('.am-tabs-nav');
var $bd = $tab.find('.am-tabs-bd');
var tabArray = ['index'];
function addTab(id, title, url) {
	var nav = '<li><span class="am-icon-close"></span>'
			+ '<a href="javascript: void(0)"> ' + title + '</a></li>';
	var content = '<div class="am-tab-panel">'
			+ '<iframe id="tab_'+id+'" src="' + url
			+ '" width="100%" height="100%" onload="Javascript:setAutoHeight(this)"></iframe>'
			+ '</div>';

	$nav.append(nav);
	$bd.append(content);
	$tab.tabs('refresh');
	tabArray.push(id);
}
function exist(id) {
	for (var i = 0; i < tabArray.length; i++) {
		if (tabArray[i] == id) {
			return i;
		}
	}
	return -1;
}
function closeTab(id) {
	var tabIndex = exist(id);
	if (tabIndex < 0) return;
	
	var $li = $("#l"+id);
	var index = $nav.children('li').index($li);
	$li.remove();
	$bd.find('.am-tab-panel').eq(index).remove();
	$tab.tabs('open', index > 0 ? index - 1 : index + 1);
	$tab.tabs('refresh');
	tabArray.splice(index,1);
}

function getTab(id){
	for(var i=0;i<tabArray.length;i++){
		if(tabArray[i]==id){
			return i;
		}
	}
	return -1;
}
function openTab(id, title, url) {
	var tabIndex = getTab(id);
	if (tabIndex>=0) {
		$tab.tabs('open', tabIndex);
	} else {
		addTab(id, title, url);
	}
	$tab.tabs('open', tabArray.length-1);
}

// 移除标签页
$nav.on('click', '.am-icon-close', function() {
	var $item = $(this).closest('li');
	var index = $nav.children('li').index($item);
	$item.remove();
	$bd.find('.am-tab-panel').eq(index).remove();
	$tab.tabs('open', index > 0 ? index - 1 : index + 1);
	$tab.tabs('refresh');
	tabArray.splice(index,1);
});
/** ****************功能****************** */
function changePassword(){
	$("#ddlUser").dropdown('close');
	openTab('password','修改密码','/home/password.action');
}
function changeSkin(skinName){
	$("#skin_css").attr("href","/skin/"+skinName+"/css/main.css");
	//设置子页面
	$(".am-tab-panel > iframe").each(function(index,item){
		var skin_css=$("#"+item.id).contents().find("#skin_css");
		skin_css.attr("href","/skin/"+skinName+"/css/main.css");
	});
}
function demo(){
	alert("调用成功");
}
