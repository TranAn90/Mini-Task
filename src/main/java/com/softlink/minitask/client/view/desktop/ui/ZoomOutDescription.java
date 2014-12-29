package com.softlink.minitask.client.view.desktop.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.Event.NativePreviewEvent;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RichTextArea;
import com.google.gwt.user.client.ui.Widget;
import com.softlink.minitask.client.AppConstants;
import com.softlink.minitask.shared.CommonFunction;
import com.google.gwt.user.client.ui.HTMLPanel;

public class ZoomOutDescription extends DialogBox {

	private static Binder uiBinder = GWT.create(Binder.class);

	interface Binder extends UiBinder<Widget, ZoomOutDescription> {
	}

	public interface Listener {
		void onClickanSave(String description);
	}

	private Listener listener;
	@UiField
	RichTextArea description;
	@UiField
	Label btSave;
	@UiField
	Anchor btCancel;
	@UiField
	Label lbOr;
	@UiField
	Label btReload;
	@UiField HTMLPanel htmlZoomOutDescription;

	private final CommonFunction fun = new CommonFunction();
	
	public void setDescription(String description) {
		this.description.setText(description);
	}

	public ZoomOutDescription(String descriptionOld, String headerText,
			Listener listener) {
		setWidget(uiBinder.createAndBindUi(this));
		setAnimationEnabled(true);
		setText(headerText);
		setGlassEnabled(true);
		this.listener = listener;
		InitForm();
		if (fun.isBlank(descriptionOld) == false) {
			description.setText(descriptionOld);
		}
		htmlZoomOutDescription.setWidth((Window.getClientWidth()-680)/2+"px");
	}

	@UiHandler("btSave")
	void onBtSaveClick(ClickEvent event) {
		listener.onClickanSave(description.getText());
		hide();
	}

	@UiHandler("btCancel")
	void onBtCancelClick(ClickEvent event) {
		hide();
	}

	@UiHandler("btReload")
	void onBtReloadClick(ClickEvent event) {
		description.setText(null);
	}

	private static final AppConstants CONSTANTS = GWT
			.create(AppConstants.class);

	protected void InitForm() {
		btCancel.setText(CONSTANTS.ButtonTextCancel());
		btReload.setText(CONSTANTS.ZoomOutDescriptionButtonTextReload());
		btSave.setText(CONSTANTS.ZoomOutDescriptionButtonTextSaveDes());
		lbOr.setText(CONSTANTS.ZoomOutDescriptionLabelTextOr());
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
