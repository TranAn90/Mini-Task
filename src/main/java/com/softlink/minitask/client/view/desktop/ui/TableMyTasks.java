package com.softlink.minitask.client.view.desktop.ui;

import java.util.Date;

import com.google.gwt.cell.client.Cell.Context;
import com.google.gwt.cell.client.ClickableTextCell;
import com.google.gwt.cell.client.DateCell;
import com.google.gwt.cell.client.ImageResourceCell;
import com.google.gwt.cell.client.TextCell;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.DataGrid;
import com.google.gwt.user.client.ui.Label;
import com.softlink.minitask.client.AppConstants;
import com.softlink.minitask.shared.CommonFunction;
import com.softlink.minitask.shared.Task_Data;

public class TableMyTasks {
	private DataGrid<Task_Data> gridTasks;
	private CSSImageResource cSSImageResource;
	private DataGrid.Resources dataGridCss;

	public TableMyTasks(DataGrid.Resources dataGridCss,
			CSSImageResource cSSImageResource) {
		this.dataGridCss = dataGridCss;
		this.cSSImageResource = cSSImageResource;
	}

	private CommonFunction fun = new CommonFunction();
	private AppConstants appConstants = GWT.create(AppConstants.class);

	public DataGrid<Task_Data> InitDataGrid() {
		gridTasks = new DataGrid<Task_Data>(20, dataGridCss);
		Label lbEmpty = new Label(appConstants.EmptryLabel());
		gridTasks.setEmptyTableWidget(lbEmpty);
		gridTasks.setWidth("100%");
		gridTasks.setHeight("500px");
		gridTasks.setMinimumTableWidth(30, Unit.PX);
		gridTasks.setAutoHeaderRefreshDisabled(true);

		Column<Task_Data, ImageResource> clPriority = new Column<Task_Data, ImageResource>(
				new ImageResourceCell()) {

			@Override
			public ImageResource getValue(Task_Data object) {
				return fun.getImgPriority(object, cSSImageResource);
			}

			@Override
			public String getCellStyleNames(Context context, Task_Data object) {
				// TODO Auto-generated method stub
				return "";
			}

		};
		gridTasks.setColumnWidth(clPriority, "30px");

		gridTasks.addColumn(clPriority);
		// column status
		Column<Task_Data, String> clStatus = new Column<Task_Data, String>(
				new TextCell()) {

			@Override
			public String getValue(Task_Data object) {
				if (object == null)
					return "";
				else {
					String viewStatus = fun.getTextTaskStatus(object
							.getStatus());
					return viewStatus;
				}
			}

			@Override
			public String getCellStyleNames(Context context, Task_Data object) {
				return "grid-TaskStatus";
			}
		};
		gridTasks.setColumnWidth(clStatus, "100px");
		gridTasks.addColumn(clStatus, appConstants.ViewTaskStatus());

		Column<Task_Data, String> clNameTask = new Column<Task_Data, String>(
				new ClickableTextCell()) {
			@Override
			public String getValue(Task_Data object) {

				if (object != null && object.getName() != null) {
					String stReturn = object.getName();
					if (stReturn.length() >= 100) {
						return stReturn.substring(0, 100) + "....";
					} else {
						return stReturn;
					}
				} else {
					return null;
				}

			}
		};
		gridTasks.addColumn(clNameTask, appConstants.ViewTaskName());

		// cl project
		Column<Task_Data, String> clNameProject = new Column<Task_Data, String>(
				new ClickableTextCell()) {
			@Override
			public String getValue(Task_Data object) {
				return "nameProject";
			}
		};
		gridTasks.addColumn(clNameProject, appConstants.ViewProjectName());
		// column description

		DateTimeFormat formatDate = CommonFunction.formatDate;

		DateCell dateCell = new DateCell(formatDate);
		// column due date
		Column<Task_Data, Date> clDueDate = new Column<Task_Data, Date>(
				dateCell) {
			@Override
			public Date getValue(Task_Data object) {
				return (object == null) ? null : object.getDueDate();
			}

			@Override
			public String getCellStyleNames(Context context, Task_Data object) {
				// String style = fun.getCssColumnDue_date(object,
				// curentDateTime);
				// return style;
				return null;

			}

		};
		gridTasks.setColumnWidth(clDueDate, "100px");
		gridTasks.addColumn(clDueDate, appConstants.ViewDueDate());
		return gridTasks;
	}
}
