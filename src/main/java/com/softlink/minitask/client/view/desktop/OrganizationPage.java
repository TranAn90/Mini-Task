package com.softlink.minitask.client.view.desktop;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOutHandler;
import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.event.dom.client.MouseOverHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.softlink.minilib.shared.Invite_Token;
import com.softlink.minilib.shared.System_Organization;
import com.softlink.minitask.client.AppController.Storage;
import com.softlink.minitask.client.view.OrganizationPageInf;

public class OrganizationPage extends Composite implements OrganizationPageInf{

	private static OrganizationPageUiBinder uiBinder = GWT
			.create(OrganizationPageUiBinder.class);
	
	@UiField HTMLPanel formOrganizationCreate;
	@UiField Anchor btnNewOrganization;
	@UiField TextBox txbOrganizationName;
	@UiField TextArea txbOrganizationDescription;
	@UiField Label btnCreateOrganization;
	@UiField FlowPanel flowOrganizationList;
	@UiField HTMLPanel htmlOrganizationForm;
	@UiField AbsolutePanel absOrganizationNew;
	@UiField HTMLPanel htmlInviteTable;
	
	private Presenter presenter;
	private List<System_Organization> organizationList;

	interface OrganizationPageUiBinder extends
			UiBinder<Widget, OrganizationPage> {
	}

	public OrganizationPage() {
		initWidget(uiBinder.createAndBindUi(this));
	}
	
	AbsolutePanel renderOrganizationUI(final System_Organization organization) {
		AbsolutePanel organizationUI = new AbsolutePanel();
		organizationUI.setSize("300px", "90px");
		organizationUI.setStyleName("organizationpage-Obj3");
		AbsolutePanel organizationUI1 = new AbsolutePanel();
		organizationUI1.setSize("90%", "100%");
		organizationUI1.setStyleName("organizationpage-Obj6");
		organizationUI.add(organizationUI1 , 0, 0);
		final Label lbOrganizationName = new Label(organization.getName());
		lbOrganizationName.setSize("240px", "21px");
		lbOrganizationName.setStyleName("organizationpage-Obj7");
		organizationUI1.add(lbOrganizationName, 10, 7);
		Anchor btnOrganization = new Anchor();
		btnOrganization.setSize("100%", "100%");
		organizationUI1.add(btnOrganization, 0, 0);
		final Label btnOrganiztionSetting = new Label("Cài đặt");
		btnOrganiztionSetting.setSize("70px", "30px");
		btnOrganiztionSetting.setVisible(false);
		btnOrganiztionSetting.setStyleName("organizationpage-Obj14 button-greenStyle");
		organizationUI1.add(btnOrganiztionSetting, 200, 60);
		//Event handler
		btnOrganization.addMouseOverHandler(new MouseOverHandler() {
			
			@Override
			public void onMouseOver(MouseOverEvent event) {
				btnOrganiztionSetting.setVisible(true);
			}
		});
		btnOrganization.addMouseOutHandler(new MouseOutHandler() {
			
			@Override
			public void onMouseOut(MouseOutEvent event) {
				btnOrganiztionSetting.setVisible(false);
			}
		});
		btnOrganization.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				Window.alert("Go To " + lbOrganizationName.getText());
			}
		});
		btnOrganiztionSetting.addMouseOverHandler(new MouseOverHandler() {
			
			@Override
			public void onMouseOver(MouseOverEvent event) {
				btnOrganiztionSetting.setVisible(true);
			}
		});
		btnOrganiztionSetting.addMouseOutHandler(new MouseOutHandler() {
			
			@Override
			public void onMouseOut(MouseOutEvent event) {
				btnOrganiztionSetting.setVisible(false);
			}
		});
		btnOrganiztionSetting.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				if(presenter != null)
					presenter.goToOrganizationDetail(organization);
			}
		});
		return organizationUI;
	}

	@UiHandler("btnNewOrganization")
	void onBtnNewOrganizationClick(ClickEvent event) {
		formOrganizationCreate.setVisible(true);
		txbOrganizationName.setFocus(true);
		txbOrganizationName.setText("");
		txbOrganizationDescription.setText("");
		Window.scrollTo(0, Window.getClientHeight());
	}
	
	@UiHandler("btnCreateOrganization")
	void onBtnCreateOrganizationClick(ClickEvent event) {
		System_Organization organization = new System_Organization();
		organization.setName(txbOrganizationName.getText());
		organization.setDescription(txbOrganizationDescription.getText());
		organization.setAdmin(Storage.getUserProfiles().getEmail());
		if(presenter != null)
			presenter.doCreateOrganization(organization);
	}

	@Override
	public void setPresenter(Presenter presenter) {
		this.presenter = presenter;
	}

	@Override
	public void setOrganizationList(List<System_Organization> organizationList) {
		this.organizationList = organizationList;
		if(this.organizationList == null) {
			htmlOrganizationForm.setHeight("250px");
			flowOrganizationList.setWidth("300px");
		}
		else {
			String htmlHeight = (250 + (this.organizationList.size() / 3) * 90) + "px";
			String flowWidth = (300 + this.organizationList.size() * 300) + "px";
			flowOrganizationList.setWidth(flowWidth);
			htmlOrganizationForm.setHeight(htmlHeight);
		}
		flowOrganizationList.clear();
		flowOrganizationList.add(absOrganizationNew);
		for(System_Organization o: this.organizationList)
			flowOrganizationList.add(renderOrganizationUI(o));
	}

	@Override
	public void clear() {
		formOrganizationCreate.setVisible(false);
	}
	
	HTMLPanel renderInviteRowUI(final Invite_Token token) {
		HTMLPanel InviteRowUi = new HTMLPanel("");
		InviteRowUi.setHeight("60px");
		InviteRowUi.setStyleName("OrganizationDetail-Obj12");
		Label inviteLabel = new Label("Bạn đã nhận được lời mời tham gia tổ chức " + token.getOrganizationName() + " - " + token.getOrganizationAdmin());
		inviteLabel.setWidth("70%");
		inviteLabel.setStyleName("OrganizationDetail-Obj14 font-blackTitle");
		InviteRowUi.add(inviteLabel);
		HorizontalPanel controlPanel = new HorizontalPanel();
		controlPanel.setHeight("35px");
		controlPanel.setStyleName("OrganizationDetail-Obj15");
		InviteRowUi.add(controlPanel);
		Label acceptInvite = new Label("Tham gia");
		acceptInvite.setSize("90px", "35px");
		acceptInvite.setStyleName("OrganizationDetail-Obj16 button-whiteStyle");
		controlPanel.add(acceptInvite);
		Label denyInvite = new Label("Bỏ qua");
		denyInvite.setSize("90px", "35px");
		denyInvite.setStyleName("OrganizationDetail-Obj16 button-whiteStyle");
		controlPanel.add(denyInvite);
		//Event handler
		acceptInvite.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				if(presenter != null)
					presenter.doAcceptInviteToken(token);
			}
		});
		return InviteRowUi;
	}

	@Override
	public void setInviteTokenList(List<Invite_Token> inviteTokenList) {
		htmlInviteTable.clear();
		if(inviteTokenList != null) {
			for(Invite_Token token: inviteTokenList)
				htmlInviteTable.add(renderInviteRowUI(token));
		}
	}
	
}
