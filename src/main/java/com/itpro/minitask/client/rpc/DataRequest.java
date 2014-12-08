package com.itpro.minitask.client.rpc;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.itpro.minitask.shared.Task_Data;

@RemoteServiceRelativePath("data_request")
public interface DataRequest extends RemoteService {
	public Task_Data insertTask(Task_Data newTask);

	boolean removeTask(Task_Data task);

	Task_Data retrieveTask(Long id);

	boolean updateTask(Task_Data newTask);

	List<Task_Data> retrieveChildTasks(List<Long> listIdChild, Long parentId);

	List<Task_Data> retrieveTasks();
}
