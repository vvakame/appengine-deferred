package net.vvakame.appengine.deferred.util;

import javax.servlet.http.HttpServletRequest;

import com.google.appengine.api.taskqueue.DeferredTaskContext;

/**
 * 非同期化用ユーティリティ
 * 
 * @author vvakame
 */
public class DeferredUtil {

	static boolean inTask() {
		HttpServletRequest request = DeferredTaskContext.getCurrentRequest();
		if (request == null) {
			return false;
		}
		return "application/x-binary-app-engine-java-runnable-task"
				.equals(request.getHeader("content-type"));
	}

	static String getTaskName() {
		HttpServletRequest request = DeferredTaskContext.getCurrentRequest();
		if (request == null) {
			return null;
		}
		return request.getHeader("X-AppEngine-TaskName");
	}

	/**
	 * 値付例外の生成とthrow
	 * 
	 * @param value
	 * @param th
	 * @author vvakame
	 */
	public static void throwWithValue(Object value, RuntimeException th) {
		if (inTask()) {
			throw th;
		} else {
			throw new PersistentException(value, th);
		}
	}

	/**
	 * 値付例外の生成とthrow
	 * 
	 * @param value
	 * @param th
	 * @author vvakame
	 * @throws Throwable
	 */
	public static void throwWithValue(Object value, Exception th)
			throws Exception {
		if (inTask()) {
			throw th;
		} else {
			throw new PersistentException(value, th);
		}
	}

	/**
	 * 値付例外の生成とthrow
	 * 
	 * @param value
	 * @param th
	 * @author vvakame
	 * @throws Throwable
	 */
	public static void throwWithValue(Object value, Throwable th)
			throws Throwable {
		if (inTask()) {
			throw th;
		} else {
			throw new PersistentException(value, th);
		}
	}

	/**
	 * 値付例外の生成とthrow
	 * 
	 * @param value
	 * @param msg
	 * @param th
	 * @author vvakame
	 */
	public static void throwWithValue(Object value, String msg,
			RuntimeException th) {
		if (inTask()) {
			throw th;
		} else {
			throw new PersistentException(value, msg, th);
		}
	}

	/**
	 * 値付例外の生成とthrow
	 * 
	 * @param value
	 * @param msg
	 * @param th
	 * @author vvakame
	 */
	public static void throwWithValue(Object value, String msg, Exception th)
			throws Exception {
		if (inTask()) {
			throw th;
		} else {
			throw new PersistentException(value, msg, th);
		}
	}

	/**
	 * 値付例外の生成とthrow
	 * 
	 * @param value
	 * @param msg
	 * @param th
	 * @author vvakame
	 * @throws Throwable
	 */
	public static void throwWithValue(Object value, String msg, Throwable th)
			throws Throwable {
		if (inTask()) {
			throw th;
		} else {
			throw new PersistentException(value, msg, th);
		}
	}
}
