sap.ui.define([
	"sap/ui/core/ComponentContainer"
], function (ComponentContainer) {
	"use strict";

	new ComponentContainer({
		name:"Elearning",
		settings : {
			id : "Elearning"
		},
		async: true
	}).placeAt("content");
});