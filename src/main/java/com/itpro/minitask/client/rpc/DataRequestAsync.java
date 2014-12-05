package com.itpro.minitask.client.rpc;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.itpro.minitask.shared.Task_Data;

public interface DataRequestAsync {
	void insertTask(Task_Data newtask, AsyncCallback<Task_Data> callback);

	void removeTask(Task_Data removeTask, AsyncCallback<Boolean> callback);

	void retrieveTask(Long id, AsyncCallback<Task_Data> callback);
}
