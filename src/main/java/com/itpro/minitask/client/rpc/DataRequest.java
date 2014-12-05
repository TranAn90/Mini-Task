package com.itpro.minitask.client.rpc;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.itpro.minitask.shared.Task_Data;

@RemoteServiceRelativePath("data_request")
public interface DataRequest extends RemoteService {
	public Task_Data insertTask(Task_Data newtask);

	boolean removeTask(Task_Data removeTask);

	Task_Data retrieveTask(Long id);
}
