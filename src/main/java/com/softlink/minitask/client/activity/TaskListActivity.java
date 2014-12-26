package com.softlink.minitask.client.activity;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.softlink.minitask.client.AppController.Storage;
import com.softlink.minitask.client.MiniTask;
import com.softlink.minitask.client.place.OrganizationPlace;
import com.softlink.minitask.client.place.TaskListPlace;
import com.softlink.minitask.client.view.TaskListInf;
import com.softlink.minitask.shared.LocalData;

public class TaskListActivity extends AbstractActivity implements TaskListInf.Presenter{
	
	TaskListInf taskList;
	
	public TaskListActivity(TaskListPlace place) {
	}

	@Override
	public void start(AcceptsOneWidget containerWidget, EventBus eventBus) {
		if(Storage.getUserProfiles().getOrganizationCurrently() == null) {
			MiniTask.clientFactory.getPlaceController().goTo(new OrganizationPlace(null));
		} 
		else {
//			eventBus.fireEvent(new InTaskListEvent());
			MiniTask.clientFactory.getContainer().inTaskList();
			taskList = MiniTask.clientFactory.getTaskList(); 
			taskList.setPresenter(this);
			taskList.activityStart();
		}
	} 
	
	@Override
	public String mayStop() {
		if(taskList != null)
			taskList.setPresenter(null);
		return null;
	}

	@Override
	public void desktopLoadData() {
		final LocalData localData = Storage.getLocalData();
		if(!localData.isLoad())
			MiniTask.appController.dataRequest.retrieveAllData(new AsyncCallback<LocalData>() {
				@Override
				public void onSuccess(LocalData result) {
					localData.setLoad(true);
					localData.setProjectList(result.getProjectList());
					localData.setTaskList(result.getTaskList());
					taskList.setTaskList(result.getTaskList());
				}	
				@Override
				public void onFailure(Throwable caught) {
					// TODO Auto-generated method stub
				}
			});
		else
			taskList.setTaskList(localData.getTaskList());
	}

}
