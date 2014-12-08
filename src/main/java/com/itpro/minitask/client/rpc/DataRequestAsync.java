package com.itpro.minitask.client.rpc;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.itpro.minitask.shared.Task_Data;

public interface DataRequestAsync {
	void insertTask(Task_Data newTask, AsyncCallback<Task_Data> callback);

	void removeTask(Task_Data task, AsyncCallback<Boolean> callback);

	void retrieveTask(Long id, AsyncCallback<Task_Data> callback);

	void updateTask(Task_Data newTask, AsyncCallback<Boolean> callback);

	void retrieveChildTasks(List<Long> listIdChild, Long parentId,
			AsyncCallback<List<Task_Data>> callback);

	void retrieveTasks(AsyncCallback<List<Task_Data>> callback);
}
