package com.softlink.minitask.client.view.desktop.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.logical.shared.CloseEvent;
import com.google.gwt.event.logical.shared.CloseHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Event.NativePreviewEvent;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.Widget;
import com.softlink.minitask.client.activity.ui.UserProfileDialogActivity;

public class UserProfileDialog extends DialogBox {

	private static UserProfileDialogUiBinder uiBinder = GWT
			.create(UserProfileDialogUiBinder.class);
	
	@UiField Label lbUserName;
	@UiField Label btnClose;
	@UiField Label btnSignOut;
	
	public boolean isShowing = false;

	interface UserProfileDialogUiBinder extends
			UiBinder<Widget, UserProfileDialog> {
	}
	
	public interface Presenter {
		void signOut();
	}
	
	private Presenter presenter;

	public UserProfileDialog(String userName) {
		setWidget(uiBinder.createAndBindUi(this));
		presenter = new UserProfileDialogActivity();
		
		setStyleName("clean");
		setAnimationEnabled(false);
		setGlassEnabled(false);
		setAutoHideEnabled(true);
		
		lbUserName.setText(userName);
		
		final Timer t = new Timer() {
			@Override
			public void run() {
				isShowing = false;
			}
		};
		
		this.addCloseHandler(new CloseHandler<PopupPanel>() {
			@Override
			public void onClose(CloseEvent<PopupPanel> event) {
				t.schedule(200);
			}
		});
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

	@UiHandler("btnClose")
	void onBtnCloseClick(ClickEvent event) {
		hide();
	}
	
	@UiHandler("btnSignOut")
	void onBtnSignOutClick(ClickEvent event) {
		presenter.signOut();
	}
}
