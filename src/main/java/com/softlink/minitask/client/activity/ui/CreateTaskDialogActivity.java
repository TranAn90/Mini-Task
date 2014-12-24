package com.softlink.minitask.client.activity.ui;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.softlink.minitask.client.MiniTask;
import com.softlink.minitask.client.view.desktop.ui.CreateTaskDialog;
import com.softlink.minitask.shared.Task_Data;

public class CreateTaskDialogActivity implements CreateTaskDialog.Presenter{

	@Override
	public void createTask(Task_Data data) {
		MiniTask.appController.dataRequest.insertTask(data, new AsyncCallback<Task_Data>() {
			@Override
			public void onSuccess(Task_Data result) {
				// TODO Auto-generated method stub
			}
			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
			}
		});
	}

}
