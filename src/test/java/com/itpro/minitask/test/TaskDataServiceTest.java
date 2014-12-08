package com.itpro.minitask.test;

import static com.itpro.minitask.test.util.OfyService.factory;
import static com.itpro.minitask.test.util.OfyService.ofy;
import static org.junit.Assert.assertEquals;

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
import com.itpro.minitask.server.DataRequestService;
import com.itpro.minitask.shared.Task_Data;

public class TaskDataServiceTest {

	private final LocalServiceTestHelper helper = new LocalServiceTestHelper(
			new LocalTaskQueueTestConfig(),
			new LocalDatastoreServiceTestConfig());

	@Before
	public void setUp() {
		factory().register(Task_Data.class);
		helper.setUp();
	}

	@After
	public void tearDown() {
		helper.tearDown();

	}

	/**
	 * create new task
	 * 
	 * @throws Exception
	 */
	@Test
	public void insertTask() throws Exception {
		DataRequestService dataRequest = new DataRequestService();
		Task_Data input = createTask();
		Task_Data output = dataRequest.insertTask(input);
		assertEquals(input, output);
	}

	protected Task_Data createTask() {
		Task_Data t = new Task_Data();
		t.setActivityDate(new Date());
		List<String> ccList = new ArrayList<String>();
		ccList.add("lent@itpro.vn");
		t.setCcList(ccList);
		t.setDescription("Thêm mô tả cho công việc");
		t.setDueDate(new Date());
		t.setFinishDate(new Date());
		t.setInitDate(new Date());
		List<Long> listChild = new ArrayList<Long>();
		listChild.add(12L);
		t.setListChild(listChild);
		t.setName("Tên công việc");
		t.setParentId(null);
		t.setPriority(1);
		t.setProjectId(13L);
		t.setRecipient("lent@itpro.vn");
		t.setSender("nguyenle@gmail.com");
		t.setSecurity(false);
		t.setState(0);
		t.setStatus(0);
		t.setUpdateDate(new Date());
		t.setVersion(t.getVersion() + 1);
		return t;
	}

	/**
	 * test get task by Id success
	 * 
	 * @throws Exception
	 */
	Long idTask = 123L;

	@Test
	public void retrieveTask() throws Exception {
		Task_Data triv = new Task_Data();
		triv.setId(idTask);
		triv.setName("hehe");
		ofy().save().entity(triv).now();
		DataRequestService dataRequest = new DataRequestService();
		Task_Data findTask = dataRequest.retrieveTask(idTask);
		assertEquals(findTask.getId(), idTask);
	}

	/**
	 * @param parent
	 *            Task_Data
	 * @return List<Task_Data> all task child
	 */

	Long idParent = 3L;
	Long idSub1 = 1l;
	Long idSub2 = 2L;

	private Task_Data autoGeneratedTask() {
		Task_Data parent = new Task_Data();
		parent.setId(idParent);
		parent.setName("parent");
		Key<Task_Data> keyP = ofy().save().entity(parent).now();
		Task_Data findP = ofy().load().key(keyP).now();

		Task_Data sub1 = new Task_Data();
		sub1.setId(idSub1);
		sub1.setName("sub 1");
		sub1.setParentId(findP.getId());
		Key<Task_Data> key1 = ofy().save().entity(sub1).now();
		Task_Data findSub1 = ofy().load().key(key1).now();

		Task_Data sub2 = new Task_Data();
		sub2.setId(idSub2);
		sub2.setName("sub 2");
		sub2.setParentId(findP.getId());
		Key<Task_Data> key2 = ofy().save().entity(sub2).now();
		Task_Data findSub2 = ofy().load().key(key2).now();

		List<Long> listChildren = new ArrayList<Long>();
		listChildren.add(findSub1.getId());
		listChildren.add(findSub2.getId());

		findP.setListChild(listChildren);
		Key<Task_Data> keyParent = ofy().save().entity(findP).now();
		return ofy().load().key(keyParent).now();
	}

	/**
	 * @param parent
	 *            Task_Data has listChild task
	 * @return true if remove success, false if remove fail
	 */
	@Test
	public void removeTask_Child() throws Exception {
		Task_Data task = autoGeneratedTask();
		DataRequestService dataRequest = new DataRequestService();
		boolean result = dataRequest.removeTask(task);
		assertEquals(true, result);
	}

