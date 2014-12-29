package com.softlink.minitask.client.activity;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.softlink.minilib.shared.System_Organization;
import com.softlink.minitask.client.AppController.Storage;
import com.softlink.minitask.client.MiniTask;
import com.softlink.minitask.client.place.OrganizationPlace;
import com.softlink.minitask.client.place.TaskListPlace;
import com.softlink.minitask.client.place.WelcomePlace;
import com.softlink.minitask.client.view.OrganizationDetailInf;

public class OrganizationDetailActivity extends AbstractActivity implements OrganizationDetailInf.Presenter{
	
	String token;
	
	public OrganizationDetailActivity(OrganizationPlace place) {
		this.token = place.getToken();
	}

	@Override
	public void start(AcceptsOneWidget containerWidget, EventBus eventBus) {
		if(!Storage.getUserProfiles().isLogin()) {
			MiniTask.clientFactory.getPlaceController().goTo(new WelcomePlace());
		}
		else {
//			eventBus.fireEvent(new InOrganizationDetailEvent());
			MiniTask.clientFactory.getContainer().inOrganizationDetail();
			MiniTask.clientFactory.getOrganizationDetail().setPresenter(this);
			System_Organization organization = Storage.getUserProfiles().findOrganization(token);
			if(organization == null) {
				MiniTask.clientFactory.getPlaceController().goTo(new OrganizationPlace(null));
			}
			else {
				MiniTask.clientFactory.getOrganizationDetail().clear();
				MiniTask.clientFactory.getOrganizationDetail().setOrganization(organization);
			}
		}
	} 
	
	@Override
	public String mayStop() {
		MiniTask.clientFactory.getOrganizationDetail().setPresenter(null);
		return null;
	}

	@Override
	public void goToOrganizationPage() {
		MiniTask.clientFactory.getPlaceController().goTo(new OrganizationPlace(null));
	}

	@Override
	public void doInviteUser(final String userEmail, final System_Organization organization) {
		MiniTask.appController.systemService.insertInviteToken(userEmail, organization, new AsyncCallback<Boolean>() {
			@Override
			public void onSuccess(Boolean result) {
				if(result == true) {
					Storage.getUserProfiles().findOrganization(organization.getId()).getInviteList().add(userEmail);
					MiniTask.clientFactory.getOrganizationDetail().setOrganization(organization);
				}
			}	
			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
			}
		});
	}

	@Override
	public void goToOrganizationTask(System_Organization organization) {
		Storage.getUserProfiles().setOrganizationCurrently(organization.getId());
		MiniTask.appController.systemService.goToOrganization(organization, new AsyncCallback<Boolean>() {
			@Override
			public void onSuccess(Boolean result) {
				if(result) {
					MiniTask.clientFactory.getPlaceController().goTo(new TaskListPlace());
					MiniTask.clientFactory.getContainer().updateHeaderInfo();
				}
				else {
					Window.alert("Error: can't find organization!");
					MiniTask.clientFactory.getPlaceController().goTo(new OrganizationPlace(null));
				}
			}
			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
			}
		});
	}

}
