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
