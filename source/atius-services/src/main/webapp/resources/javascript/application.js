function at_login(args) {
	if (args !== undefined && args.validationFailed) {
		xp_tip_atpos = 'top center';
		xp_tip_mypos = 'bottom left';
		xp_tip_style = 'ui-tooltip-red';
		xp_addTip('atius-services-login-form-id:login-action-id', 'Autenticação incorreta');
		xp_showTip('atius-services-login-form-id:login-action-id');
	}
}

function at_login_hide_message() {
	xp_removeTip('atius-services-login-form-id:login-action-id');
}

function at_service_area_onload() {
	jQuery(function() {
		xp_value('atius-services-area-list-form-id:atius-services-area-input-search-id', '');
		xp_focus('atius-services-area-list-form-id:atius-services-area-input-search-id');
	});
}

function at_service_search_onload() {
	jQuery(function() {
		xp_focus('atius-services-group-list-form-id:atius-services-input-search-id');
	});
}