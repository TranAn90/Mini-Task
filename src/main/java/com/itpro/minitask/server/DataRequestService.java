package com.itpro.minitask.server;

import java.util.Date;

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

	boolean isRemove = false;

	@Override
	public boolean removeTask(Task_Data removeTask) {
		return false;
	}

	@Override
	public Task_Data retrieveTask(Long id) {
		Task_Data findTask = null;
		if (id == null)
			findTask = null;
		else
			findTask = ofy().load().type(Task_Data.class).id(id).now();
		return findTask;
	}

}
