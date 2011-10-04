package net.vvakame.appengine.deferred.util;

import java.util.List;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

import com.google.appengine.api.taskqueue.DeferredTask;
import com.google.appengine.api.taskqueue.DeferredTaskContext;
import com.google.appengine.api.taskqueue.Queue;
import com.google.appengine.api.taskqueue.QueueFactory;
import com.google.appengine.api.taskqueue.TaskOptions;
import com.google.appengine.repackaged.com.google.common.collect.Lists;

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
		post(null, deferred);
	}

	/**
	 * {@link DeferredTask} を TQに突っ込む
	 * 
	 * @param queueName
	 * @param deferred
	 * @author vvakame
	 */
	public static void post(String queueName, DeferredTask deferred) {
		Queue queue;
		if (queueName == null) {
			queue = QueueFactory.getDefaultQueue();
		} else {
			queue = QueueFactory.getQueue(queueName);
		}
		queue.add(TaskOptions.Builder.withPayload(deferred));
	}

	/**
	 * {@link DeferredTask} を TQに突っ込む.<br>
	 * もし {@link DeferredTask} 実行中であれば、発生した例外を単にthrowする
	 * 
	 * @param queueName
	 * @param deferred
	 * @param e
	 * @author vvakame
	 * @throws Throwable
	 */
	public static <T extends Throwable> void post(DeferredTask deferred, T e)
			throws T {
		post(null, deferred, e);
	}

	/**
	 * {@link DeferredTask} を TQに突っ込む.<br>
	 * もし {@link DeferredTask} 実行中であれば、発生した例外を単にthrowする
	 * 
	 * @param queueName
	 * @param deferred
	 * @param e
	 * @author vvakame
	 * @throws Throwable
	 */
	public static <T extends Throwable> void post(String queueName,
			DeferredTask deferred, T e) throws T {
		if (isInTask()) {
			throw e;
		} else {
			post(queueName, deferred);
		}
	}

	/**
	 * {@link DeferredTask} たちを実行する.<br>
	 * 何らかの例外が発生した場合、実行しきれなかったものはTQとして実行される(非同期実行時のタスクの処理順は不定)
	 * 
	 * @param deferreds
	 * @author vvakame
	 */
	public static void run(List<DeferredTask> deferreds) {
		run(null, deferreds);
	}

	/**
	 * {@link DeferredTask} たちを実行する.<br>
	 * 何らかの例外が発生した場合、実行しきれなかったものはTQとして実行される(非同期実行時のタスクの処理順は不定)
	 * 
	 * @param queueName
	 * @param deferreds
	 * @author vvakame
	 */
	public static void run(String queueName, List<DeferredTask> deferreds) {
		List<TaskOptions> tasks = Lists.newArrayList();
		for (DeferredTask deferred : deferreds) {
			tasks.add(TaskOptions.Builder.withPayload(deferred));
		}
		while (deferreds.size() != 0) {
			try {
				deferreds.get(0).run();
				deferreds.remove(0);
				tasks.remove(0);
			} catch (Exception e) {
				Queue queue;
				if (queueName == null) {
					queue = QueueFactory.getDefaultQueue();
				} else {
					queue = QueueFactory.getQueue(queueName);
				}

				queue.add(tasks);
				break;
			}
		}
	}

	/**
	 * {@link DeferredTask} を実行する.<br>
	 * 何らかの例外が発生した場合、実行しきれなかったものはTQとして実行される
	 * 
	 * @param deferred
	 * @author vvakame
	 */
	public static void run(DeferredTask deferred) {
		run(null, deferred);
	}

	/**
	 * {@link DeferredTask} を実行する.<br>
	 * 何らかの例外が発生した場合、実行しきれなかったものはTQとして実行される
	 * 
	 * @param queueName
	 * @param deferred
	 * @author vvakame
	 */
	public static void run(String queueName, DeferredTask deferred) {
		TaskOptions task = TaskOptions.Builder.withPayload(deferred);

		try {
			deferred.run();
		} catch (Exception e) {
			Queue queue;
			if (queueName == null) {
				queue = QueueFactory.getDefaultQueue();
			} else {
				queue = QueueFactory.getQueue(queueName);
			}

			queue.add(task);
		}
	}
}
