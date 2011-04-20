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
 * {@link SampleService2} と {@link SampleService2Deferred} のテストケース.
 * @author vvakame
 */
public class SampleService2Test extends AppEngineTestCase {

	/**
	 * 動作確認
	 * @author vvakame
	 */
	@Test
	public void increment_例外発生無し() {
		SampleService2.setRaiseError(false);

		long result = SampleService2.increment(3);
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
		SampleService2.setRaiseError(true);

		long result = SampleService2.increment(8);
		assertThat(result, is(9L));

		assertThat(tester.tasks.size(), is(1));
		assertThat(tester.count(SampleInfo.class), is(0));

		TaskQueueAddRequest tq = tester.tasks.get(0);
		DeferredTask deferred = get(tq);

		SampleService2.setRaiseError(false);

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
		SampleService2.setRaiseError(false);

		SampleService2.getKeys(null);
		SampleService2.generics(null, null);
		SampleService2.throwRuntimeException();
		SampleService2.throwException();
		SampleService2.returnBoolean();
		SampleService2.returnChar();
		SampleService2.returnByte();
		SampleService2.returnShort();
		SampleService2.returnInteger();
		SampleService2.returnLong();
		SampleService2.returnFloat();
		SampleService2.returnDouble();
		SampleService2.ownTask();
	}

	/**
	 * 動作確認.
	 * @author vvakame
	 * @throws Exception 
	 */
	@Test
	public void 全メソッド叩き_例外発生() throws Exception {
		SampleService2.setRaiseError(true);

		SampleService2.getKeys(null);
		SampleService2.generics(null, null);
		SampleService2.throwRuntimeException();
		SampleService2.throwException();
		SampleService2.returnBoolean();
		SampleService2.returnChar();
		SampleService2.returnByte();
		SampleService2.returnShort();
		SampleService2.returnInteger();
		SampleService2.returnLong();
		SampleService2.returnFloat();
		SampleService2.returnDouble();
		SampleService2.ownTask();

		assertThat(tester.tasks.size(), is(13));

		SampleService2.setRaiseError(false);

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
