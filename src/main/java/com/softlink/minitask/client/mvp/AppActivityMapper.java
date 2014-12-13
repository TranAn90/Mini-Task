package com.softlink.minitask.client.mvp;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;
import com.softlink.minitask.client.activity.LoginPageActivity;
import com.softlink.minitask.client.activity.OrganizationDetailActivity;
import com.softlink.minitask.client.activity.OrganizationPageActivity;
import com.softlink.minitask.client.place.OrganizationPlace;
import com.softlink.minitask.client.place.WelcomePlace;

public class AppActivityMapper implements ActivityMapper {

	/**
	 * AppActivityMapper associates each Place with its corresponding
	 * {@link Activity}
	 * 
	 * @param clientFactory
	 *            Factory to be passed to activities
	 */
	public AppActivityMapper() {
		super();
	}

	/**
	 * Map each Place to its corresponding Activity. This would be a great use
	 * for GIN.
	 */
	@Override
	public Activity getActivity(Place place) {
		// This is begging for GIN
		if (place instanceof WelcomePlace)
			return new LoginPageActivity((WelcomePlace) place);
		
		else if (place instanceof OrganizationPlace) {
			OrganizationPlace currentPlace = (OrganizationPlace) place;
			if(currentPlace.getToken() == null || currentPlace.getToken().length() == 0)
				return new OrganizationPageActivity(currentPlace);
			else
				return new OrganizationDetailActivity(currentPlace);
		}
		
		return null;
	}
}
