package com.softlink.minitask.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.googlecode.objectify.ObjectifyService;
import com.softlink.minitask.shared.Task_Data;
import com.softlink.minitask.shared.Task_Project;

public class DataRegister extends RemoteServiceServlet{

	public DataRegister() {
		super();
		ObjectifyService.register(Task_Data.class);
		ObjectifyService.register(Task_Project.class);
	}

}
