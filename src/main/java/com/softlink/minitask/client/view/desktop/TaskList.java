package com.softlink.minitask.client.view.desktop;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.cellview.client.DataGrid;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.ListDataProvider;
import com.softlink.minitask.client.AppConstants;
import com.softlink.minitask.client.AppController.Storage;
import com.softlink.minitask.client.view.TaskListInf;
import com.softlink.minitask.client.view.desktop.ui.AllTasksTable;
import com.softlink.minitask.client.view.desktop.ui.CSSImageResource;
import com.softlink.minitask.client.view.desktop.ui.CreateTaskDialog;
import com.softlink.minitask.client.view.desktop.ui.CssDataGridResources;
import com.softlink.minitask.client.view.desktop.ui.MyTasksTable;
import com.softlink.minitask.shared.Task_Data;

public class TaskList extends Composite implements TaskListInf{
	interface Binder extends UiBinder<Widget, TaskList> {
	}

	private static Binder uiBinder = GWT.create(Binder.class);
	public final DataGrid.Resources dataGridCss = GWT
			.create(CssDataGridResources.class);

	public final CSSImageResource cSSImageResource = GWT
			.create(CSSImageResource.class);

	@UiField HorizontalPanel horMain;
	@UiField HTMLPanel htmlTaskList;
	@UiField HTMLPanel htmlMenu;
	@UiField Label btnAddTask;
	
	public static final int menuHeight = 46;
	
	private AppConstants appConstants = GWT.create(AppConstants.class);
	
	private MyTasksTable gridTaskRecipients = new MyTasksTable(dataGridCss,
			cSSImageResource);

	private List<Task_Data> autoGenerateTasks = Task_Data.autoGenerateTasks();

	private List<Task_Data> allTasks = new ArrayList<Task_Data>();

	private List<Task_Data> recipients = new ArrayList<Task_Data>();

	private List<Task_Data> senders = new ArrayList<Task_Data>();
	
	private DataGrid<Task_Data> gridRecipient;

	private DataGrid<Task_Data> gridSender;

	private Presenter presenter;
	
	CreateTaskDialog createTaskDialog = new CreateTaskDialog();

	public TaskList() {
		initWidget(uiBinder.createAndBindUi(this));
		htmlMenu.setHeight(menuHeight + "px");
		horMain.setHeight(Window.getClientHeight() - Container.headerHeight - menuHeight + "px");
		InitViewMyTasks();
//		InitViewAllTasks();
	}

	private void InitViewMyTasks() {
		horMain.setSpacing(10);
		InitViewRecipient();
		InitViewSender();
	}

	private void InitViewAllTasks() {
		AllTasksTable tableAllTasks = new AllTasksTable(dataGridCss,
				cSSImageResource);
		DataGrid<Task_Data> allTasksDataGrid = tableAllTasks.InitTable();
		allTasksDataGrid.setSize("100%", "100%");
		horMain.add(allTasksDataGrid);
		ListDataProvider<Task_Data> providerAllTasks = new ListDataProvider<Task_Data>();
		providerAllTasks.addDataDisplay(allTasksDataGrid);
		allTasks = providerAllTasks.getList();
		allTasks.addAll(autoGenerateTasks);
		Task_Data data = new Task_Data();
		allTasks.add(data);
		tableAllTasks.addFooter();
	}

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
//		recipients.addAll(autoGenerateTasks.subList(0, 9));
	}

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
//		senders.addAll(autoGenerateTasks.subList(10, 19));
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

	@Override
	public void setTaskList(List<Task_Data> taskList) {
		allTasks.clear();
		senders.clear();
		recipients.clear();
		allTasks.addAll(taskList);
		String user = Storage.getUserProfiles().getEmail();
		for(Task_Data data: allTasks) {
			if(data.getSender() != null && data.getSender().equals(user))
				senders.add(data);
			if(data.getRecipient() != null && data.getRecipient().equals(user))
				recipients.add(data);
		}
	}

	@UiHandler("btnAddTask")
	void onBtnAddTaskClick(ClickEvent event) {
		createTaskDialog.getInfo();
		createTaskDialog.center();
	}
}
