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
	
	String ViewInitDate();
	String ViewProjectStartDate();
	String ViewProjectEndDate();
	String CreateProjectDialogButtonAddMember();
	String CreateProjectDialogShowMore();
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
}
