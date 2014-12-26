package com.softlink.minitask.client.activity.ui;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.softlink.minitask.client.MiniTask;
import com.softlink.minitask.client.view.desktop.ui.CreateProjectDialog;
import com.softlink.minitask.shared.Task_Project;

public class CreateProjectDialogActivity implements CreateProjectDialog.Presenter{

	@Override
	public void createProject(Task_Project project) {
		MiniTask.appController.dataRequest.insertProject(project, new AsyncCallback<Task_Project>() {
			@Override
			public void onSuccess(Task_Project result) {
				// TODO Auto-generated method stub	
			}
			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
			}
		});
	}

}
