package com.softlink.minitask.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import com.google.appengine.tools.development.testing.LocalTaskQueueTestConfig;
import com.googlecode.objectify.Key;
import com.softlink.minitask.server.DataRequestService;
import com.softlink.minitask.shared.Task_Project;

import static com.softlink.minitask.test.util.OfyService.factory;
import static com.softlink.minitask.test.util.OfyService.ofy;
import static org.junit.Assert.assertEquals;

public class ProjectDataServiceTest {
	private final LocalServiceTestHelper helper = new LocalServiceTestHelper(
			new LocalTaskQueueTestConfig(),
			new LocalDatastoreServiceTestConfig());

	@Before
	public void setUp() {
		factory().register(Task_Project.class);
		helper.setUp();
	}

	@After
	public void tearDow() {
		helper.tearDown();
	}

	@Test
	public void insertProject() throws Exception {
		Task_Project p = new Task_Project();
		p.setInitDate(new Date());
		p.setCreator("lent@itpro.vn");
		p.setName("GAE");
		DataRequestService dataService = new DataRequestService();
		Task_Project saveP = dataService.insertProject(p);
		assertEquals(p, saveP);
	}

	@Test
	public void retrieveProject() throws Exception {
		Long id = 123L;
		Task_Project p = new Task_Project();
		p.setId(id);
		p.setInitDate(new Date());
		p.setCreator("lent@itpro.vn");
		p.setName("GAE");
		ofy().save().entity(p).now();

		// case 1: project exits in database
		DataRequestService dataRequest = new DataRequestService();
		Task_Project findP = dataRequest.retrieveProject(id);
		assertEquals(findP.getId(), id);

		// case 2 project not exits in database
		Task_Project findP2 = dataRequest.retrieveProject(234L);
		assertEquals(findP2, null);

		// case 3 with parameter = null
		Task_Project findP3 = dataRequest.retrieveProject(null);
		assertEquals(findP3, null);
	}

	@Test
	public void retrieveProjects() throws Exception {
		autoGenerateProjects();
		DataRequestService dataRequest = new DataRequestService();
		List<Task_Project> result = dataRequest.retrieveProjects();
		assertEquals(result.size(), 10);
	}

	protected void autoGenerateProjects() {
		for (int i = 0; i < 10; i++) {
			Task_Project p = new Task_Project();
			p.setCreator("lent@itpro.vn");
			p.setName("Du an " + i);
			p.setManager("hoavq@itpro.vn");
			p.setInitDate(new Date());
			p.setUpdateDate(new Date());
			p.setVersion(1);
			ofy().save().entity(p).now();
		}
	}

	@Test
	public void retrieveChildProjects() throws Exception {

		DataRequestService dataRequest = new DataRequestService();
		// generate list project
		autoGenerateProjects();
		List<Task_Project> result = dataRequest.retrieveProjects();

		List<Long> listChilds = new ArrayList<Long>();
		if (result != null && !result.isEmpty()) {
			for (Task_Project task_Project : result) {
				if (task_Project != null)
					listChilds.add(task_Project.getId());
			}
		}
		// create parent project
		Task_Project saveP = new Task_Project();
		saveP.setName("Parent project");
		saveP.setListChildId(listChilds);
		Key<Task_Project> key = ofy().save().entity(saveP).now();
		Task_Project parentP = ofy().load().key(key).now();

		List<Task_Project> projectChilds = dataRequest
				.retrieveChildProjects(parentP.getListChildId());

		assertEquals(projectChilds.size(), 10);

	}

	@Test
	public void updateProject() throws Exception {
		// create new project
		Task_Project p = new Task_Project();
		p.setInitDate(new Date());
		p.setCreator("lent@itpro.vn");
		p.setName("GAE");
		DataRequestService dataService = new DataRequestService();
		Task_Project saveP = dataService.insertProject(p);

		// update project exits in database

		saveP.setName("GAE Ipro");
		boolean isUpdate = dataService.updateProject(saveP);
		assertEquals(isUpdate, true);

		// update project not exits in database
		boolean isUpdate1 = dataService.updateProject(new Task_Project());
		assertEquals(isUpdate1, false);
	}

	@Test
	public void removeProject() throws Exception {
		// create new project
		Task_Project p = new Task_Project();
		p.setInitDate(new Date());
		p.setCreator("lent@itpro.vn");
		p.setName("GAE");
		DataRequestService dataService = new DataRequestService();
		Task_Project saveP = dataService.insertProject(p);
		
		// delete project in database
		
		boolean isRemove = dataService.removeProject(saveP);
		assertEquals(isRemove, true);
		
		// delete project not exits in database
		boolean isRemove1 = dataService.removeProject(new Task_Project());
		assertEquals(isRemove1, true);
	}
}
