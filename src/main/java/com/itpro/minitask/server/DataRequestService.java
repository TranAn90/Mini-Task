package com.itpro.minitask.server;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.googlecode.objectify.Key;
import com.itpro.minitask.client.rpc.DataRequest;
import com.itpro.minitask.shared.Task_Data;

import static com.googlecode.objectify.ObjectifyService.ofy;

@SuppressWarnings("serial")
public class DataRequestService extends RemoteServiceServlet implements
		DataRequest {
	/**
	 * 
	 * @param task
	 *            will save in database
	 * @return null if parentId invalid, task if save task success
	 */
	Task_Data exportData = null;

	public Task_Data insertTask(Task_Data newtask) {
		newtask.setInitDate(new Date());
		newtask.setUpdateDate(new Date());
		Key<Task_Data> key = ofy().save().entity(newtask).now();
		exportData = ofy().load().key(key).now();
		return exportData;
	}

	/**
	 * get task by id
	 */
	@Override
	public Task_Data retrieveTask(Long id) {
		Task_Data findTask = null;
		if (id == null)
			findTask = null;
		else
			findTask = ofy().load().type(Task_Data.class).id(id).now();
		return findTask;
	}

	/**
	 * 
	 * @param parent
	 *            Task_Data is parent
	 * @return list sub Task_Data
	 */
	public List<Task_Data> getTaskChilds(Task_Data parent) {
		List<Task_Data> taskChilds = new ArrayList<Task_Data>();
		if (parent.getListChild() != null && !parent.getListChild().isEmpty()) {
			for (Long id : parent.getListChild()) {
				Task_Data findTask = retrieveTask(id);
				if (findTask != null)
					taskChilds.add(findTask);
			}
		}
		return taskChilds;
	}

	boolean isRemove = false;

	/**
	 * @param task
	 *            Task_Data will remove
	 * @return true(remove success, not found task), false( remove fail)
	 */
	@Override
	public boolean removeTask(Task_Data task) {
		if (task != null && task.getId() != null) {
			Task_Data findTask = retrieveTask(task.getId());
			if (findTask != null) {
				ofy().delete().entities(findTask);
				isRemove = true;
			} else
				isRemove = true;

		} else
			isRemove = true;
		return isRemove;
	}

	boolean isUpdate = false;
	final int TASK_STATUS_WORKING = Task_Data.TASK_STATUS_WORKING;
	final int TASK_STATUS_NEW = Task_Data.TASK_STATUS_NEW;
	final int TASK_STATUS_FINISHED = Task_Data.TASK_STATUS_FINISHED;

	@Override
	public boolean updateTask(Task_Data newTask) {
		if (newTask != null && newTask.getId() != null) {
			Task_Data oldTask = retrieveTask(newTask.getId());
			if (oldTask == null)
				isUpdate = false;
			else {
				int newStatus = newTask.getStatus();
				switch (newStatus) {
				case TASK_STATUS_WORKING:
					if (newTask.getActivityDate() != null)
						newTask.setActivityDate(new Date());
					break;
				case TASK_STATUS_FINISHED:
					newTask.setActivityDate(new Date());
					break;
				default:
					break;
				}
				newTask.setUpdateDate(new Date());
				ofy().save().entity(newTask);
				isUpdate = true;
			}
		} else
			isUpdate = false;
		return isUpdate;
	}

	@Override
	public List<Task_Data> retrieveChildTasks(List<Long> listIdChild,
			Long parentId) {
		List<Long> listIdNotFound = new ArrayList<Long>();

		List<Task_Data> taskChilds = new ArrayList<Task_Data>();
		if (listIdChild != null && !listIdChild.isEmpty()) {
			for (Long id : listIdChild) {
				Task_Data findTask = retrieveTask(id);
				if (findTask != null)
					taskChilds.add(findTask);
				else
					listIdNotFound.add(id);
			}
			if (!listIdNotFound.isEmpty()) {
				for (Long idNotFound : listIdNotFound) {
					if (listIdChild.contains(idNotFound))
						listIdChild.remove(idNotFound);
				}
				// update task parent
				Task_Data findParent = retrieveTask(parentId);
				findParent.setListChild(listIdChild);
				updateTask(findParent);
			}
		}
		return taskChilds;

	}

	List<Task_Data> result;

	@Override
	public List<Task_Data> retrieveTasks() {
		result = new ArrayList<Task_Data>();
		List<Task_Data> list = ofy().load().type(Task_Data.class).list();
		if (!list.isEmpty())
			result.addAll(list);
		return result;
	}
}
