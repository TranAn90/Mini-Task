package com.softlink.minitask.client.view.desktop.ui;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.Event.NativePreviewEvent;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.DisclosurePanel;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.datepicker.client.DateBox;
import com.softlink.minilib.shared.System_Organization;
import com.softlink.minitask.client.AppConstants;
import com.softlink.minitask.client.AppController.Storage;
import com.softlink.minitask.client.activity.ui.CreateProjectDialogActivity;
import com.softlink.minitask.shared.Task_Project;
import com.softlink.minitask.shared.UserProfiles;

public class CreateProjectDialog extends DialogBox {

	private static Binder uiBinder = GWT.create(Binder.class);

	interface Binder extends UiBinder<Widget, CreateProjectDialog> {
	}

	@UiField
	TextBox name;
	@UiField
	ListBox manager;
	@UiField
	DateBox dueDate;
	@UiField
	TextArea description;
	@UiField
	Image btZoomOut;
	@UiField
	DateBox startDate;
	@UiField
	DateBox endDate;
	@UiField
	Anchor btAddMember;
	@UiField
	FlowPanel tableMember;
	@UiField
	Label btSave;
	@UiField
	Label btSaveContinue;
	@UiField
	Image btReload;
	@UiField
	Image btClose;
	@UiField
	DisclosurePanel disclosurePanel;
	@UiField
	Label header;
	@UiField
	Label lbNameProject;
	@UiField
	Label lbManager;
	@UiField
	Label lbDescription;
	@UiField
	Label lbDueDate;
	@UiField
	Label lbStartDate;
	@UiField
	Label lbEndDate;
	@UiField
	Label btnMemberAdd;
	@UiField
	HTMLPanel htmlMembersList;

	public interface Presenter {
		void createProject(Task_Project project);
	}

	private Presenter presenter;

	private final AppConstants CONSTANTS = GWT.create(AppConstants.class);

	private SelectMembers selectMembersDialog;
	private List<String> organizationMembers = new ArrayList<String>();
	private List<String> projectMembers = new ArrayList<String>();

	private ZoomOutDescription zoomOut;

	public void getInfo() {
		UserProfiles profile = Storage.getUserProfiles();
		System_Organization organization = profile.findOrganization(profile
				.getOrganizationCurrently());
		organizationMembers.clear();
		manager.clear();
		organizationMembers.add(organization.getAdmin());
		manager.addItem(organization.getAdmin());
		for (String user : organization.getUserList()) {
			organizationMembers.add(user);
			manager.addItem(user);
		}
		manager.setSelectedIndex(organizationMembers.indexOf(profile.getEmail()));
	}

	public void clear() {
		name.setText(null);
		description.setText(null);
		dueDate.setValue(null);
		startDate.setValue(null);
		endDate.setValue(null);
		tableMember.clear();
		projectMembers.clear();
		htmlMembersList.clear();
	}

	public CreateProjectDialog() {
		setWidget(uiBinder.createAndBindUi(this));

		SetTextForm();
		setStyleName("frame_dialogBoxClean");
		disclosurePanel.setOpen(false);
		setGlassEnabled(true);
		setAnimationEnabled(true);

		presenter = new CreateProjectDialogActivity();

		selectMembersDialog = new SelectMembers(new SelectMembers.Listener() {
			@Override
			public void setListSelectMember(List<String> listMembers) {
				projectMembers.clear();
				projectMembers.addAll(listMembers);
				htmlMembersList.clear();
				for (String member : projectMembers)
					htmlMembersList.add(renderMemberUI(member));
				center();
			}
		});

		manager.addChangeHandler(new ChangeHandler() {
			@Override
			public void onChange(ChangeEvent event) {
				String manager1 = manager.getItemText(manager
						.getSelectedIndex());
				projectMembers.remove(manager1);
				htmlMembersList.clear();
				for (String member : projectMembers)
					htmlMembersList.add(renderMemberUI(member));
			}
		});
		
		zoomOut = new ZoomOutDescription(description.getText(), CONSTANTS.CreateProjectDialogHeaderZoomOutDes(),
				new ZoomOutDescription.Listener() {
					@Override
					public void onClickanSave(String description1) {
						description.setText(description1);
					}
				});
	}

