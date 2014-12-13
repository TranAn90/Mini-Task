package com.softlink.minitask.client.mvp;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceHistoryMapper;
import com.softlink.minitask.client.place.OrganizationPlace;
import com.softlink.minitask.client.place.WelcomePlace;

/**
 * PlaceHistoryMapper interface is used to attach all places which the
 * PlaceHistoryHandler should be aware of.
 */
public class AppPlaceHistoryMapper implements PlaceHistoryMapper {

	String delimiter = "=";

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
		
		return null;
	}
}
