package com.softlink.minitask.client.view.desktop;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gwt.cell.client.ClickableTextCell;
import com.google.gwt.cell.client.DateCell;
import com.google.gwt.cell.client.ImageResourceCell;
import com.google.gwt.cell.client.TextCell;
import com.google.gwt.cell.client.Cell.Context;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.HeadingElement;
import com.google.gwt.dom.client.ParagraphElement;
import com.google.gwt.dom.client.TableCellElement;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.resources.client.ImageResource;
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
import com.softlink.minitask.client.view.desktop.ui.CSSImageResource;
import com.softlink.minitask.client.view.desktop.ui.CssDataGridResources;
import com.softlink.minitask.shared.CommonFunction;
import com.softlink.minitask.shared.Task_Data;
import com.softlink.minitask.shared.Task_Project;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.DataGrid;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.event.dom.client.ClickEvent;

public class ProjectView extends Composite implements ProjectViewInf {

	private static Binder uiBinder = GWT.create(Binder.class);

	interface Binder extends UiBinder<Widget, ProjectView> {
	}

	@UiField
	HeadingElement name;

	@UiField
	TableCellElement listMemberth;
	@UiField
	TableCellElement listMember;
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
	Label btnBack;
	@UiField
	ScrollPanel sclContent;
	@UiField
	HTMLPanel htmlMenu;
	@UiField
	HeadingElement nameSubProject;
	@UiField
	TableCellElement initDateSubth;
	@UiField
	TableCellElement initDateSub;
	@UiField
	TableCellElement dueDateSub;
	@UiField
	TableCellElement dueDateSubth;
	@UiField(provided = true)
	CellTable<Task_Data> gridTasks;
	@UiField
	Anchor btNewTask;

	private final AppConstants CONSTANTS = GWT.create(AppConstants.class);
	private List<Task_Data> tasks = new ArrayList<Task_Data>();
	private ListDataProvider<Task_Data> dataProvider = new ListDataProvider<Task_Data>();
	private final String spilit = TaskView.split;
	private final CommonFunction fun = new CommonFunction();
	public final CSSImageResource cSSImageResource = GWT
			.create(CSSImageResource.class);
	private Presenter presenter;

	public static final int menuHeight = 46;

	public ProjectView() {
		gridTasks = new CellTable<Task_Data>();
		initWidget(uiBinder.createAndBindUi(this));
		htmlMenu.setHeight(menuHeight + "px");
		sclContent.setHeight(Window.getClientHeight() - Container.headerHeight
				- menuHeight + "px");
		SetTextForm();
		InitTable();
	}

	protected void InitTable() {
		dataProvider.addDataDisplay(gridTasks);
		tasks = dataProvider.getList();
		Task_Data t = new Task_Data();
		t.setName("new name");
		t.setDueDate(new Date());
		t.setPriority(2);
		t.setSecurity(true);
		t.setStatus(0);
		tasks.add(t);
		Label lbEmpty = new Label(CONSTANTS.EmptryLabel());
		gridTasks.setEmptyTableWidget(lbEmpty);
		gridTasks.setWidth("100%");
		gridTasks.setHeight("100%");
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
		gridTasks.addColumn(clStatus, CONSTANTS.ViewTaskStatus());

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
		gridTasks.addColumn(clNameTask, CONSTANTS.ViewTaskName());

		// cl project
		Column<Task_Data, String> clNameProject = new Column<Task_Data, String>(
				new ClickableTextCell()) {
			@Override
			public String getValue(Task_Data object) {
				return "nameProject";
			}
		};
		gridTasks.addColumn(clNameProject, CONSTANTS.ViewProjectName());

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
		gridTasks.addColumn(clDueDate, CONSTANTS.ViewDueDate());

	}

	protected void SetTextForm() {
		nameSubProject.setInnerText("Giai doan 1 cua du an");
		// header.setInnerHTML(CONSTANTS.ProjectDetailHeader());
		listMemberth.setInnerHTML(CONSTANTS.ViewProjectListMember() + spilit);
		managerth.setInnerHTML(CONSTANTS.ViewProjectManager() + spilit);
		creatorth.setInnerHTML(CONSTANTS.ViewProjectCreator() + spilit);
		startDateth.setInnerHTML(CONSTANTS.ViewProjectStartDate() + spilit);
		dueDateth.setInnerHTML(CONSTANTS.ViewDueDate() + spilit);
		descriptionth.setInnerHTML(CONSTANTS.ViewDescription());
		btNewTask.setText(CONSTANTS.ButtonTextNewTask());
		// for subproject
		dueDateSubth.setInnerText(CONSTANTS.ViewDueDate());
		initDateSubth.setInnerText(CONSTANTS.ViewInitDate());
		// btEdit.setText(CONSTANTS.ButtonTextEdit());
		// btRemove.setText(CONSTANTS.ButtonTextRemove());
	}

	@Override
	public void setProject(Task_Project project) {
		name.setInnerText(project.getName());
		creator.setInnerText("Tạo bởi " + project.getCreator());
		listMember.setInnerText(project.getListMember().toString());
		manager.setInnerText(project.getManager());
		creator.setInnerText(project.getCreator());
		// startDate.setInnerText(project.getStartDate().toString());
		dueDate.setInnerText(project.getDueDate().toString());
		description.setInnerText(project.getDescription());
	}

	@Override
	public void activityStart() {
		if (presenter != null)
			presenter.desktopLoadData();
	}

	@Override
	public void setPresenter(Presenter presenter) {
		this.presenter = presenter;
	}

	@UiHandler("btNewTask")
	void onBtNewTaskClick(ClickEvent event) {
	}
}
