package com.softlink.minitask.client.mvp;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceHistoryMapper;
import com.softlink.minitask.client.place.OrganizationPlace;
import com.softlink.minitask.client.place.ProjectEditPlace;
import com.softlink.minitask.client.place.ProjectListPlace;
import com.softlink.minitask.client.place.ProjectViewPlace;
import com.softlink.minitask.client.place.TaskEditPlace;
import com.softlink.minitask.client.place.TaskListPlace;
import com.softlink.minitask.client.place.TaskViewPlace;
import com.softlink.minitask.client.place.WelcomePlace;

/**
 * PlaceHistoryMapper interface is used to attach all places which the
 * PlaceHistoryHandler should be aware of.
 */
public class AppPlaceHistoryMapper implements PlaceHistoryMapper {

	String delimiter = "/";

	/**
	 * In fact it check URL on browser and set corresponding place in App
	 */
	@Override
	public Place getPlace(String token) {
		if (token == null || token.equals(""))
			return new WelcomePlace();

		String[] tokens = new String[2];
		if (!token.contains(delimiter))
			tokens[0] = token;
		else
			tokens = token.split(delimiter, 2);

		if (tokens[0].equals("welcome")) 
			return new WelcomePlace();
			
		else if (tokens[0].equals("organization")) 
			return new OrganizationPlace(tokens[1]);
		
		else if (tokens[0].equals("taskList")) 
			return new TaskListPlace();
		
		else if (tokens[0].equals("taskView")) 
			return new TaskViewPlace(tokens[1]);
		
		else if (tokens[0].equals("taskEdit")) 
			return new TaskEditPlace(tokens[1]);
		
		else if (tokens[0].equals("projectList")) 
			return new ProjectListPlace();
		
		else if (tokens[0].equals("projectView")) 
			return new ProjectViewPlace(tokens[1]);
		
		else if (tokens[0].equals("projectEdit")) 
			return new ProjectEditPlace(tokens[1]);
		
		return new WelcomePlace();
	}

	/**
	 * Contrary, it checks where you are and set corresponding URL on browser
	 */
	@Override
	public String getToken(Place place) {
		if (place instanceof WelcomePlace)
			return "welcome";
			
		else if (place instanceof OrganizationPlace) {
			OrganizationPlace currentPlace = (OrganizationPlace) place;
			if(currentPlace.getToken() == null || currentPlace.getToken() == "")
				return "organization";
			else
				return "organization" + delimiter + currentPlace.getToken();
		}
		
		else if (place instanceof TaskListPlace)
			return "taskList";
		
		else if (place instanceof TaskViewPlace)
			return "taskView" + delimiter + ((TaskViewPlace)place).getToken();
		
		else if (place instanceof TaskEditPlace)
			return "taskEdit" + delimiter + ((TaskEditPlace)place).getToken();
		
		else if (place instanceof ProjectListPlace)
			return "projectList";
		
		else if (place instanceof ProjectViewPlace)
			return "projectView" + delimiter + ((ProjectViewPlace)place).getToken();
		
		else if (place instanceof ProjectEditPlace)
			return "projectEdit" + delimiter + ((ProjectEditPlace)place).getToken();
		 
		return null;
	}
}
