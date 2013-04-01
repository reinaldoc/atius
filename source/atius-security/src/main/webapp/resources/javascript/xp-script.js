function xp_id(_id) {
	if (typeof (_id) != 'string')
		return _id;

	if (_id.indexOf('.') == 0 || _id.indexOf('#') != -1)
		return jQuery(_id);

	idList = _id.split(" ");
	id = idList.splice(0, 1)[0];
	return jQuery('#' + id.replace(/:/g, '\\:') + " " + idList.join());
}

/**
 * XP Basic Functions
 */

function xp_focus(_id) {
	xp_id(_id).focus();
}

function xp_value(_id, value) {
	xp_id(_id).val(value);
}

function xp_hide(obj) {
	if (obj !== undefined)
		obj.hide();
}

function xp_size(_id, _size) {
	xp_id(_id).height(_size);
}

function xp_wsize(_id, _size) {
	xp_id(_id).width(_size);
}

function xp_wHeight() {
	if (window.innerHeight == null)
		return document.documentElement.offsetHeight;
	return window.innerHeight + 3;
}

function xp_scroll_top(_id) {
	xp_id(_id).animate({
		scrollTop : 0
	}, 600);
}

function xp_scroll_bottom(_id) {
	xp_id(_id).animate({
		scrollTop : xp_id(_id).prop('scrollHeight')
	}, 600);
}

/**
 * XP DIALOG
 */

function xp_showDlg(_dlgVar, _dataVar) {
	if (typeof _dataVar != 'undefined')
		_dataVar.getPaginator().setPage(0);

	if (typeof _dlgVar != 'undefined') {
		if (typeof _dlgVar.cfg.dynamic == 'undefined')
			_dlgVar.initPosition();
		_dlgVar.show();
	}
}

/**
 * XP STYLE
 */
function xp_toggle_neighbors(_id, _styleClass) {
	jObj = xp_id(_id);
	jObj.parent().children().removeClass(_styleClass);
	jObj.addClass(_styleClass);
}

/**
 * XP POPUP
 */
function xp_popup(URL) {
	window
			.open(URL, '_blank',
					'width=800, height=600, top=100, left=100, scrollbars=yes, status=no, toolbar=no, location=no, directories=no, menubar=no, resizable=no, fullscreen=no');
}

function xp_report(reportPath, reportId) {
	openWindow("/atius-security/" + reportPath + "/report?id=" + reportId);
}

/**
 * XP TIP
 * 
 * xp_tip_style: ui-tooltip-cream; ui-tooltip-dark; ui-tooltip-green
 * ui-tooltip-light; ui-tooltip-red; ui-tooltip-blue
 * 
 */
var xp_tip_style = 'ui-tooltip-dark';
var xp_tip_atpos = 'top right';
var xp_tip_mypos = 'bottom left';

function xp_addTip(_id, msg) {
	obj = xp_id(_id);
	obj.qtip({
		content : {
			text : msg
		},
		hide : {
			event : 'mouseleave'
		},
		position : {
			my : xp_tip_mypos,
			at : xp_tip_atpos
		},
		style : {
			classes : xp_tip_style + ' ui-tooltip-shadow ui-tooltip-rounded'
		}
	});
}

function xp_showTip(_id) {
	xp_id(_id).qtip('api').show();
}

function __xp_hasTip(jObj) {
	if (jObj.qtip('api') == null || jObj.qtip('api') === undefined)
		return false;
	return true;
}

function xp_hasTip(_id) {
	if (__xp_hasTip(xp_id(_id)))
		return true;
	return false;
}

function xp_hideTip(_id) {
	xp_tip_style = 'ui-tooltip-dark';
	xp_tip_atpos = 'top right';
	xp_tip_mypos = 'bottom left';
	xp_tip = xp_id(_id);
	if (__xp_hasTip(xp_tip))
		xp_tip.qtip('api').hide();
}

/**
 * XP Browser data
 */
function xp_engine() {
	// From: http://msdn.microsoft.com/en-us/library/cc817574.aspx
	engine = 10;
	if (window.navigator.appName == "Microsoft Internet Explorer") {
		if (document.documentMode) // IE8
			engine = document.documentMode;
		else {
			engine = 5;
			if (document.compatMode) {
				if (document.compatMode == "CSS1Compat")
					engine = 7;
			}
		}
	}
	return engine;
}
