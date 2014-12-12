package com.softlink.minitask.client.view.desktop;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.Event.NativePreviewEvent;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.DisclosurePanel;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RichTextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.datepicker.client.DateBox;

public class CreateTaskDialog extends DialogBox {

	private static Binder uiBinder = GWT.create(Binder.class);
	@UiField
	Image btReload;
	@UiField
	Image btClose;
	@UiField
	Image btZoomOut;
	@UiField
	TextBox name;
	@UiField
	ListBox recipient;
	@UiField
	RichTextArea description;
	@UiField
	ListBox nameProject;
	@UiField
	DateBox dueDate;
	@UiField
	ListBox status;
	@UiField
	ListBox priority;
	@UiField
	TextBox parentId;
	@UiField
	CheckBox security;
	@UiField
	CheckBox sendToEmail;
	@UiField
	DisclosurePanel disclosurePanel;
	@UiField
	Label btSave;
	@UiField
	Label btSaveContinue;
	@UiField
	Label headerText;
	@UiField
	ListBox namePhase;
	@UiField
	FlowPanel tableCC;
	@UiField
	Anchor btAddCC;

	interface Binder extends UiBinder<Widget, CreateTaskDialog> {
	}

	public interface Listener {

	}

	private Listener listener;

	public CreateTaskDialog(Listener listener) {
		setWidget(uiBinder.createAndBindUi(this));
		setStyleName("frame_dialogBoxClean");
		setGlassEnabled(true);
		this.listener = listener;
		center();
		show();
		disclosurePanel.setOpen(false);

	}

	@UiHandler("btReload")
	void onBtReloadClick(ClickEvent event) {
	}

	@UiHandler("btClose")
	void onBtCloseClick(ClickEvent event) {
		hide();
	}

	@UiHandler("btSave")
	void onBtSaveClick(ClickEvent event) {
	}

	@UiHandler("btSaveContinue")
	void onBtSaveContinueClick(ClickEvent event) {
	}

	@UiHandler("btAddCC")
	void onBtAddCCClick(ClickEvent event) {
	}

	@UiHandler("btZoomOut")
	void onBtZoomOutClick(ClickEvent event) {
		@SuppressWarnings("unused")
		ZoomOutDescription zoom = new ZoomOutDescription(description.getText(),
				"Mở rộng mô tả dự án", new ZoomOutDescription.Listener() {

					@Override
					public void onClickanSave(String description) {
						// TODO Auto-generated method stub

					}
				});
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

}
