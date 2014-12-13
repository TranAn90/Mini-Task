package com.softlink.minitask.client;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.place.shared.PlaceController;
import com.softlink.minitask.client.view.ContainerInf;
import com.softlink.minitask.client.view.LoginPageInf;
import com.softlink.minitask.client.view.OrganizationDetailInf;
import com.softlink.minitask.client.view.OrganizationPageInf;
import com.softlink.minitask.client.view.desktop.Container;
import com.softlink.minitask.client.view.desktop.LoginPage;
import com.softlink.minitask.client.view.desktop.OrganizationDetail;
import com.softlink.minitask.client.view.desktop.OrganizationPage;

/**
 * Use ClientFactoryImpl by default for Desktop
 */
public class ClientFactoryImpl implements ClientFactory {
	
	private static final EventBus eventBus = new SimpleEventBus();
	private static final PlaceController placeController = new PlaceController(eventBus);

	private static final Container container = new Container();
	private static final LoginPage loginPage = new LoginPage();
	private static final OrganizationPage organizationPage = new OrganizationPage();
	private static final OrganizationDetail organizationDetail = new OrganizationDetail();
	
	public ClientFactoryImpl() {}

	@Override
	public EventBus getEventBus() {
		return eventBus;
	}

	@Override
	public PlaceController getPlaceController() {
		return placeController;
	}

	@Override
	public ContainerInf getContainer() {
		return container;
	}

	@Override
	public LoginPageInf getLoginPage() {
		return loginPage;
	}

	@Override
	public OrganizationPageInf getOrganizationPage() {
		return organizationPage;
	}

	@Override
	public OrganizationDetailInf getOrganizationDetail() {
		return organizationDetail;
	}

}
