package com.softlink.minitask.client.view.desktop;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.DataGrid;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.ListDataProvider;
import com.softlink.minitask.client.AppConstants;
import com.softlink.minitask.client.view.desktop.ui.CSSImageResource;
import com.softlink.minitask.client.view.desktop.ui.CssDataGridResources;
import com.softlink.minitask.client.view.desktop.ui.AllTasksTable;
import com.softlink.minitask.client.view.desktop.ui.MyTasksTable;
import com.softlink.minitask.shared.Task_Data;

public class TaskListView extends Composite {
	interface Binder extends UiBinder<Widget, TaskListView> {
	}

	private static Binder uiBinder = GWT.create(Binder.class);
	public final DataGrid.Resources dataGridCss = GWT
			.create(CssDataGridResources.class);

	public final CSSImageResource cSSImageResource = GWT
			.create(CSSImageResource.class);

	@UiField
	HorizontalPanel horMain;
	private AppConstants appConstants = GWT.create(AppConstants.class);
	private MyTasksTable gridTaskRecipients = new MyTasksTable(dataGridCss,
			cSSImageResource);

	public TaskListView() {
		initWidget(uiBinder.createAndBindUi(this));
		// InitViewMyTasks();
		InitViewAllTasks();

	}

	private void InitViewMyTasks() {
		horMain.setSpacing(10);
		InitViewRecipient();
		InitViewSender();
	}

	private List<Task_Data> autoGenerateTasks = Task_Data.autoGenerateTasks();
	private List<Task_Data> allTasks = new ArrayList<Task_Data>();

	private void InitViewAllTasks() {
		AllTasksTable tableAllTasks = new AllTasksTable(dataGridCss,
				cSSImageResource);
		DataGrid<Task_Data> allTasksDataGrid = tableAllTasks.InitTable();
		horMain.add(allTasksDataGrid);
		ListDataProvider<Task_Data> providerAllTasks = new ListDataProvider<Task_Data>();
		providerAllTasks.addDataDisplay(allTasksDataGrid);
		allTasks = providerAllTasks.getList();
		allTasks.addAll(autoGenerateTasks);

	}

	private List<Task_Data> recipients = new ArrayList<Task_Data>();
	private DataGrid<Task_Data> gridRecipient;

	private void InitViewRecipient() {
		VerticalPanel verRecipient = new VerticalPanel();
		verRecipient.setSize("100%", "100%");
		verRecipient.setSpacing(5);
		Label headerRecipient = new Label(
				appConstants.TaskListViewHeaderRecipient());
		headerRecipient.setStyleName("font-blackTitle");
		verRecipient.add(headerRecipient);
		gridRecipient = gridTaskRecipients.InitDataGrid();
		verRecipient.add(gridRecipient);
		horMain.add(verRecipient);
		ListDataProvider<Task_Data> providerRecipient = new ListDataProvider<Task_Data>();
		providerRecipient.addDataDisplay(gridRecipient);
		recipients = providerRecipient.getList();
		recipients.addAll(autoGenerateTasks.subList(0, 30));
	}

	private DataGrid<Task_Data> gridSender;
	private List<Task_Data> senders = new ArrayList<Task_Data>();

	private void InitViewSender() {
		VerticalPanel verSender = new VerticalPanel();
		verSender.setSpacing(5);
		verSender.setSize("100%", "100%");
		Label headerSender = new Label(appConstants.TaskListViewHeaderSender());
		headerSender.setStyleName("font-blackTitle");
		verSender.add(headerSender);
		gridSender = gridTaskRecipients.InitDataGrid();
		gridSender.setWidth("100%");
		verSender.add(gridSender);
		horMain.add(verSender);
		ListDataProvider<Task_Data> providerSender = new ListDataProvider<Task_Data>();
		providerSender.addDataDisplay(gridSender);
		senders = providerSender.getList();
		senders.addAll(autoGenerateTasks.subList(30, 100));
	}

}
