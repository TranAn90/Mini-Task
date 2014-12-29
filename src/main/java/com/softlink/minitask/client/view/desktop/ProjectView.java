package com.softlink.minitask.client.view.desktop;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.HeadingElement;
import com.google.gwt.dom.client.ParagraphElement;
import com.google.gwt.dom.client.TableCellElement;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.Widget;
import com.softlink.minitask.client.AppConstants;
import com.softlink.minitask.client.view.ProjectViewInf;
import com.softlink.minitask.shared.Task_Project;

public class ProjectView extends Composite implements ProjectViewInf {

	private static Binder uiBinder = GWT.create(Binder.class);

	interface Binder extends UiBinder<Widget, ProjectView> {
	}

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
	@UiField Label btnBack;
	@UiField ScrollPanel sclContent;
	@UiField HTMLPanel htmlMenu;
	
	private final AppConstants CONSTANTS = GWT.create(AppConstants.class);
	private final String spilit = TaskView.split;
	
	private Presenter presenter;
	
	public static final int menuHeight = 46;

	public ProjectView() {
		initWidget(uiBinder.createAndBindUi(this));
		htmlMenu.setHeight(menuHeight + "px");
		sclContent.setHeight(Window.getClientHeight() - Container.headerHeight - menuHeight + "px");
		SetTextForm();
	}

	protected void SetTextForm() {
//		header.setInnerHTML(CONSTANTS.ProjectDetailHeader());
		lblistMember.setText(CONSTANTS.ViewProjectListMember() + spilit);
		managerth.setInnerHTML(CONSTANTS.ViewProjectManager() + spilit);
		creatorth.setInnerHTML(CONSTANTS.ViewProjectCreator() + spilit);
		startDateth.setInnerHTML(CONSTANTS.ViewProjectStartDate() + spilit);
		dueDateth.setInnerHTML(CONSTANTS.ViewDueDate() + spilit);
		descriptionth.setInnerHTML(CONSTANTS.ViewDescription());
//		btEdit.setText(CONSTANTS.ButtonTextEdit());
//		btRemove.setText(CONSTANTS.ButtonTextRemove());
	}

	@Override
	public void setProject(Task_Project project) {
		name.setInnerText(project.getName());
		creator.setInnerText("Tạo bởi " + project.getCreator());
		listMember.setInnerText(project.getListMember().toString());
		manager.setInnerText(project.getManager());
		creator.setInnerText(project.getCreator());
//		startDate.setInnerText(project.getStartDate().toString());
		dueDate.setInnerText(project.getDueDate().toString());
		description.setInnerText(project.getDescription());
	}

	@Override
	public void activityStart() {
		if(presenter != null)
			presenter.desktopLoadData();
	}

	@Override
	public void setPresenter(Presenter presenter) {
		this.presenter = presenter;
	}
}
