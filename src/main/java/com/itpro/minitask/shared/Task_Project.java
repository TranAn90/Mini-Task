package com.itpro.minitask.shared;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

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
	List<String> listMember;
	@Unindex
	boolean type;
	@Unindex
	int version = 0;

}
