/**
 * xp:dialog-buttons JavaScript
 */

function xp_dialog_buttons_saving(_id, _dialogId) {
	xp_id(_id + ':default-bar').hide();
	xp_id(_id + ':saving-bar').show();
	xp_id(_dialogId + ' :input').attr('readonly', true);
}

function xp_dialog_buttons_complete(args, _id, _dialogId) {
	if (args.validationFailed) {
		xp_id(_dialogId + ' :input').removeAttr('readonly');
		xp_id(_id + ':saving-bar').hide();
		xp_id(_id + ':default-bar').show();
		xp_id(_id + ':error-bar').show();
		xp_tip_style = 'ui-tooltip-red';
		xp_tip_atpos = 'top center';
		xp_tip_mypos = 'bottom right';
		xp_addTip(_id + ':message-link', xp_id(_id + ':messages'));
		xp_showTip(_id + ':message-link');
	} else {
		xp_hideTip(_id + ":message-link");
		xp_id(_id + ':error-bar').hide();
		xp_id(_id + ':saving-bar').hide();
		xp_id(_id + ':success-bar').show();
	}
}

function xp_dialog_buttons_showTip(args, _id) {
	xp_id(_id + ':error-bar').show();
	xp_tip_atpos = 'top center';
	xp_tip_mypos = 'bottom right';
	xp_tip_style = 'ui-tooltip-green';
	if (args.validationFailed) {
		xp_tip_style = 'ui-tooltip-red';
	}
	xp_addTip(_id + ':message-link', xp_id(_id + ':messages'));
	xp_showTip(_id + ':message-link');
}
