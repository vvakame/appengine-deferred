package net.vvakame.appengine.deferred.sample;

import com.google.appengine.api.taskqueue.DeferredTask;
import com.google.appengine.api.taskqueue.QueueFactory;
import com.google.appengine.api.taskqueue.TaskOptions;

/**
 * {@link TestService} を遅延処理させる.
 * 
 * @author vvakame
 */
public class TestServiceDeffered {

	/**
	 * {@link TestService#test(long)} の失敗時非同期化.
	 * 
	 * @return 実行結果
	 * @author vvakame
	 * @return
	 */
	public static int test(int sample) {
		try {
			return TestService.test(sample);
		} catch (Exception e) {
			DeferredTask deferred = new Task_test_int(sample);
			QueueFactory.getDefaultQueue().add(
					TaskOptions.Builder.withPayload(deferred));
		}
		return Integer.MIN_VALUE;
	}

	@SuppressWarnings("serial")
	static class Task_test_int implements DeferredTask {

		int sample;

		Task_test_int(int sample) {
			this.sample = sample;
		}

		@Override
		public void run() {
			TestService.test(sample);
		}
	}
}
