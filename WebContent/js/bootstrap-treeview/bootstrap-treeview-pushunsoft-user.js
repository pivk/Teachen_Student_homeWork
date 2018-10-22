var nodeCheckedSilent = false;
function nodeChecked(event, node) {
	if (nodeCheckedSilent) {
		return;
	}
	nodeCheckedSilent = true;
	checkAllParent(node);
	if(node.flag){
		var row = "<div id='"+node.id+"'>"+node.unitName+"-"+node.text+"<input name='"+node.text+"'class='"+node.unitName+"' type='hidden'value='"+node.id+"'></div>";
		$("#list").append(row);
	}
	checkAllSon(node);
	nodeCheckedSilent = false;
}

var nodeUncheckedSilent = false;
function nodeUnchecked(event, node) {
	if (nodeUncheckedSilent)
		return;
	nodeUncheckedSilent = true;
	uncheckAllParent(node);
	$("#"+node.id).remove();
	uncheckAllSon(node);
	nodeUncheckedSilent = false;
}

// 选中全部父节点
function checkAllParent(node) {
	$('#tree').treeview('checkNode', node.nodeId, {
		silent : true
	});
	var parentNode = $('#tree').treeview('getParent', node.nodeId);
	if (!("nodeId" in parentNode)) {
		return;
	} else {
		checkAllParent(parentNode);
	}
}
// 取消全部父节点
function uncheckAllParent(node) {
	$('#tree').treeview('uncheckNode', node.nodeId, {
		silent : true
	});
	var siblings = $('#tree').treeview('getSiblings', node.nodeId);
	var parentNode = $('#tree').treeview('getParent', node.nodeId);
	if (!("nodeId" in parentNode)) {
		return;
	}
	var isAllUnchecked = true; // 是否全部没选中
	for ( var i in siblings) {
		if (siblings[i].state.checked) {
			isAllUnchecked = false;
			break;
		}
	}
	if (isAllUnchecked) {
		uncheckAllParent(parentNode);
	}
}

// 级联选中所有子节点
function checkAllSon(node) {
	$('#tree').treeview('checkNode', node.nodeId, {
		silent : true
	});
	if (node.nodes != null && node.nodes.length > 0) {
		for ( var i in node.nodes) {
			if(node.nodes[i].flag){
				var row = "<div id='"+node.nodes[i].id+"'>"+node.nodes[i].unitName+"-"+node.nodes[i].text+
						  "<input name='"+node.nodes[i].text+"' class='"+node.nodes[i].unitName+"' type='hidden' value='"+node.nodes[i].id+"'></div>";
				$("#list").append(row);
			}
			checkAllSon(node.nodes[i]);
		}
	}
}
// 级联取消所有子节点
function uncheckAllSon(node) {
	$('#tree').treeview('uncheckNode', node.nodeId, {
		silent : true
	});
	if (node.nodes != null && node.nodes.length > 0) {
		for ( var i in node.nodes) {
			$("#"+node.nodes[i].id).remove();
			uncheckAllSon(node.nodes[i]);
		}
	}
}
function checkNodeById(id) {
	for (var i = 0; i < $('#tree').treeview('getCount'); i++) {
		var node = $('#tree').treeview('getNode', i);
		if (node.id == id) {
			nodeCheckedSilent = true;
			$('#tree').treeview('checkNode', i, {
				silent : true
			});
			nodeCheckedSilent = false;
			break;
		}
	}
}