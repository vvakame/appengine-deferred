package net.vvakame.appengine.deferred.util;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

import com.google.appengine.api.taskqueue.DeferredTask;
import com.google.appengine.api.taskqueue.DeferredTaskContext;
import com.google.appengine.api.taskqueue.QueueFactory;
import com.google.appengine.api.taskqueue.TaskOptions;

/**
 * 非同期化用ユーティリティ
 * 
 * @author vvakame
 */
public class DeferredUtil {

	static final Logger logger = Logger.getLogger(DeferredUtil.class.getName());

	/**
	 * 現在実行中のコンテキストが {@link DeferredTask} な TQかどうかを判定し返す.
	 * 
	 * @return {@link DeferredTask} として実行中か否か
	 * @author vvakame
	 */
	public static boolean isInTask() {
		HttpServletRequest request = DeferredTaskContext.getCurrentRequest();
		if (request == null) {
			return false;
		}
		return "application/x-binary-app-engine-java-runnable-task"
				.equals(request.getHeader("content-type"));
	}

	/**
	 * 実行中の {@link DeferredTask} の名前を取得する.<br>
	 * 名前は、管理コンソールから見る時に対応関係を調べるのに利用できる.
	 * 
	 * @return タスクの名前.
	 * @author vvakame
	 */
	static String getTaskName() {
		HttpServletRequest request = DeferredTaskContext.getCurrentRequest();
		if (request == null) {
			return null;
		}
		return request.getHeader("X-AppEngine-TaskName");
	}

	/**
	 * {@link DeferredTask} を TQに突っ込む
	 * 
	 * @param deferred
	 * @author vvakame
	 */
	public static void post(DeferredTask deferred) {
		logger.log(Level.FINEST, "add DeferredTask");
		QueueFactory.getDefaultQueue().add(
				TaskOptions.Builder.withPayload(deferred));
	}

	/**
	 * {@link DeferredTask} を TQに突っ込む.<br>
	 * もし {@link DeferredTask} 実行中であれば、発生した例外を単にthrowする
	 * 
	 * @param deferred
	 * @author vvakame
	 * @throws Throwable
	 */
	public static <T extends Throwable> void post(DeferredTask deferred, T e)
			throws T {
		if (isInTask()) {
			throw e;
		} else {
			logger.log(Level.FINEST, "add DeferredTask, raise Exception.", e);
			QueueFactory.getDefaultQueue().add(
					TaskOptions.Builder.withPayload(deferred));
		}
	}
}
