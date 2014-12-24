package com.softlink.minitask.client;

import com.google.gwt.i18n.client.Constants;

public interface AppConstants extends Constants {

	 // for task
	String ViewTaskName();
	String ViewProjectName();
	String ViewTaskSender();
	String ViewTaskRecipient();
	String ViewTaskPriority();
	String ViewTaskParentId();
	String ViewTaskStatus();
	String ViewDueDate();
	String ViewDescription();
	String ViewTaskActivityDate();
	String ViewFinishDate();
	String ViewTaskSecurity();
	
	// for status task
	String TaskStatusNew();
	String TaskStatusWorking();
	String TaskStatusFinished();
	
	
	// for my task
	String EmptryLabel();
	String TaskListViewHeaderSender();
	String TaskListViewHeaderRecipient();
	
	
	// for project
	String CreateProjectDialogHeader();
	String ViewProjectManager();
	String CreateProjectDialogButtonAddMember();
	String CreateProjectDialogShowMore();
	
	String ViewInitDate();
	String ViewProjectStartDate();
	String ViewProjectEndDate();
	
	String ButtonTextSave();
	String ButtonTextSaveContinue();
	String ButtonTitleReload();
	String ButtonTitleClose();
	String ButtonTitleZoomOutDes();
	String CreateTaskDialogHeaderCreate();
	String ViewProjectSub();
	String CreateTaskDialogSendToEmail();
	String CreateTaskDialogButtonTextAddCC();
	String CreateTaskDialogShowMore();
	String ZoomOutDescriptionButtonTextSaveDes();
	String ZoomOutDescriptionButtonTextReload();
	String ZoomOutDescriptionLabelTextOr();
	String CreateProjectDialogHeaderZoomOutDes();
	String CreateTaskDialogHeaderZoomOutDes();
	String TaskPriorityLow();
	String TaskPriorityMedium();
	String TaskPriorityHight();
	String AppNameMiniTask();
	String ButtonTextBack();
	String OrganizationDetailLbNameOrganization();
	String OrganizationDetailTabNameInformation();
	String OrganizationDetailTabNameMember();
	String OrganizationDetailLbJoin();
	String OrganizationDetailLbAddUser();
	String OrganizationDetailBtTextInviteUser();
	String UserRoleAdmin();
	String ButtonTextCancel();
	String OrganizationPagelbOptionOrganization();
	String OrganizationPagelbCreate();
	String OrganizationPagebtTextCreateOrganization();
	String ButtonTextSetting();
	String OrganizationPagelbInvite();
	String OrganizationPagelbdenyInvite();
	String ButtonTextRemove();
	String OrganizationDetailbtRemoveTextLeave();
	String OrganizationDetaillbWaitingJoin();
	String LoginPagelbWelcome();
	String LoginPagelbStart();
	String LoginPagelbLogin();
	String ViewProjectListMember();
	String OptionLanguagelbHeader();
	String OptionLanguagebtEnglish();
	String OptionLanguagebtVietNamese();
}
