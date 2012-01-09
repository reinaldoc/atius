/**
 * Fuselage JavaScript
 */

function setLoginFocus(userId, passId) {
	field = document.getElementById(userId);
	if (field.value == "") {
		field.focus();
	} else {
		document.getElementById(passId).focus();
	}
}

function requireIE8() {
	engine = getEngine();
	if (engine > 7)
		return true;
	else {
		alert('É necessário utilizar um navegador mais recente,\nou desativar o modo compatibilidade de seu navegador.\n\nConsulte roteiro de uso do AELIS,\nou entre em contato com o ServiceDesk (4690).');
		return false;
	}
}

/**
 * Code from: http://msdn.microsoft.com/en-us/library/cc817574.aspx
 */
function getEngine() {
	engine = 10;
	if (window.navigator.appName == "Microsoft Internet Explorer") {
		// This is an IE browser. What mode is the engine in?
		if (document.documentMode) // IE8
			engine = document.documentMode;
		else {
			// IE 5-7
			engine = 5; // Assume quirks mode unless proven otherwise.
			if (document.compatMode) {
				if (document.compatMode == "CSS1Compat")
					engine = 7; // standards mode
			}
		}
	}
	return engine;
}
