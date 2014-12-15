package com.softlink.minitask.client.view.desktop.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.RichTextArea;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.softlink.minitask.shared.CommonFunction;
import com.google.gwt.user.client.ui.Label;

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
	Label btRemake;
	@UiField
	Anchor btCancel;

	private final CommonFunction fun = new CommonFunction();

	public ZoomOutDescription(String descriptionOld, String headerText,
			Listener listener) {
		setWidget(uiBinder.createAndBindUi(this));
		setText(headerText);
		setGlassEnabled(true);
		this.listener = listener;
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

	@UiHandler("btRemake")
	void onBtRemakeClick(ClickEvent event) {
		description.setText(null);
	}

	@UiHandler("btCancel")
	void onBtCancelClick(ClickEvent event) {
		hide();
	}
}