	/**
	 * @param parent
	 *            Task_Data no has child
	 * @return true if remove success, false if remove fail
	 */
	@Test
	public void removeTask_NoChild() throws Exception {
		DataRequestService dataRequest = new DataRequestService();
		Task_Data task1 = new Task_Data();
		task1.setId(4L);
		task1.setName("task1");
		Key<Task_Data> key2 = ofy().save().entity(task1).now();
		Task_Data findTask = ofy().load().key(key2).now();
		boolean result = dataRequest.removeTask(findTask);
		assertEquals(true, result);
	}

	/**
	 * test function updateTask in server
	 * 
	 * @throws Exception
	 */
	@Test
	public void updateTask() throws Exception {
		// create new task and save in database
		Task_Data task1 = new Task_Data();
		task1.setId(4L);
		task1.setName("task1");
		task1.setVersion(1);
		task1.setStatus(Task_Data.TASK_STATUS_NEW);
		task1.setInitDate(new Date());
		task1.setUpdateDate(new Date());
		Key<Task_Data> key2 = ofy().save().entity(task1).now();
		Task_Data findTask = ofy().load().key(key2).now();

		// update task
		DataRequestService dataRequest = new DataRequestService();
		findTask.setStatus(Task_Data.TASK_STATUS_WORKING);
		findTask.setVersion(2);
		findTask.setName("name");
		boolean isUpdate = dataRequest.updateTask(findTask);

		// compare result
		assertEquals(isUpdate, true);

		Task_Data result = ofy().load().type(Task_Data.class)
				.id(findTask.getId()).now();

		assertEquals(result.getId(), findTask.getId());
		assertEquals(findTask.getStatus(), result.getStatus());
		assertEquals(findTask.getVersion(), result.getVersion());
		assertEquals(findTask.getName(), result.getName());
		assertEquals(result.getName(), "name");
		assertEquals(result.getActivityDate(), findTask.getActivityDate());

		Task_Data dataNull = new Task_Data();
		boolean isUpdateNull = dataRequest.updateTask(dataNull);
		assertEquals(isUpdateNull, false);
	}

	@Test
	public void retrieveTasks() {
		autoGeneratedTask();
		DataRequestService dataRequest = new DataRequestService();
		List<Task_Data> result = dataRequest.retrieveTasks();
		assertEquals(result.size(), 3);
	}

	@Test
	public void retrieveChildTasks() {
		// create task
		Task_Data parentTask = autoGeneratedTask();
		List<Long> listChildTest = parentTask.getListChild();
		listChildTest.add(12L);
		ofy().save().entity(parentTask);
		
		DataRequestService dataRequest = new DataRequestService();
		// get list
		List<Task_Data> result = dataRequest.retrieveChildTasks(
				parentTask.getListChild(), parentTask.getId());
		// check data
		Task_Data findParent = ofy().load().type(Task_Data.class).id(parentTask.getId()).now();
		assertEquals(findParent.getListChild().size(), 2);
		assertEquals(result.size(), 2);
	}
	// Long idTask = 123L;
	//
	// @Test
	// public void simpleQueryWorks() throws Exception {
	// factory().register(Task_Data.class);
	//
	// Task_Data triv = new Task_Data();
	//
	// triv.setId(idTask);
	// triv.setName("hehe");
	// ofy().save().entity(triv).now();
	//
	// Query q = new Query("task_data");
	// PreparedQuery pq = ds().prepare(q);
	// List<Entity> stuff = pq.asList(FetchOptions.Builder.withDefaults());
	// assert stuff.size() == 1;
	//
	// int count = 0;
	// for (@SuppressWarnings("unused")
	// Task_Data fetched : ofy().load().type(Task_Data.class)) {
	// count++;
	// }
	// assert count == 1;
	//
	// // Task_Data findTask =
	// // ofy().load().type(Task_Data.class).id(idTask).now();
	// // assertEquals(findTask.getId(), idTask);
	// DataRequestService dataRequest = new DataRequestService();
	// Task_Data findTask = dataRequest.retrieveTask(idTask);
	// assertEquals(findTask.getId(), idTask);
	// }

}