package com.softlink.minitask.client.view.desktop.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RichTextArea;
import com.google.gwt.user.client.ui.Widget;
import com.softlink.minitask.client.AppConstants;
import com.softlink.minitask.shared.CommonFunction;

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

	private final CommonFunction fun = new CommonFunction();

	public ZoomOutDescription(String descriptionOld, String headerText,
			Listener listener) {
		setWidget(uiBinder.createAndBindUi(this));
		setText(headerText);
		setGlassEnabled(true);
		this.listener = listener;
		InitForm();
		center();
		show();
		if (fun.isBlank(descriptionOld) == false) {
			description.setText(descriptionOld);
		}
	}

	@UiHandler("btSave")
	void onBtSaveClick(ClickEvent event) {
		listener.onClickanSave(description.getText());
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

}
