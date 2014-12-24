package com.softlink.minitask.client.view.desktop;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TabPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.softlink.minilib.shared.System_Organization;
import com.softlink.minitask.client.AppConstants;
import com.softlink.minitask.client.AppController.Storage;
import com.softlink.minitask.client.view.OrganizationDetailInf;

public class OrganizationDetail extends Composite implements
		OrganizationDetailInf {

	private static CompanyDetailUiBinder uiBinder = GWT
			.create(CompanyDetailUiBinder.class);

	@UiField
	Label lbOrganizationName;
	@UiField
	TabPanel tabOrganizationSetting;
	@UiField
	TextBox txbOrganizationName;
	@UiField
	TextArea txbOrganizationDetail;
	@UiField
	Anchor btnBackOrganizationPage;
	@UiField
	Anchor btnGoToOrganization;
	@UiField
	HTMLPanel htmlUserTable;
	@UiField
	HTMLPanel htmlAddUser;
	@UiField
	Anchor btnAddUser;
	@UiField
	AbsolutePanel absAddUserForm;
	@UiField
	TextBox txbInviteUser;
	@UiField
	Label btnInviteUser;
	@UiField
	Label lbBack;
	@UiField
	Label lbNameOrganization;
	@UiField
	Label lbDescription;
	@UiField
	Label lbJoin;
	@UiField
	Label lbAddUser;
	@UiField 
	Label btnSave;

	private System_Organization organization;
	private boolean isAdmin;
	private Presenter presenter;

	interface CompanyDetailUiBinder extends
			UiBinder<Widget, OrganizationDetail> {
	}

	public OrganizationDetail() {
		initWidget(uiBinder.createAndBindUi(this));
		tabOrganizationSetting.selectTab(0);
		txbInviteUser.getElement().setPropertyString("placeholder", "Email");
		SetTextForm();
	}

	HTMLPanel renderAdminRowUI(String admin) {
		HTMLPanel AdminRowUi = new HTMLPanel("");
		AdminRowUi.setHeight("60px");
		AdminRowUi.setStyleName("OrganizationDetail-Obj12");
		Label adminName = new Label(admin);
		adminName.setStyleName("OrganizationDetail-Obj14 font-blackTitle");
		AdminRowUi.add(adminName);
		HorizontalPanel controlPanel = new HorizontalPanel();
		controlPanel.setHeight("35px");
		controlPanel.setStyleName("OrganizationDetail-Obj15");
		AdminRowUi.add(controlPanel);
		Label adminRole = new Label(CONSTANTS.UserRoleAdmin());
		adminRole.setStyleName("OrganizationDetail-Obj16 font-grayTitle");
		controlPanel.add(adminRole);
		return AdminRowUi;
	}

	HTMLPanel renderUserRowUI(String user) {
		HTMLPanel UserRowUi = new HTMLPanel("");
		UserRowUi.setHeight("60px");
		UserRowUi.setStyleName("OrganizationDetail-Obj12");
		Label userName = new Label(user);
		userName.setStyleName("OrganizationDetail-Obj14 font-blackTitle");
		UserRowUi.add(userName);
		HorizontalPanel controlPanel = new HorizontalPanel();
		controlPanel.setHeight("35px");
		controlPanel.setStyleName("OrganizationDetail-Obj15");
		UserRowUi.add(controlPanel);
		Label userRoleStatic = new Label(
				CONSTANTS.OrganizationDetailTabNameMember());
		userRoleStatic.setStyleName("OrganizationDetail-Obj16 font-grayTitle");
		Label userRoleControl = new Label(
				CONSTANTS.OrganizationDetailTabNameMember());
		userRoleControl.setSize("90px", "35px");
		userRoleControl
				.setStyleName("OrganizationDetail-Obj16 button-whiteStyle");
		Label removeUser = new Label();
		removeUser.setSize("90px", "35px");
		removeUser.setStyleName("OrganizationDetail-Obj16 button-whiteStyle");
		if (isAdmin) {
			removeUser.setText(CONSTANTS.ButtonTextRemove());
			controlPanel.add(userRoleControl);
			controlPanel.add(removeUser);
		} else {
			removeUser.setText(CONSTANTS.OrganizationDetailbtRemoveTextLeave());
			controlPanel.add(userRoleStatic);
			if (Storage.getUserProfiles().getEmail().equals(user))
				controlPanel.add(removeUser);
		}
		return UserRowUi;
	}

	HTMLPanel renderInviteRowUI(String invite) {
		HTMLPanel InviteRowUi = new HTMLPanel("");
		InviteRowUi.setHeight("60px");
		InviteRowUi.setStyleName("OrganizationDetail-Obj12");
		Label inviteName = new Label(invite);
		inviteName.setStyleName("OrganizationDetail-Obj14 font-blackTitle");
		InviteRowUi.add(inviteName);
		HorizontalPanel controlPanel = new HorizontalPanel();
		controlPanel.setHeight("35px");
		controlPanel.setStyleName("OrganizationDetail-Obj15");
		InviteRowUi.add(controlPanel);
		Label inviteRole = new Label(CONSTANTS.OrganizationDetaillbWaitingJoin());
		inviteRole.setStyleName("OrganizationDetail-Obj16 font-grayTitle");
		controlPanel.add(inviteRole);
		Label removeInvite = new Label(CONSTANTS.ButtonTextRemove());
		removeInvite.setSize("90px", "35px");
		removeInvite.setStyleName("OrganizationDetail-Obj16 button-whiteStyle");
		if (isAdmin)
			controlPanel.add(removeInvite);
		return InviteRowUi;
	}

	@Override
	public void setOrganization(System_Organization organization) {
		this.organization = organization;
		lbOrganizationName.setText(organization.getName());
		txbOrganizationName.setText(organization.getName());
		txbOrganizationDetail.setText(organization.getDescription());
		txbInviteUser.setText("");
		if (organization.getAdmin()
				.equals(Storage.getUserProfiles().getEmail()))
			isAdmin = true;
		else
			isAdmin = false;
		htmlUserTable.clear();
		if (isAdmin) {
			htmlUserTable.add(htmlAddUser);
			txbOrganizationName.setEnabled(true);
			txbOrganizationDetail.setEnabled(true);
		} else {
			txbOrganizationName.setEnabled(false);
			txbOrganizationDetail.setEnabled(false);
		}
		htmlUserTable.add(renderAdminRowUI(organization.getAdmin()));
		if (organization.getUserList() != null) {
			for (String user : organization.getUserList())
				htmlUserTable.add(renderUserRowUI(user));
		}
		if (organization.getInviteList() != null) {
			for (String invite : organization.getInviteList())
				htmlUserTable.add(renderInviteRowUI(invite));
		}
	}

	@Override
	public void setPresenter(Presenter presenter) {
		this.presenter = presenter;
	}

	@UiHandler("btnGoToOrganization")
	void onBtnGoToOrganizationClick(ClickEvent event) {
		if(presenter != null)
			presenter.goToOrganizationTask(organization);
	}

	@UiHandler("btnBackOrganizationPage")
	void onBtnBackOrganizationPageClick(ClickEvent event) {
		if (presenter != null)
			presenter.goToOrganizationPage();
	}

	@UiHandler("btnAddUser")
	void onBtnAddUserClick(ClickEvent event) {
		absAddUserForm.setVisible(true);
		txbInviteUser.setFocus(true);
	}

	@UiHandler("btnInviteUser")
	void onBtnInviteUserClick(ClickEvent event) {
		if (presenter != null)
			presenter.doInviteUser(txbInviteUser.getText(), organization);
	}

	@Override
	public void clear() {
		tabOrganizationSetting.selectTab(0);
		absAddUserForm.setVisible(false);
	}

	final AppConstants CONSTANTS = GWT.create(AppConstants.class);

	protected void SetTextForm() {
		lbBack.setText(CONSTANTS.ButtonTextBack());
		lbNameOrganization.setText(CONSTANTS
				.OrganizationDetailLbNameOrganization());
		lbDescription.setText(CONSTANTS.ViewDescription());
		tabOrganizationSetting.getTabBar().setTabText(0,
				CONSTANTS.OrganizationDetailTabNameInformation());
		tabOrganizationSetting.getTabBar().setTabText(1,
				CONSTANTS.OrganizationDetailTabNameMember());
		btnSave.setText(CONSTANTS.ButtonTextSave());
		lbAddUser.setText(CONSTANTS.OrganizationDetailLbAddUser());
		lbJoin.setText(CONSTANTS.OrganizationDetailLbJoin());
		btnInviteUser.setText(CONSTANTS.OrganizationDetailBtTextInviteUser());
	}
}
