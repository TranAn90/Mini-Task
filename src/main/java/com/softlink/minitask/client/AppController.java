package com.softlink.minitask.client;

import com.google.gwt.activity.shared.ActivityManager;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.place.shared.PlaceHistoryHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.SimplePanel;
import com.softlink.minilib.client.rpc.SystemService;
import com.softlink.minilib.client.rpc.SystemServiceAsync;
import com.softlink.minitask.client.mvp.AppActivityMapper;
import com.softlink.minitask.client.mvp.AppPlaceHistoryMapper;
import com.softlink.minitask.client.place.WelcomePlace;
import com.softlink.minitask.client.rpc.DataRequest;
import com.softlink.minitask.client.rpc.DataRequestAsync;
import com.softlink.minitask.client.rpc.UserRequest;
import com.softlink.minitask.client.rpc.UserRequestAsync;
import com.softlink.minitask.shared.LocalData;
import com.softlink.minitask.shared.UserProfiles;

public class AppController {

	private ClientFactory clientFactory;
	private EventBus eventBus;
	
	public static class Storage {
		static UserProfiles userProfiles = new UserProfiles();
		
		static LocalData localData = new LocalData();
		
		public static UserProfiles getUserProfiles() {
			return userProfiles;
		}
		
		public static LocalData getLocalData() {
			return localData;
		}
	}
	
	public static final SystemServiceAsync systemService = SystemService.Util.getInstance();
	public static final UserRequestAsync userRequest = GWT.create(UserRequest.class);
	public static final DataRequestAsync dataRequest = GWT.create(DataRequest.class);

	private void EventManager() {
//		eventBus.addHandler(InLoginPageEvent.TYPE, new InLoginPageEvent.Handler() {
//			@Override
//			public void inPlace(InLoginPageEvent event) {
//				clientFactory.getContainer().inLoginPage();
//			}
//		});
//		eventBus.addHandler(InOrganizationPageEvent.TYPE, new InOrganizationPageEvent.Handler() {
//			@Override
//			public void inPlace(InOrganizationPageEvent event) {
//				clientFactory.getContainer().inOrganizationPage();
//			}
//		});
//		eventBus.addHandler(InOrganizationDetailEvent.TYPE, new InOrganizationDetailEvent.Handler() {
//			@Override
//			public void inPlace(InOrganizationDetailEvent event) {
//				clientFactory.getContainer().inOrganizationDetail();
//			}
//		});
//		eventBus.addHandler(InTaskListEvent.TYPE, new InTaskListEvent.Handler() {
//			@Override
//			public void inPlace(InTaskListEvent event) {
//				clientFactory.getContainer().inTaskList();
//			}
//		});
//		eventBus.addHandler(OnStartUpEvent.TYPE, new OnStartUpEvent.Handler() {
//			@Override
//			public void inPlace(OnStartUpEvent event) {
//				clientFactory.getContainer().onStartUp();
//			}
//		});
	}

	public AppController() {
		this.clientFactory = MiniTask.clientFactory;
		this.eventBus = MiniTask.clientFactory.getEventBus();
	}

	public void run() {
//		EventManager();
		getUserProfiles();
	}
	
	private void getUserProfiles() {
		userRequest.getUserProfiles(new AsyncCallback<UserProfiles>() {
			@Override
			public void onSuccess(UserProfiles result) {
				if(result != null)  {
					Storage.userProfiles = result;
					clientFactory.getContainer().onStartUp();
				}
				handlerHistory();	
			}
			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
			}
		});
	}
	
	private void handlerHistory() {
		EventBus eventBus = MiniTask.clientFactory.getEventBus();
		PlaceController placeController = MiniTask.clientFactory.getPlaceController();

		// Start ActivityManager for the main widget with our ActivityMapper
		SimplePanel activityDisplay = new SimplePanel();
		ActivityMapper activityMapper = new AppActivityMapper();
		ActivityManager activityManager = new ActivityManager(activityMapper, eventBus);
		activityManager.setDisplay(activityDisplay);

		// Start PlaceHistoryHandler with our PlaceHistoryMapper
		Place defaultPlace = new WelcomePlace();
		AppPlaceHistoryMapper historyMapper = GWT.create(AppPlaceHistoryMapper.class);
		PlaceHistoryHandler historyHandler = new PlaceHistoryHandler(historyMapper);
		historyHandler.register(placeController, eventBus, defaultPlace);
		historyHandler.handleCurrentHistory();
	}
	
}
