$(function() {
	$('#addCompanyForm').submit(function() {
		$(this).ajaxSubmit({
			target : "outputdiv", //配置此项，则会用返回的数据更新此div。
			beforeSubmit : beforeAddCompany,
			success : afterAddCompany
		});
		return false;
	});
});
function beforeAddCompany(formData, jqForm, options) {
	alert("formData=" + $.param(formData));
}

function afterAddCompany(responseText, statusText, xhr, $form) {
	alert("success=" + responseText);
}

function toEditMode(tableId) {
	var table = $("#" + tableId);
	var text = table.clone(true);
	// enable fields.
	$("[editable=true]", table).attr('disabled', false);
	$("input.dateField").datepicker({
		dateFormat : 'yy-mm-dd'
	});
	var editLink = $('#' + tableId + "Edit");
	var saveLink = $('#' + tableId + "Save");
	var cancelLink = $('#' + tableId + "Cancel");
	editLink.hide();
	saveLink.show();
	cancelLink.show();
	editLink.data('table', text);
}

/***************************************************/
/**                   由文本变成编辑模式                     **/
/***************************************************/
/**
 * 将一个表格变成编辑模式
 */
function toEditMode2(tableId, superLink) {
	var table = $("#" + tableId);
	var isEditMode = table.attr('isEditMode');
	var text = null;
	if(isEditMode != 'true') {
		text = table.clone(true);
	}
	var fields = table.find("span[name]");
	fields.each(function(i) {
		var name = $(this).attr('name');
		var type = $(this).attr('type');
		var value = $(this).val();
		var ele = null;
		if(type == 'select') {
			ele = $('<select type="select" name="' + name + '"/>');
			var options = $(this).attr('options').split(',');
			for(var i = 0; i < options.length; i++) {
				var arr = options[i].split('=');
				var isSelected = "";
				if( value = arr[0]) {
					isSelected = " selected='selected' ";
				}
				ele.append("<option " + isSelected + " value='" + arr[0] + "'>" + arr[1] + "</option>");
			}
		} else if(type == 'date') {
			ele = $('<input type="text" name="' + name + '"/>');
			ele.val(value);
		} else {
			ele = $('<input type="text" name="' + name + '"/>');
			ele.val(value);
		}
		$(this).replaceWith(ele);
	});
	table.attr('isEditMode', 'true');
	if(superLink) {
		var link = $('#' + superLink);
		link.data('table', text);
		link.html('Save');
		link.attr('href', 'javascript:postData("' + tableId + '");');
		var cancelLink = $('<a href="#">Cancel</a>');
		cancelLink.css('margin-left', '10px');
		link.after(cancelLink);
		cancelLink.click(function() {
			exitEditMode(tableId, superLink, cancelLink);
		});
	}
}

/**
 * 退出编辑模式
 */
function exitEditMode(tableId) {
	var editLink = $('#' + tableId + "Edit");
	var saveLink = $('#' + tableId + "Save");
	var cancelLink = $('#' + tableId + "Cancel");
	editLink.show();
	saveLink.hide();
	cancelLink.hide();
	var table = editLink.data('table');
	$('#' + tableId).replaceWith(table);
}

/**
 * 提交数据
 */
function postData(tableId) {
	var form = $("#" + tableId).parent("form");
	$(form[0]).ajaxSubmit({
		//target : "msgDiv", //配置此项，则会用返回的数据更新此div，并且不会执行success callback！。
		beforeSubmit : function(formData, jqForm, options) {
			return beforePost(tableId, formData, jqForm, options);
		},
		success : function(resp, statusText, xhr, $form) {
			afterPost(tableId, resp, statusText, xhr, $form);
		}
	});
}

function beforePost(tableId, formData, jqForm, options) {
	var validator = jqForm.data('validator');
	if(validator) {
		var result = validator.form();
		return result == true;
	}
	return true;
}

function afterPost(tableId, resp, statusText, xhr, $form) {
	if(resp.result) {
		var table = $("#" + tableId);
		$("[editable=true]", table).attr('disabled', true);
		var editLink = $("#" + tableId + "Edit");
		var saveLink = $("#" + tableId + "Save");
		var cancelLink = $("#" + tableId + "Cancel");
		editLink.show();
		saveLink.hide();
		cancelLink.hide();
		showComMsg('<font color="green">' + resp.msg + "</font>", tableId + "Msg");
	} else {
		showComMsg('<font color="red">' + resp.msg + "</font>", tableId + "Msg");
	}
}

function showComMsg(msg, elementId) {
	var ele = $("#" + elementId);
	ele.html(msg);
	ele.fadeIn("slow").delay(1500).fadeOut("slow");
}