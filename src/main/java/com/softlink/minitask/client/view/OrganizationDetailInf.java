package com.softlink.minitask.client.view;

import com.google.gwt.user.client.ui.IsWidget;
import com.softlink.minilib.shared.System_Organization;

public interface OrganizationDetailInf extends IsWidget{
	
	void clear();
	
	void setOrganization(System_Organization organization);
	
	void setPresenter(Presenter presenter);

	public interface Presenter {
		void goToOrganizationPage();
		
		void doInviteUser(String userEmail, System_Organization organization);
	}

}
