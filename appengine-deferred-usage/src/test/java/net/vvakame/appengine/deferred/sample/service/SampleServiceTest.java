package net.vvakame.appengine.deferred.sample.service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import net.vvakame.appengine.deferred.sample.entity.SampleInfo;

import org.junit.Test;
import org.slim3.tester.AppEngineTestCase;

import com.google.appengine.api.taskqueue.DeferredTask;
import com.google.appengine.api.taskqueue.TaskQueuePb.TaskQueueAddRequest;

import static org.hamcrest.CoreMatchers.*;

import static org.junit.Assert.*;

/**
 * {@link SampleService} と {@link SampleServiceDeferred} のテストケース.
 * @author vvakame
 */
public class SampleServiceTest extends AppEngineTestCase {

	/**
	 * 動作確認
	 * @author vvakame
	 */
	@Test
	public void increment_例外発生無し() {
		SampleService.setRaiseError(false);

		long result = SampleServiceDeferred.increment(3);
		assertThat(result, is(4L));

		assertThat(tester.tasks.size(), is(0));
		assertThat(tester.count(SampleInfo.class), is(1));
	}

	/**
	 * 動作確認
	 * @author vvakame
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */
	@Test
	public void increment_例外発生有り() throws IOException, ClassNotFoundException {
		SampleService.setRaiseError(true);

		long result = SampleServiceDeferred.increment(8);
		assertThat(result, is(9L));

		assertThat(tester.tasks.size(), is(1));
		assertThat(tester.count(SampleInfo.class), is(0));

		TaskQueueAddRequest tq = tester.tasks.get(0);
		DeferredTask deferred = get(tq);

		SampleService.setRaiseError(false);

		deferred.run();
		assertThat(tester.count(SampleInfo.class), is(1));
	}

	/**
	 * 動作確認.
	 * @author vvakame
	 * @throws Exception 
	 */
	@Test
	public void 全メソッド叩き_成功() throws Exception {
		SampleService.setRaiseError(false);

		SampleServiceDeferred.getKeys(null);
		SampleServiceDeferred.generics(null, null);
		SampleServiceDeferred.throwRuntimeException();
		SampleServiceDeferred.throwException();
		SampleServiceDeferred.returnBoolean();
		SampleServiceDeferred.returnChar();
		SampleServiceDeferred.returnByte();
		SampleServiceDeferred.returnShort();
		SampleServiceDeferred.returnInteger();
		SampleServiceDeferred.returnLong();
		SampleServiceDeferred.returnFloat();
		SampleServiceDeferred.returnDouble();
		SampleServiceDeferred.ownTask();
	}

	/**
	 * 動作確認.
	 * @author vvakame
	 * @throws Exception 
	 */
	@Test
	public void 全メソッド叩き_例外発生() throws Exception {
		SampleService.setRaiseError(true);

		SampleServiceDeferred.getKeys(null);
		SampleServiceDeferred.generics(null, null);
		SampleServiceDeferred.throwRuntimeException();
		SampleServiceDeferred.throwException();
		SampleServiceDeferred.returnBoolean();
		SampleServiceDeferred.returnChar();
		SampleServiceDeferred.returnByte();
		SampleServiceDeferred.returnShort();
		SampleServiceDeferred.returnInteger();
		SampleServiceDeferred.returnLong();
		SampleServiceDeferred.returnFloat();
		SampleServiceDeferred.returnDouble();
		SampleServiceDeferred.ownTask();

		assertThat(tester.tasks.size(), is(13));

		SampleService.setRaiseError(false);

		for (TaskQueueAddRequest tq : tester.tasks) {
			DeferredTask deferred = get(tq);
			deferred.run();
		}
	}

	static DeferredTask get(TaskQueueAddRequest tq) throws IOException, ClassNotFoundException {
		ObjectInputStream ois =
				new ObjectInputStream(new ByteArrayInputStream(tq.getBodyAsBytes()));
		DeferredTask deferred = (DeferredTask) ois.readObject();
		return deferred;
	}
}
