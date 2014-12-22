package com.softlink.minitask.client.mvp;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;
import com.softlink.minitask.client.activity.LoginPageActivity;
import com.softlink.minitask.client.activity.OrganizationDetailActivity;
import com.softlink.minitask.client.activity.OrganizationPageActivity;
import com.softlink.minitask.client.activity.ProjectEditActivity;
import com.softlink.minitask.client.activity.ProjectListActivity;
import com.softlink.minitask.client.activity.ProjectViewActivity;
import com.softlink.minitask.client.activity.TaskEditActivity;
import com.softlink.minitask.client.activity.TaskListActivity;
import com.softlink.minitask.client.activity.TaskViewActivity;
import com.softlink.minitask.client.place.OrganizationPlace;
import com.softlink.minitask.client.place.ProjectEditPlace;
import com.softlink.minitask.client.place.ProjectListPlace;
import com.softlink.minitask.client.place.ProjectViewPlace;
import com.softlink.minitask.client.place.TaskEditPlace;
import com.softlink.minitask.client.place.TaskListPlace;
import com.softlink.minitask.client.place.TaskViewPlace;
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
		
		if (place instanceof TaskListPlace)
			return new TaskListActivity((TaskListPlace) place);
		
		if (place instanceof TaskViewPlace)
			return new TaskViewActivity((TaskViewPlace) place);
		
		if (place instanceof TaskEditPlace)
			return new TaskEditActivity((TaskEditPlace) place);
		
		if (place instanceof ProjectListPlace)
			return new ProjectListActivity((ProjectListPlace) place);
		
		if (place instanceof ProjectViewPlace)
			return new ProjectViewActivity((ProjectViewPlace) place);
		
		if (place instanceof ProjectEditPlace)
			return new ProjectEditActivity((ProjectEditPlace) place);
		
		return null;
	}
}
