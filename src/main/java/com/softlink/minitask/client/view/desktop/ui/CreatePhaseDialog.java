package com.softlink.minitask.client.view.desktop;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.datepicker.client.DateBox;
import com.softlink.minitask.client.AppConstants;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.event.dom.client.ClickEvent;

public class CreatePhaseDialog extends DialogBox {

	private static Binder uiBinder = GWT.create(Binder.class);

	interface Binder extends UiBinder<Widget, CreatePhaseDialog> {
	}

	public interface Listener {

	}

	@UiField
	Label lbSubProject;
	@UiField
	Label lbStartDate;
	@UiField
	Label lbEndDate;
	@UiField
	Label btSave;
	@UiField
	Label lbOr;
	@UiField
	TextBox subProject;
	@UiField
	DateBox startDate;
	@UiField
	DateBox endDate;
	@UiField
	Label btCancel;
	private Listener listener;

	public CreatePhaseDialog(Listener listener) {
		setWidget(uiBinder.createAndBindUi(this));
		setGlassEnabled(true);
		show();
		center();
		this.listener = listener;
		InitForm();
	}

	private final AppConstants CONSTANTS = GWT.create(AppConstants.class);

	protected void InitForm() {
		lbEndDate.setText(CONSTANTS.ViewProjectEndDate());
		lbOr.setText(CONSTANTS.ZoomOutDescriptionLabelTextOr());
		lbStartDate.setText(CONSTANTS.ViewProjectStartDate());
		lbSubProject.setText(CONSTANTS.ViewProjectSub());
		btSave.setText(CONSTANTS.ButtonTextSave());
		btCancel.setText(CONSTANTS.ButtonTextCancel());
	}

	@UiHandler("btSave")
	void onBtSaveClick(ClickEvent event) {
	}

	@UiHandler("btCancel")
	void onBtCancelClick(ClickEvent event) {
		hide();
	}
}
