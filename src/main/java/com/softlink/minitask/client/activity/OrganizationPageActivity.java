package com.softlink.minitask.client.activity;

import java.util.ArrayList;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.softlink.minilib.shared.Invite_Token;
import com.softlink.minilib.shared.System_Organization;
import com.softlink.minitask.client.AppController.Storage;
import com.softlink.minitask.client.MiniTask;
import com.softlink.minitask.client.events.InOrganizationPageEvent;
import com.softlink.minitask.client.place.OrganizationPlace;
import com.softlink.minitask.client.place.WelcomePlace;
import com.softlink.minitask.client.view.OrganizationPageInf;

public class OrganizationPageActivity extends AbstractActivity implements OrganizationPageInf.Presenter{
	
	public OrganizationPageActivity(OrganizationPlace place) {
	}

	@Override
	public void start(AcceptsOneWidget containerWidget, EventBus eventBus) {
		eventBus.fireEvent(new InOrganizationPageEvent());
		if(!Storage.getUserProfiles().isLogin()) {
			MiniTask.clientFactory.getPlaceController().goTo(new WelcomePlace());
		}
		else {
			MiniTask.clientFactory.getOrganizationPage().setPresenter(this);
			MiniTask.clientFactory.getOrganizationPage().clear();
			MiniTask.clientFactory.getOrganizationPage().setOrganizationList(Storage.getUserProfiles().getOrganizationList());
			MiniTask.clientFactory.getOrganizationPage().setInviteTokenList(Storage.getUserProfiles().getInviteTokenList());
		}
	} 
	
	@Override
	public String mayStop() {
		MiniTask.clientFactory.getOrganizationPage().setPresenter(null);
		return null;
	}

	@Override
	public void doCreateOrganization(System_Organization organization) {
		MiniTask.appController.systemService.insertOrganization(organization, new AsyncCallback<System_Organization>() {
			@Override
			public void onSuccess(System_Organization result) {
				if(Storage.getUserProfiles().getOrganizationList() == null)
					Storage.getUserProfiles().setOrganizationList(new ArrayList<System_Organization>());
				Storage.getUserProfiles().getOrganizationList().add(result);
				MiniTask.clientFactory.getOrganizationPage().setOrganizationList(Storage.getUserProfiles().getOrganizationList());
			}	
			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub			
			}
		});
	}

	@Override
	public void goToOrganizationDetail(System_Organization organization) {
		MiniTask.clientFactory.getPlaceController().goTo(new OrganizationPlace(organization.getId()));
	}

	@Override
	public void doAcceptInviteToken(final Invite_Token token) {
		MiniTask.appController.systemService.inviteTokenAccepted(token, new AsyncCallback<System_Organization>() {
			@Override
			public void onSuccess(System_Organization result) {
				Storage.getUserProfiles().getInviteTokenList().remove(token);
				if(result != null)
					Storage.getUserProfiles().getOrganizationList().add(result);
				MiniTask.clientFactory.getOrganizationPage().setOrganizationList(Storage.getUserProfiles().getOrganizationList());
				MiniTask.clientFactory.getOrganizationPage().setInviteTokenList(Storage.getUserProfiles().getInviteTokenList());
			}
			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub	
			}
		});
	}

}
