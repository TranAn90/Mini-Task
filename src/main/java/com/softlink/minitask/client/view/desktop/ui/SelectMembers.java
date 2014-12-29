package com.softlink.minitask.client.view.desktop.ui;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.cell.client.CheckboxCell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.BrowserEvents;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.Event.NativePreviewEvent;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.CellPreviewEvent;
import com.google.gwt.view.client.CellPreviewEvent.Handler;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.MultiSelectionModel;

public class SelectMembers extends DialogBox {
	
	interface Binder extends UiBinder<Widget, SelectMembers> {
	}

	private static Binder uiBinder = GWT.create(Binder.class);
	
	private final CellTable.Resources tableRes = GWT.create(CSSCellTableResources.class);

	@UiField(provided = true)
	CellTable<String> talbeUser = new CellTable<String>(0, tableRes);
	@UiField
	Image imgClose;
	@UiField
	Label btnCancel;
	@UiField
	Label btnOk;
	@UiField Label btnSelectAllMember;

	private ListDataProvider<String> dataProviderTable = new ListDataProvider<String>();

	private List<String> listMembers = new ArrayList<String>();
	private List<String> listSelected = new ArrayList<String>();

	public interface Listener {
		void setListSelectMember(List<String> listMembers);
	}

	private Listener listener;

	public void setListMembers(List<String> listAllMembers, List<String> listSelectedMembers) {
		listMembers.clear();
		listMembers.addAll(listAllMembers);
		listSelected.clear();
		listSelected.addAll(listSelectedMembers);
		talbeUser.redraw();
	}

	public SelectMembers(Listener listener) {
		setWidget(uiBinder.createAndBindUi(this));
		setStyleName("clean");
		setGlassEnabled(true);

		this.listener = listener;
		InitTable();
	}

	public void InitTable() {
		talbeUser.setAutoHeaderRefreshDisabled(true);
		talbeUser.setEmptyTableWidget(new Label("..."));
		talbeUser.setPageSize(100);

		final MultiSelectionModel<String> selectionModel = new MultiSelectionModel<String>();
		final Column<String, Boolean> clCheck = new Column<String, Boolean>(
				new CheckboxCell(false, true)) {
			@Override
			public Boolean getValue(String object) {
				if (listSelected.contains(object))
					return true;
				else
					return false;
			}
		};

		clCheck.setFieldUpdater(new FieldUpdater<String, Boolean>() {
			@Override
			public void update(int index, String object, Boolean value) {
				selectionModel.setSelected(object, value);
				String id = object;
				if (value == false) {
					listSelected.remove(id);
				} else {
					listSelected.add(id);
				}
			}
		});

		talbeUser.addColumn(clCheck);
		talbeUser.setColumnWidth(clCheck, "20px");

		TextColumn<String> clName = new TextColumn<String>() {
			@Override
			public String getValue(String object) {
				return object;
			}
		};
		talbeUser.addColumn(clName);

		talbeUser.addCellPreviewHandler(new Handler<String>() {
			@Override
			public void onCellPreview(CellPreviewEvent<String> event) {
				if (BrowserEvents.CLICK
						.equals(event.getNativeEvent().getType())) {
					if (event.getColumn() != 0) {
						String member = event.getValue();
						if (listSelected.contains(member))
							listSelected.remove(member);
						else
							listSelected.add(member);
						talbeUser.redraw();
					}
				}
			}
		});

		dataProviderTable.addDataDisplay(talbeUser);
		listMembers = dataProviderTable.getList();
	}

	@UiHandler("imgClose")
	void onImgCloseClick(ClickEvent event) {
		hide();
	}

	@Override
	protected void onPreviewNativeEvent(NativePreviewEvent event) {
		super.onPreviewNativeEvent(event);
		switch (event.getTypeInt()) {
		case Event.ONKEYDOWN:
			if (event.getNativeEvent().getKeyCode() == KeyCodes.KEY_ESCAPE) {
				hide();
			}
			break;
		}
	}

	@UiHandler("btnOk")
	void onBtnOkClick(ClickEvent event) {
		listener.setListSelectMember(listSelected);
		hide();
	}

	@UiHandler("btnCancel")
	void onBtnCancelClick(ClickEvent event) {
		hide();
	}
	
	@UiHandler("btnSelectAllMember")
	void onBtnSelectAllMemberClick(ClickEvent event) {
		listSelected.clear();
		listSelected.addAll(listMembers);
		talbeUser.redraw();
	}
}
