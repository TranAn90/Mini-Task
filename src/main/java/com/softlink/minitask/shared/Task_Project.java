package com.softlink.minitask.shared;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Unindex;
import com.googlecode.objectify.condition.IfNotEmpty;
import com.googlecode.objectify.condition.IfNotNull;

@Index
@SuppressWarnings("serial")
@Entity(name = "task_project")
public class Task_Project implements Serializable {
	@Id
	Long id;
	@Unindex
	List<Long> listTaskId;
	@Unindex
	List<Long> listChildId;
	@Index
	String name;
	@Unindex
	String description;
	@Index
	String creator;
	@Index({ IfNotNull.class, IfNotEmpty.class })
	String manager;
	@Unindex
	Date initDate;
	@Unindex
	Date startDate;
	@Unindex
	Date endDate;
	@Unindex
	Date dueDate;
	@Unindex
	Date updateDate;
	@Unindex
	int status;
	@Unindex
	List<String> listMember = new ArrayList<String>();
	@Unindex
	boolean type;
	@Unindex
	int version = 0;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Long> getListTaskId() {
		return listTaskId;
	}

	public void setListTaskId(List<Long> listTaskId) {
		this.listTaskId = listTaskId;
	}

	public List<Long> getListChildId() {
		return listChildId;
	}

	public void setListChildId(List<Long> listChildId) {
		this.listChildId = listChildId;
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

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}

	public Date getInitDate() {
		return initDate;
	}

	public void setInitDate(Date initDate) {
		this.initDate = initDate;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public List<String> getListMember() {
		return listMember;
	}

	public void setListMember(List<String> listMember) {
		this.listMember = listMember;
	}

	public boolean isType() {
		return type;
	}

	public void setType(boolean type) {
		this.type = type;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	@Override
	public boolean equals(Object o) {
		Task_Project p = (Task_Project) o;
		if (this.id.equals(p.getId()))
			return true;
		else
			return false;
	}

	public static List<String> emails = Task_Data.autoGeneraEmails();

	public static List<Task_Project> autoGenerateProjects() {
		List<Task_Project> results = new ArrayList<Task_Project>();
		for (int i = 1; i <= 50; i++) {
			Long id = (long) i;
			Task_Project p = new Task_Project();

			p.setId(id);
			p.setName("Dự án " + i);
			int randomEmail = (int) (Math.random() * emails.size());
			p.setCreator(emails.get(randomEmail));
			if (i % 2 == 0) {
				p.setManager(emails.get(randomEmail));
				p.setDueDate(new Date());
			}
			p.setInitDate(new Date());
			p.setUpdateDate(new Date());
			p.setDescription("Mô tả cho dự án thứ " + i);
			p.setVersion(p.getVersion() + 1);
			if (i % 3 == 0) {
				Set<String> members = new HashSet<String>();
				members.add(p.getCreator());
				int randomMember = (int) (Math.random() * emails.size());
				members.add(emails.get(randomMember));
				p.setListMember(new ArrayList<String>(members));
			}
			results.add(p);
		}
		return results;
	}
}
