package com.softlink.minitask.client.view.desktop;

import java.util.Date;
import java.util.List;

import com.google.gwt.cell.client.Cell.Context;
import com.google.gwt.cell.client.ClickableTextCell;
import com.google.gwt.cell.client.DateCell;
import com.google.gwt.cell.client.ImageResourceCell;
import com.google.gwt.cell.client.TextCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.HeadingElement;
import com.google.gwt.dom.client.ParagraphElement;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.dom.client.TableCellElement;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.DataGrid;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.ListDataProvider;
import com.softlink.minitask.client.AppConstants;
import com.softlink.minitask.client.view.desktop.ui.CSSImageResource;
import com.softlink.minitask.client.view.desktop.ui.CssDataGridResources;
import com.softlink.minitask.shared.CommonFunction;
import com.softlink.minitask.shared.Task_Data;

public class TaskDetail extends Composite {

	private static Binder uiBinder = GWT.create(Binder.class);

	interface Binder extends UiBinder<Widget, TaskDetail> {
	}

	@UiField
	HeadingElement name;
	@UiField
	DivElement nameProject;
	@UiField
	TableCellElement statusth;
	@UiField
	TableCellElement status;
	@UiField
	TableCellElement dueDateth;
	@UiField
	TableCellElement dueDate;
	@UiField
	TableCellElement priorityth;
	@UiField
	TableCellElement priority;
	@UiField
	TableCellElement startDateth;
	@UiField
	TableCellElement startDate;
	@UiField
	TableCellElement senderth;
	@UiField
	TableCellElement sender;
	@UiField
	TableCellElement securityth;
	@UiField
	TableCellElement security;
	@UiField
	TableCellElement recipientth;
	@UiField
	TableCellElement recipient;
	@UiField
	TableCellElement idParentth;
	@UiField
	TableCellElement idParent;
	@UiField
	Element descriptionth;
	@UiField
	Element listSubTaskth;
	@UiField
	Label lbNameProject;
	@UiField
	ParagraphElement description;
	@UiField
	Anchor btNewSubTask;
	@UiField
	VerticalPanel verMainContent;
	@UiField
	HeadingElement header;
	@UiField
	Label btEdit;
	@UiField
	Label btCopy;
	@UiField
	Label btRemove;
	@UiField
	Label lbCcList;
	@UiField
	DivElement ccList;
	public static final String split = ":";

	private final AppConstants CONSTANT = GWT.create(AppConstants.class);
	public final CSSImageResource cSSImageResource = GWT
			.create(CSSImageResource.class);
	public final DataGrid.Resources dataGridCss = GWT
			.create(CssDataGridResources.class);

	private DataGrid<Task_Data> gridRecipient;
	private List<Task_Data> recipients;
	private CommonFunction fun = new CommonFunction();

	public TaskDetail() {
		initWidget(uiBinder.createAndBindUi(this));
		SetTextForm();
		InitGridSubTasks();
	}

	protected void SetTextForm() {
		lbNameProject.setText(CONSTANT.ViewProjectName() + split);
		statusth.setInnerHTML(CONSTANT.ViewTaskStatus());
		startDateth.setInnerHTML(CONSTANT.ViewProjectStartDate() + split);
		priorityth.setInnerHTML(CONSTANT.ViewTaskPriority() + split);
		dueDateth.setInnerHTML(CONSTANT.ViewDueDate() + split);
		senderth.setInnerHTML(CONSTANT.ViewTaskSender() + split);
		securityth.setInnerHTML(CONSTANT.ViewTaskSecurity() + split);
		recipientth.setInnerHTML(CONSTANT.ViewTaskRecipient() + split);
		idParentth.setInnerHTML(CONSTANT.ViewTaskParentId() + split);
		descriptionth.setInnerHTML(CONSTANT.ViewDescription());
		listSubTaskth.setInnerHTML(CONSTANT.TaskDetaillistSubTaskth());
		btNewSubTask.setText(CONSTANT.TaskDetailbtNewSubTask());
		header.setInnerHTML(CONSTANT.TaskDetaillbHeader());
		btEdit.setText(CONSTANT.ButtonTextEdit());
		btCopy.setText(CONSTANT.ButtonTextCopy());
		btRemove.setText(CONSTANT.ButtonTextRemove());
		lbCcList.setText(CONSTANT.ViewTaskCcList() + split);

	}

	protected void InitGridSubTasks() {
		gridRecipient = InitDataGrid();
		verMainContent.add(gridRecipient);
		ListDataProvider<Task_Data> providerRecipient = new ListDataProvider<Task_Data>();
		providerRecipient.addDataDisplay(gridRecipient);
		recipients = providerRecipient.getList();
		recipients.addAll(Task_Data.autoGenerateTasks().subList(0, 3));
	}

	protected DataGrid<Task_Data> InitDataGrid() {
		DataGrid<Task_Data> gridTasks = new DataGrid<Task_Data>(20, dataGridCss);
		Label lbEmpty = new Label(CONSTANT.EmptryLabel());
		gridTasks.setEmptyTableWidget(lbEmpty);
		gridTasks.setWidth("100%");
		gridTasks.setHeight("200px");
		gridTasks.setMinimumTableWidth(30, Unit.PX);
		gridTasks.setAutoHeaderRefreshDisabled(true);

		Column<Task_Data, ImageResource> clPriority = new Column<Task_Data, ImageResource>(
				new ImageResourceCell()) {

			@Override
			public ImageResource getValue(Task_Data object) {
				return fun.getImgPriority(object, cSSImageResource);
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
		gridTasks.addColumn(clStatus, CONSTANT.ViewTaskStatus());

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
		gridTasks.addColumn(clNameTask, CONSTANT.ViewTaskName());

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
		gridTasks.addColumn(clDueDate, CONSTANT.ViewDueDate());
		return gridTasks;
	}

	@UiHandler("btNewSubTask")
	void onBtNewSubTaskClick(ClickEvent event) {
	}

	@UiHandler("btEdit")
	void onBtEditClick(ClickEvent event) {
	}

	@UiHandler("btCopy")
	void onBtCopyClick(ClickEvent event) {
	}

	@UiHandler("btRemove")
	void onBtRemoveClick(ClickEvent event) {
	}
}
