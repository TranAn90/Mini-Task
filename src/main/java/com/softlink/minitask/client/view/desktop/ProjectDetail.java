package com.softlink.minitask.client.view.desktop;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.HeadingElement;
import com.google.gwt.dom.client.ParagraphElement;
import com.google.gwt.dom.client.TableCellElement;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.softlink.minitask.client.AppConstants;

public class ProjectDetail extends Composite {

	private static Binder uiBinder = GWT.create(Binder.class);

	interface Binder extends UiBinder<Widget, ProjectDetail> {
	}

	@UiField
	HeadingElement header;
	@UiField
	HeadingElement name;
	@UiField
	Label lblistMember;
	@UiField
	DivElement listMember;
	@UiField
	TableCellElement managerth;
	@UiField
	TableCellElement manager;
	@UiField
	TableCellElement startDateth;
	@UiField
	TableCellElement startDate;
	@UiField
	TableCellElement creatorth;
	@UiField
	TableCellElement creator;
	@UiField
	TableCellElement dueDateth;
	@UiField
	TableCellElement dueDate;
	@UiField
	Element descriptionth;
	@UiField
	ParagraphElement description;
	@UiField
	Label btEdit;
	@UiField
	Label btRemove;
	private final AppConstants CONSTANTS = GWT.create(AppConstants.class);
	private final String spilit = TaskDetail.split;

	public ProjectDetail() {
		initWidget(uiBinder.createAndBindUi(this));
		SetTextForm();
	}

	protected void SetTextForm() {
		header.setInnerHTML(CONSTANTS.ProjectDetailHeader());
		lblistMember.setText(CONSTANTS.ViewProjectListMember() + spilit);
		managerth.setInnerHTML(CONSTANTS.ViewProjectManager() + spilit);
		creatorth.setInnerHTML(CONSTANTS.ViewProjectCreator() + spilit);
		startDateth.setInnerHTML(CONSTANTS.ViewProjectStartDate() + spilit);
		dueDateth.setInnerHTML(CONSTANTS.ViewDueDate() + spilit);
		descriptionth.setInnerHTML(CONSTANTS.ViewDescription());
		btEdit.setText(CONSTANTS.ButtonTextEdit());
		btRemove.setText(CONSTANTS.ButtonTextRemove());
	}

	@UiHandler("btEdit")
	void onBtEditClick(ClickEvent event) {
	}

	@UiHandler("btRemove")
	void onBtRemoveClick(ClickEvent event) {
	}
}
