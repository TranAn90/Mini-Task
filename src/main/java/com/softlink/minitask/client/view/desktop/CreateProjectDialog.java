package com.softlink.minitask.client.view.desktop;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.datepicker.client.DateBox;
import com.google.gwt.user.client.ui.RichTextArea;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.user.client.ui.DisclosurePanel;
import com.softlink.minitask.client.AppConstants;
import com.softlink.minitask.client.view.desktop.ui.ZoomOutDescription;

public class CreateProjectDialog extends DialogBox {

	private static Binder uiBinder = GWT.create(Binder.class);

	interface Binder extends UiBinder<Widget, CreateProjectDialog> {
	}

	@UiField
	TextBox name;
	@UiField
	ListBox manager;
	@UiField
	DateBox dueDate;
	@UiField
	RichTextArea description;
	@UiField
	Image btZoomOut;
	@UiField
	DateBox startDate;
	@UiField
	DateBox endDate;
	@UiField
	Anchor btAddMember;
	@UiField
	FlowPanel tableMember;
	@UiField
	Label btSave;
	@UiField
	Label btSaveContinue;
	@UiField
	Image btReload;
	@UiField
	Image btClose;
	@UiField
	DisclosurePanel disclosurePanel;
	@UiField
	Label header;
	@UiField
	Label lbNameProject;
	@UiField
	Label lbManager;
	@UiField
	Label lbDescription;
	@UiField
	Label lbDueDate;
	@UiField
	Label lbStartDate;
	@UiField
	Label lbEndDate;

	public interface Listener {

	}

	private Listener listener;

	public CreateProjectDialog(Listener listener) {
		setWidget(uiBinder.createAndBindUi(this));
		this.listener = listener;
		setStyleName("frame_dialogBoxClean");
		SetTextForm();
		disclosurePanel.setOpen(false);
		setGlassEnabled(true);
		this.listener = listener;
		center();
		show();

	}

	@UiHandler("btZoomOut")
	void onBtZoomOutClick(ClickEvent event) {
		ZoomOutDescription zommOut = new ZoomOutDescription(
				description.getText(),
				CONSTANTS.CreateProjectDialogHeaderZoomOutDes(),
				new ZoomOutDescription.Listener() {

					@Override
					public void onClickanSave(String description) {
						// TODO Auto-generated method stub

					}
				});
	}

	@UiHandler("btAddMember")
	void onBtAddMemberClick(ClickEvent event) {
	}

	@UiHandler("btSave")
	void onBtSaveClick(ClickEvent event) {
	}

	@UiHandler("btSaveContinue")
	void onBtSaveContinueClick(ClickEvent event) {
	}

	@UiHandler("btReload")
	void onBtReloadClick(ClickEvent event) {
		ClearData();
	}

	private void ClearData() {
		name.setText(null);
		description.setText(null);
		dueDate.setValue(null);
		startDate.setValue(null);
		endDate.setValue(null);
		tableMember.clear();
	}

	@UiHandler("btClose")
	void onBtCloseClick(ClickEvent event) {
		hide();
	}

	private final AppConstants CONSTANTS = GWT.create(AppConstants.class);

	private void SetTextForm() {
		header.setText(CONSTANTS.CreateProjectDialogHeader());
		lbNameProject.setText(CONSTANTS.ViewProjectName());
		lbManager.setText(CONSTANTS.ViewProjectManager());
		lbDescription.setText(CONSTANTS.ViewDescription());
		lbDueDate.setText(CONSTANTS.ViewDueDate());
		lbStartDate.setText(CONSTANTS.ViewProjectStartDate());
		lbEndDate.setText(CONSTANTS.ViewProjectEndDate());
		btAddMember.setText(CONSTANTS.CreateProjectDialogButtonAddMember());
		disclosurePanel.getHeaderTextAccessor().setText(
				CONSTANTS.CreateProjectDialogShowMore());
		btSave.setText(CONSTANTS.ButtonTextSave());
		btSaveContinue.setText(CONSTANTS.ButtonTextSaveContinue());
		btReload.setTitle(CONSTANTS.ButtonTitleReload());
		btClose.setTitle(CONSTANTS.ButtonTitleClose());
		btZoomOut.setTitle(CONSTANTS.ButtonTitleZoomOutDes());
	}
}
