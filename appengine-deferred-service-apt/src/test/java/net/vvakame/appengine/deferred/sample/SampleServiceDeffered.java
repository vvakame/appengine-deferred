package net.vvakame.appengine.deferred.sample;

import net.vvakame.appengine.deferred.util.PersistentException;

import com.google.appengine.api.taskqueue.DeferredTask;
import com.google.appengine.api.taskqueue.QueueFactory;
import com.google.appengine.api.taskqueue.TaskOptions;

/**
 * {@link SampleService} を遅延処理させる.
 * 
 * @author vvakame
 */
public class SampleServiceDeffered {

	/**
	 * {@link SampleService#hoge(long)} の失敗時非同期化.
	 * 
	 * @return 実行結果
	 * @author vvakame
	 * @return 実行結果(非同期化した場合も)
	 */
	public static int hoge(int sample) {
		try {
			return SampleService.hoge(sample);
		} catch (PersistentException e) {
			DeferredTask deferred = new Task_hoge_int(sample);
			QueueFactory.getDefaultQueue().add(
					TaskOptions.Builder.withPayload(deferred));
			return (Integer) e.getValue();
		}
	}

	@SuppressWarnings("serial")
	static class Task_hoge_int implements DeferredTask {

		int sample;

		Task_hoge_int(int sample) {
			this.sample = sample;
		}

		@Override
		public void run() {
			SampleService.hoge(sample);
		}
	}
}
