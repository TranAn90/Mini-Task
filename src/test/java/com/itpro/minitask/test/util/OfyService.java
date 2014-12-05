package com.itpro.minitask.test.util;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyFactory;
import com.googlecode.objectify.ObjectifyService;

public class OfyService {
	public static Objectify ofy() {
		return ObjectifyService.ofy();
	}

	public static ObjectifyFactory factory() {
		return ObjectifyService.factory();
	}

	public static DatastoreService ds() {
		DatastoreService ds = DatastoreServiceFactory.getDatastoreService();

		// Collection<Transaction> active = ds.getActiveTransactions();
		// if (active.size() > 0)
		// throw new IllegalStateException("Active is: " + active);

		return ds;
	}
}
