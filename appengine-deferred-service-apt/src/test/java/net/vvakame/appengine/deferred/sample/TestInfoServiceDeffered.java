package net.vvakame.appengine.deferred.sample;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.taskqueue.DeferredTask;
import com.google.appengine.api.taskqueue.QueueFactory;
import com.google.appengine.api.taskqueue.TaskOptions;

/**
 * {@link GeoInfoService} を遅延処理させる.
 * 
 * @author vvakame
 */
public class TestInfoServiceDeffered {

	/**
	 * {@link GeoInfoService#captureGeoInfo(Key, Key)} の失敗時非同期化.
	 * 
	 * @param lordKey
	 * @param geoKey
	 * @return 実行結果
	 * @author vvakame
	 * @return
	 */
	public static int test(int sample) {
		try {
			return TestInfoService.test(sample);
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
			TestInfoService.test(sample);
		}
	}
}
