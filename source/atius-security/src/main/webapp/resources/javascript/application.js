
function at_login(args) {
	if (args.validationFailed) {
		xp_tip_atpos = 'top center';
		xp_tip_mypos = 'bottom left';
		xp_tip_style = 'ui-tooltip-dark';
		xp_addTip('login-form-id:login-user-id', 'Autenticação incorreta');
		xp_showTip('login-form-id:login-user-id');
	}
}
