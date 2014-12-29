package com.softlink.minitask.client.activity;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.softlink.minitask.client.AppController.Storage;
import com.softlink.minitask.client.MiniTask;
import com.softlink.minitask.client.place.OrganizationPlace;
import com.softlink.minitask.client.place.ProjectListPlace;
import com.softlink.minitask.client.place.ProjectViewPlace;
import com.softlink.minitask.client.view.ProjectListInf;
import com.softlink.minitask.shared.LocalData;
import com.softlink.minitask.shared.Task_Project;

public class ProjectListActivity extends AbstractActivity implements ProjectListInf.Presenter{
	
	ProjectListInf projectList;
	
	public ProjectListActivity(ProjectListPlace place) {
	}

	@Override
	public void start(AcceptsOneWidget containerWidget, EventBus eventBus) {
		if(Storage.getUserProfiles().getOrganizationCurrently() == null) {
			MiniTask.clientFactory.getPlaceController().goTo(new OrganizationPlace(null));
		} 
		else {
			MiniTask.clientFactory.getContainer().inProjectList();
			projectList = MiniTask.clientFactory.getProjectList(); 
			projectList.setPresenter(this);
			projectList.activityStart();
		}
	} 
	
	@Override
	public String mayStop() {
		if(projectList != null)
			projectList.setPresenter(null);
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
					projectList.setProjectList(result.getProjectList());
				}	
				@Override
				public void onFailure(Throwable caught) {
					// TODO Auto-generated method stub
				}
			});
		else
			projectList.setProjectList(localData.getProjectList());
		
	}

	@Override
	public void desktopRefresh() {
		final LocalData localData = Storage.getLocalData();
		MiniTask.appController.dataRequest.retrieveAllData(new AsyncCallback<LocalData>() {
			@Override
			public void onSuccess(LocalData result) {
				localData.setLoad(true);
				localData.setProjectList(result.getProjectList());
				localData.setTaskList(result.getTaskList());
				projectList.setProjectList(result.getProjectList());
			}	
			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
			}
		});
	}

	@Override
	public void goToProjectView(Task_Project project) {
		String tokenId = String.valueOf(project.getId());
		MiniTask.clientFactory.getPlaceController().goTo(new ProjectViewPlace(tokenId));
	}

}
