package com.softlink.minitask.shared;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Unindex;

@SuppressWarnings("serial")
@Index
@Entity(name = "task_data")
public class Task_Data implements Serializable {
	public static final int TASK_STATUS_WORKING = 1;
	public static final int TASK_STATUS_NEW = 0;
	public static final int TASK_STATUS_FINISHED = 2;
	
	public static final int TASK_PRIORITY_LOW = 0;
	public static final int TASK_PRIORITY_HIGH = 1;
	public static final int TASK_PRIORITY_MEDIUM = 2;

	public static class CompDateInit implements Comparator<Task_Data> {

		private int mod = 1;

		public CompDateInit(boolean desc) {
			if (desc)
				mod = -1;
		}

		@Override
		public int compare(Task_Data o1, Task_Data o2) {

			return mod * o1.getInitDate().compareTo(o2.getInitDate());
		}

	}

	@com.googlecode.objectify.annotation.Id
	Long id;
	Long projectId;
	@Unindex
	List<Long> listChild;
	@Unindex
	Long parentId;
	@Unindex
	String name;
	@Unindex
	String description;
	int priority;
	String sender;
	String recipient;
	@Unindex
	Date initDate;
	@Unindex
	Date activityDate;
	@Unindex
	Date finishDate;
	@Unindex
	Date updateDate;
	@Unindex
	Date dueDate;
	@Unindex
	List<String> ccList = new ArrayList<String>();
	// [(0,new); (1,working); (2, finished)]
	int status;
	// [(0, open); (1,archive)]
	int state;
	boolean security;
	// {-100, delete}
	@Unindex
	int version = 0;

	public void setVersion(int version) {
		this.version = version;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public int getVersion() {
		return version;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	public List<Long> getListChild() {
		return listChild;
	}

	public void setListChild(List<Long> listChild) {
		this.listChild = listChild;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getRecipient() {
		return recipient;
	}

	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}

	public Date getInitDate() {
		return initDate;
	}

	public void setInitDate(Date initDate) {
		this.initDate = initDate;
	}

	public Date getActivityDate() {
		return activityDate;
	}

	public void setActivityDate(Date activityDate) {
		this.activityDate = activityDate;
	}

	public Date getFinishDate() {
		return finishDate;
	}

	public void setFinishDate(Date finishDate) {
		this.finishDate = finishDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public List<String> getCcList() {
		return ccList;
	}

	public void setCcList(List<String> ccList) {
		this.ccList = ccList;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public boolean isSecurity() {
		return security;
	}

	public void setSecurity(boolean security) {
		this.security = security;
	}

	@Override
	public boolean equals(Object o) {
		Task_Data task = (Task_Data) o;
		if (this.id.equals(task.getId()))
			return true;
		else
			return false;

	}

	public static List<String> autoGeneraEmails() {
		List<String> emails = new ArrayList<String>();
		emails.add("lent@itpro.vn");
		emails.add("antp@itpro.vn");
		emails.add("nguyenhue@itpro.vn");
		emails.add("hoavq@itpro.vn");
		return emails;
	}

	public static List<Task_Data> autoGenerateTasks() {
		List<String> emails = autoGeneraEmails();
		List<Task_Data> result = new ArrayList<Task_Data>();
		for (int i = 1; i <= 100; i++) {
			Task_Data t = new Task_Data();
			t.setName("Công việc thứ " + i);
			t.setDescription("Mô tả cho công việc thứ " + i);
			int randomEmail = (int) (Math.random() * (emails.size()));
			t.setSender(emails.get(randomEmail));
			t.setRecipient(emails.get(randomEmail));
			Long id = (long) i;
			t.setId(id);
			t.setUpdateDate(new Date());
			t.setInitDate(new Date());
			t.setDueDate( new Date());
			if (i % 2 == 1)
				t.setSecurity(true);
			else
				t.setSecurity(false);

			int randomStatus = (int) (Math.random() * 3);
			switch (randomStatus) {
			case 0:
				t.setStatus(0);
				break;
			case 1:
				t.setStatus(1);
				t.setActivityDate(new Date());
				break;
			case 2:
				t.setStatus(2);
				t.setActivityDate(new Date());
				t.setFinishDate(new Date());
				break;
			default:
				break;
			}
			t.setState(0);
			int randomPriority = (int) (Math.random() * 2);
			t.setPriority(randomPriority);
			List<String> listCC = new ArrayList<String>();
			listCC.add("lent@itpro.vn");
			listCC.add(emails.get(randomEmail));
			t.setCcList(listCC);
			t.setVersion(t.getVersion() + 1);
			result.add(t);
		}

		return result;
	}

	// public enum TaskStatus {
	// New(0), Working(1), Finished(2), Close(3), Cancel(4);
	// private int status;
	//
	// public int getStatus() {
	// return status;
	// }
	//
	// private TaskStatus(int status) {
	// this.status = status;
	// }
	// }
	// public void updateTask(Task_Data newData) {
	// this.activityDate = newData.getActivityDate();
	// this.finishDate = newData.getFinishDate();
	// this.description = newData.getDescription();
	// this.dueDate = newData.getDueDate();
	// this.name = newData.getName();
	// this.projectId = newData.getProjectId();
	// this.listChild = newData.getListChild();
	// this.priority = newData.getPriority();
	// this.recipient = newData.getRecipient();
	// this.security = newData.isSecurity();
	// this.sender = newData.getSender();
	// this.status = newData.getStatus();
	// this.ccList = newData.getCcList();
	// this.version = newData.getVersion();
	// this.parentId = newData.getParentId();
	//
	// }
}
