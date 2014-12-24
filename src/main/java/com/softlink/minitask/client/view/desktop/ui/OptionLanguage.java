package com.softlink.minitask.client.view.desktop.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Event.NativePreviewEvent;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.Widget;
import com.softlink.minitask.client.AppConstants;

public class OptionLanguage extends DialogBox {

	private static Binder uiBinder = GWT.create(Binder.class);
	@UiField
	Label btClose;
	@UiField
	Label lbHeader;
	@UiField
	RadioButton btVietNamese;
	@UiField
	RadioButton btEnglish;

	private final AppConstants CONSTANTS = GWT.create(AppConstants.class);

	interface Binder extends UiBinder<Widget, OptionLanguage> {
	}

	public OptionLanguage() {
		setWidget(uiBinder.createAndBindUi(this));
		setStyleName("clean");
		setAnimationEnabled(false);
		setGlassEnabled(false);
		setAutoHideEnabled(true);
		SetTextForm();
	}

	protected void SetTextForm() {
		lbHeader.setText(CONSTANTS.OptionLanguagelbHeader());
		btEnglish.setText(CONSTANTS.OptionLanguagebtEnglish());
		btVietNamese.setText(CONSTANTS.OptionLanguagebtVietNamese());
		href = Window.Location.getHref();
		if (href.contains(local_en)) {
			btEnglish.setValue(true);
		} else
			btVietNamese.setValue(true);

	}

	@Override
	protected void onPreviewNativeEvent(NativePreviewEvent preview) {
		super.onPreviewNativeEvent(preview);

		NativeEvent evt = preview.getNativeEvent();
		if (evt.getType().equals("keydown")) {
			// Use the popup's key preview hooks to close the dialog when either
			// enter or escape is pressed.
			switch (evt.getKeyCode()) {
			case KeyCodes.KEY_ENTER:
			case KeyCodes.KEY_ESCAPE:
				hide();
				break;
			}
		}
	}

	@UiHandler("btClose")
	void onBtCloseClick(ClickEvent event) {
		hide();
	}

	private String replaceURL;
	public static final String local_en = "?locale=" + "en";
	private String href;
	private String sharps = "#";

	@UiHandler("btEnglish")
	void onBtEnglishClick(ClickEvent event) {
		href = Window.Location.getHref();
		if (!href.contains(local_en)) {
			if (href.contains(sharps)) {
				String[] part = href.split(sharps);
				if (part.length >= 2)
					replaceURL = part[0] + local_en + sharps + part[1];
				else
					replaceURL = part[0] + local_en + sharps;
			}

			Window.Location.replace(replaceURL);
		}

	}

	@UiHandler("btVietNamese")
	void onBtVietNameseClick(ClickEvent event) {
		href = Window.Location.getHref();
		if (href.contains(local_en)) {
			String spilit = local_en + sharps;
			if (href.contains(spilit)) {

				String[] part = href.split("\\" + spilit);

				if (part.length >= 2)
					replaceURL = part[0] + sharps + part[1];

				else
					replaceURL = part[0] + sharps;
				Window.Location.replace(replaceURL);
			}
		}
	}
}
