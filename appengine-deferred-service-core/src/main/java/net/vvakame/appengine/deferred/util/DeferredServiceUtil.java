package net.vvakame.appengine.deferred.util;

public class DeferredServiceUtil {

	public static void throwWithValue(Object value, Throwable th) {
		throw new PersistentException(value, th);
	}

	public static void throwWithValue(Object value, String msg, Throwable th) {
		throw new PersistentException(value, msg, th);
	}
}