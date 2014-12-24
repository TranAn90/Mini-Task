package com.softlink.minitask.client.view;

import java.util.List;

import com.google.gwt.user.client.ui.IsWidget;
import com.softlink.minilib.shared.Invite_Token;
import com.softlink.minilib.shared.System_Organization;

public interface OrganizationPageInf extends IsWidget {
	
	void clear();
	
	void setOrganizationList(List<System_Organization> organizationList);
	
	void setInviteTokenList(List<Invite_Token> inviteTokenList);
	
	void setPresenter(Presenter presenter);

	public interface Presenter {
		void doCreateOrganization(System_Organization organization);
		
		void goToOrganizationDetail(System_Organization organization);
		
		void goToOrganizationTask(System_Organization organization);
		
		void doAcceptInviteToken(Invite_Token token);
	}

}
