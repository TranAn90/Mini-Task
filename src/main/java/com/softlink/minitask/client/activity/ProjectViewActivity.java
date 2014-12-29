package com.softlink.minitask.client.activity;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.softlink.minitask.client.AppController.Storage;
import com.softlink.minitask.client.MiniTask;
import com.softlink.minitask.client.place.OrganizationPlace;
import com.softlink.minitask.client.place.ProjectListPlace;
import com.softlink.minitask.client.place.ProjectViewPlace;
import com.softlink.minitask.client.view.ProjectViewInf;
import com.softlink.minitask.shared.LocalData;

public class ProjectViewActivity extends AbstractActivity implements ProjectViewInf.Presenter{
	
	ProjectViewInf projectView;
	Long tokenId;

	public ProjectViewActivity(ProjectViewPlace place) {
		tokenId = Long.valueOf(place.getToken());
	}

	@Override
	public void start(AcceptsOneWidget containerWidget, EventBus eventBus) {
		if (Storage.getUserProfiles().getOrganizationCurrently() == null) {
			MiniTask.clientFactory.getPlaceController().goTo(new OrganizationPlace(null));
		} else {
			projectView = MiniTask.clientFactory.getProjectView();
			projectView.setPresenter(this);
			projectView.activityStart();
		}
	}

	@Override
	public String mayStop() {
		if(projectView != null)
			projectView.setPresenter(null);
		return null;
	}

	@Override
	public void desktopLoadData() {
		final LocalData localData = Storage.getLocalData();
		if(!localData.isLoad()) {
			MiniTask.appController.dataRequest.retrieveAllData(new AsyncCallback<LocalData>() {
				@Override
				public void onSuccess(LocalData result) {
					localData.setLoad(true);
					localData.setProjectList(result.getProjectList());
					localData.setTaskList(result.getTaskList());
					if(localData.findProject(tokenId) != null) {
						projectView.setProject(localData.findProject(tokenId));
						MiniTask.clientFactory.getContainer().inProjectView();
					}
					else {
						Window.alert("Can't find project");
						MiniTask.clientFactory.getPlaceController().goTo(new ProjectListPlace());
					}
				}	
				@Override
				public void onFailure(Throwable caught) {
					// TODO Auto-generated method stub
				}
			});
		} 
		else {
			if(localData.findProject(tokenId) != null) {
				projectView.setProject(localData.findProject(tokenId));
				MiniTask.clientFactory.getContainer().inProjectView();
			}
			else {
				Window.alert("Can't find project");
				MiniTask.clientFactory.getPlaceController().goTo(new ProjectListPlace());
			}
		}
	}

}
