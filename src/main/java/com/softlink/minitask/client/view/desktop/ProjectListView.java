package com.softlink.minitask.client.view.desktop;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gwt.cell.client.DateCell;
import com.google.gwt.cell.client.TextCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.DataGrid;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.ListDataProvider;
import com.softlink.minitask.client.AppConstants;
import com.softlink.minitask.client.view.desktop.ui.CssDataGridResources;
import com.softlink.minitask.shared.CommonFunction;
import com.softlink.minitask.shared.Task_Project;

public class ProjectListView extends Composite {

	private static Binder uiBinder = GWT.create(Binder.class);

	interface Binder extends UiBinder<Widget, ProjectListView> {
	}

	public final DataGrid.Resources dataGridCss = GWT
			.create(CssDataGridResources.class);
	@UiField(provided = true)
	DataGrid<Task_Project> gridProjects;

	private final AppConstants CONSTANTS = GWT.create(AppConstants.class);
	private List<Task_Project> projects = new ArrayList<Task_Project>();
	private ListDataProvider<Task_Project> dataProvider = new ListDataProvider<Task_Project>();

	public ProjectListView() {
		gridProjects = new DataGrid<Task_Project>(50, dataGridCss);
		initWidget(uiBinder.createAndBindUi(this));
		InitDataGrid();
	}

	protected void InitDataGrid() {
		gridProjects.setHeight("500px");
		gridProjects.setWidth("100%");
		Label lbEmtry = new Label(CONSTANTS.EmptryLabel());
		gridProjects.setEmptyTableWidget(lbEmtry);
		gridProjects.setAutoHeaderRefreshDisabled(true);
		dataProvider.addDataDisplay(gridProjects);
		projects = dataProvider.getList();
		Column<Task_Project, String> clNameProject = new Column<Task_Project, String>(
				new TextCell()) {

			@Override
			public String getValue(Task_Project object) {
				if (object != null) {
					String name = object.getName();
					if (name.length() >= 300)
						return name.substring(0, 300);
					else
						return name;
				}

				else
					return null;

			}
		};
		gridProjects.addColumn(clNameProject, CONSTANTS.ViewProjectName());

		// column view name manager project

		Column<Task_Project, String> clManager = new Column<Task_Project, String>(
				new TextCell()) {

			@Override
			public String getValue(Task_Project object) {
				return (object == null || object.getManager() == null) ? null
						: object.getManager();

			}
		};
		gridProjects.addColumn(clManager, CONSTANTS.ViewProjectManager());

		Column<Task_Project, String> clListMember = new Column<Task_Project, String>(
				new TextCell()) {

			@Override
			public String getValue(Task_Project object) {
				return (object == null || object.getListMember().isEmpty()) ? null
						: object.getListMember().toString();

			}
		};
		gridProjects.addColumn(clListMember,
				CONSTANTS.ViewProjectListMember());

		DateTimeFormat formatDate = CommonFunction.formatDate;

		DateCell dateCell = new DateCell(formatDate);
		Column<Task_Project, Date> clDueDate = new Column<Task_Project, Date>(
				dateCell) {

			@Override
			public Date getValue(Task_Project object) {

				return (object == null || object.getDueDate() == null) ? null
						: object.getDueDate();
			}
		};
		gridProjects.addColumn(clDueDate, CONSTANTS.ViewDueDate());
	}

}
