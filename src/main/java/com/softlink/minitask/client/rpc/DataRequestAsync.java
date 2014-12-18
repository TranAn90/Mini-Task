package com.softlink.minitask.client.rpc;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.softlink.minitask.shared.Task_Data;
import com.softlink.minitask.shared.Task_Project;

public interface DataRequestAsync {
	void insertTask(Task_Data newTask, AsyncCallback<Task_Data> callback);

	void removeTask(Task_Data task, AsyncCallback<Boolean> callback);

	void retrieveTask(Long id, AsyncCallback<Task_Data> callback);

	void updateTask(Task_Data newTask, AsyncCallback<Boolean> callback);

	void retrieveChildTasks(List<Long> listIdChild, Long parentId,
			AsyncCallback<List<Task_Data>> callback);

	void retrieveTasks(AsyncCallback<List<Task_Data>> callback);

	void insertProject(Task_Project newProject,
			AsyncCallback<Task_Project> callback);

	void retrieveProject(Long id, AsyncCallback<Task_Project> callback);

	void retrieveProjects(AsyncCallback<List<Task_Project>> callback);

	void retrieveChildProjects(List<Long> idChilds,
			AsyncCallback<List<Task_Project>> callback);

	void updateProject(Task_Project newData, AsyncCallback<Boolean> callback);

	void removeProject(Task_Project project, AsyncCallback<Boolean> callback);

}