	@Override
	protected void onPreviewNativeEvent(NativePreviewEvent event) {
		super.onPreviewNativeEvent(event);
		switch (event.getTypeInt()) {
		case Event.ONKEYDOWN:
			if (event.getNativeEvent().getKeyCode() == KeyCodes.KEY_ESCAPE) {
				hide();
			}
			break;
		}
	}
	
	@UiHandler("btZoomOut")
	void onBtZoomOutClick(ClickEvent event) {
		int top = 10;
		int left = this.getAbsoluteLeft() + 625;
		zoomOut.setPopupPosition(left, top);
		zoomOut.setDescription(description.getText());
		zoomOut.show();
	}

	@UiHandler("btAddMember")
	void onBtAddMemberClick(ClickEvent event) {
	}

	@UiHandler("btSave")
	void onBtSaveClick(ClickEvent event) {
		Task_Project project = new Task_Project();
		project.setName(name.getText());
		project.setDescription(description.getText());
		project.setCreator(Storage.getUserProfiles().getEmail());
		project.setManager(manager.getItemText(manager.getSelectedIndex()));
		project.setDueDate(dueDate.getValue());
		project.setListMember(projectMembers);
		presenter.createProject(project);
		hide();
	}

	@UiHandler("btSaveContinue")
	void onBtSaveContinueClick(ClickEvent event) {
	}

	@UiHandler("btReload")
	void onBtReloadClick(ClickEvent event) {
		clear();
	}

	@UiHandler("btClose")
	void onBtCloseClick(ClickEvent event) {
		hide();
	}

	@UiHandler("btnMemberAdd")
	void onBtnMemberAddClick(ClickEvent event) {
		int top = this.getAbsoluteTop();
		int left = this.getAbsoluteLeft() + 625;
		selectMembersDialog.setPopupPosition(left, top);
		selectMembersDialog.show();
		List<String> cMembers = new ArrayList<String>();
		cMembers.addAll(organizationMembers);
		cMembers.remove(manager.getItemText(manager.getSelectedIndex()));
		selectMembersDialog.setListMembers(cMembers, projectMembers);
	}

	private void SetTextForm() {
		header.setText(CONSTANTS.CreateProjectDialogHeader());
		lbNameProject.setText(CONSTANTS.ViewProjectName());
		lbManager.setText(CONSTANTS.ViewProjectManager());
		lbDescription.setText(CONSTANTS.ViewDescription());
		lbDueDate.setText(CONSTANTS.ViewDueDate());
		lbStartDate.setText(CONSTANTS.ViewProjectStartDate());
		lbEndDate.setText(CONSTANTS.ViewProjectEndDate());
		btAddMember.setText(CONSTANTS.CreateProjectDialogButtonAddMember());
		disclosurePanel.getHeaderTextAccessor().setText(
				CONSTANTS.CreateProjectDialogShowMore());
		btSave.setText(CONSTANTS.ButtonTextSave());
		btSaveContinue.setText(CONSTANTS.ButtonTextSaveContinue());
		btReload.setTitle(CONSTANTS.ButtonTitleReload());
		btClose.setTitle(CONSTANTS.ButtonTitleClose());
		btZoomOut.setTitle(CONSTANTS.ButtonTitleZoomOutDes());
	}

	private HTMLPanel renderMemberUI(final String member) {
		final HTMLPanel memberUI = new HTMLPanel("");
		memberUI.setHeight("25px");
		memberUI.setStyleName("createProjectDialog_Obj11");
		Label memberName = new Label(member);
		memberName.setStyleName("createProjectDialog_Obj13");
		memberUI.add(memberName);
		AbsolutePanel absClose = new AbsolutePanel();
		absClose.setSize("20px", "100%");
		absClose.setStyleName("createProjectDialog_Obj12");
		memberUI.add(absClose);
		Label lbClose = new Label("X");
		Label btnClose = new Label();
		btnClose.setSize("100%", "100%");
		absClose.add(lbClose, 5, 3);
		absClose.add(btnClose, 0, 0);
		btnClose.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				htmlMembersList.remove(memberUI);
				projectMembers.remove(member);
				center();
			}
		});
		return memberUI;
	}

}
