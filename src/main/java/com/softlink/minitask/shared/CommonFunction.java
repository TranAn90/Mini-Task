package com.softlink.minitask.shared;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.resources.client.ImageResource;
import com.softlink.minitask.client.AppConstants;
import com.softlink.minitask.client.view.desktop.ui.CSSImageResource;

public class CommonFunction {
	public boolean isBlank(String s) {
		return (s == null) || (s.trim().length() == 0);
	}

	final int TASK_PRIORITY_LOW = Task_Data.TASK_PRIORITY_LOW;
	final int TASK_PRIORITY_HIGH = Task_Data.TASK_PRIORITY_HIGH;
	final int TASK_PRIORITY_MEDIUM = Task_Data.TASK_PRIORITY_MEDIUM;
	public static final String dateString = "dd-MM-yyyy";
	public static final DateTimeFormat formatDate = DateTimeFormat
			.getFormat(dateString);

	public ImageResource getImgPriority(Task_Data object,
			CSSImageResource cSSImageResource) {
		ImageResource img;
		if (object != null) {
			if (object.getPriority() == TASK_PRIORITY_HIGH) {
				img = cSSImageResource.imgPriority_Hight();
			} else if (object.getPriority() == TASK_PRIORITY_MEDIUM) {
				img = cSSImageResource.imgPriority_Medium();
			} else {
				img = cSSImageResource.imgPriority_Low();
			}

		} else {
			img = null;
		}
		return img;
	}

	final int TASK_STATUS_NEW = Task_Data.TASK_STATUS_NEW;
	final int TASK_STATUS_WORKING = Task_Data.TASK_STATUS_WORKING;
	final int TASK_STATUS_FINISHED = Task_Data.TASK_STATUS_FINISHED;
	final AppConstants appConstants = GWT.create(AppConstants.class);

	public String getTextTaskStatus(int status) {
		String stStatus = null;
		switch (status) {
		case TASK_STATUS_NEW:
			stStatus = appConstants.TaskStatusNew();

			break;
		case TASK_STATUS_WORKING:
			stStatus = appConstants.TaskStatusWorking();

			break;
		case TASK_STATUS_FINISHED:
			stStatus = appConstants.TaskStatusFinished();

			break;

		default:
			break;
		}
		return stStatus;
	}
}
