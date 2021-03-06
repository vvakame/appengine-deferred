package net.vvakame.appengine.deferred.sample.service;

import java.util.ConcurrentModificationException;
import java.util.List;
import java.util.Map;

import net.vvakame.appengine.deferred.annotation.Deferred;
import net.vvakame.appengine.deferred.sample.entity.SampleInfo;
import net.vvakame.appengine.deferred.sample.util.RaiseUnsupportedOperationDeferred;
import net.vvakame.appengine.deferred.util.DeferredUtil;

import org.slim3.datastore.Datastore;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.taskqueue.DeferredTask;

/**
 * なんか適当な処理.
 * 
 * @author vvakame
 */
public class SampleService2 {

	static boolean raiseError = false;


	/**
	 * @param raiseError the raiseError to set
	 * @category accessor
	 */
	public static void setRaiseError(boolean raiseError) {
		SampleService2.raiseError = raiseError;
	}

	/**
	 * テスト用メソッド
	 * @param sample
	 * @return 適当
	 * @author vvakame
	 */
	@Deferred
	public static long increment(long sample) {
		try {
			if (raiseError) {
				throw new IllegalStateException();
			}
			Datastore.put(new SampleInfo());
		} catch (IllegalStateException e) {
			DeferredTask deferred = SampleService2Deferred.increment(sample);
			DeferredUtil.post(deferred, e);
		}
		return sample + 1;
	}

	/**
	 * テスト用メソッド
	 * @param keyBy 
	 * @return 適当
	 * @author vvakame
	 */
	@Deferred
	public static List<Key> getKeys(Key keyBy) {
		try {
			if (raiseError) {
				throw new RuntimeException();
			} else {
				return null;
			}
		} catch (RuntimeException e) {
			DeferredTask deferred = SampleService2Deferred.getKeys(keyBy);
			DeferredUtil.post(deferred, e);
		}
		return null;
	}

	/**
	 * テスト用メソッド
	 * @param key 
	 * @param keys 
	 * @return 適当
	 * @author vvakame
	 */
	@Deferred
	public static Map<String, List<? extends Number>> generics(Key key, List<Key> keys) {
		try {
			if (raiseError) {
				throw new RuntimeException();
			} else {
				return null;
			}
		} catch (RuntimeException e) {
			DeferredTask deferred = SampleService2Deferred.generics(key, keys);
			DeferredUtil.post(deferred, e);
		}
		return null;
	}

	/**
	 * テスト用メソッド
	 * @author vvakame
	 * @throws ConcurrentModificationException 
	 */
	@Deferred
	public static void throwRuntimeException() throws ConcurrentModificationException {
		try {
			if (raiseError) {
				throw new ConcurrentModificationException();
			}
		} catch (RuntimeException e) {
			DeferredTask deferred = SampleService2Deferred.throwRuntimeException();
			DeferredUtil.post(deferred, e);
		}
	}

	/**
	 * テスト用メソッド
	 * @throws Exception
	 * @author vvakame
	 */
	@Deferred
	public static void throwException() throws Exception {
		try {
			if (raiseError) {
				throw new Exception();
			}
		} catch (Exception e) {
			DeferredTask deferred = SampleService2Deferred.throwException();
			DeferredUtil.post(deferred, e);
		}
	}

	/**
	 * テスト用メソッド
	 * @return 適当
	 * @author vvakame
	 */
	@Deferred
	public static boolean returnBoolean() {
		try {
			if (raiseError) {
				throw new RuntimeException();
			} else {
				return false;
			}
		} catch (RuntimeException e) {
			DeferredTask deferred = SampleService2Deferred.returnBoolean();
			DeferredUtil.post(deferred, e);
		}
		return false;
	}

	/**
	 * テスト用メソッド
	 * @return 適当
	 * @author vvakame
	 */
	@Deferred
	public static char returnChar() {
		try {
			if (raiseError) {
				throw new RuntimeException();
			} else {
				return 'a';
			}
		} catch (RuntimeException e) {
			DeferredTask deferred = SampleService2Deferred.returnChar();
			DeferredUtil.post(deferred, e);
		}
		return 'a';
	}

	/**
	 * テスト用メソッド
	 * @return 適当
	 * @author vvakame
	 */
	@Deferred
	public static byte returnByte() {
		try {
			if (raiseError) {
				throw new RuntimeException();
			} else {
				return 0;
			}
		} catch (RuntimeException e) {
			DeferredTask deferred = SampleService2Deferred.returnByte();
			DeferredUtil.post(deferred, e);
		}
		return 0;
	}

	/**
	 * テスト用メソッド
	 * @return 適当
	 * @author vvakame
	 */
	@Deferred
	public static short returnShort() {
		try {
			if (raiseError) {
				throw new RuntimeException();
			} else {
				return 0;
			}
		} catch (RuntimeException e) {
			DeferredTask deferred = SampleService2Deferred.returnShort();
			DeferredUtil.post(deferred, e);
		}
		return 0;
	}

	/**
	 * テスト用メソッド
	 * @return 適当
	 * @author vvakame
	 */
	@Deferred
	public static int returnInteger() {
		try {
			if (raiseError) {
				throw new RuntimeException();
			} else {
				return 0;
			}
		} catch (RuntimeException e) {
			DeferredTask deferred = SampleService2Deferred.returnInteger();
			DeferredUtil.post(deferred, e);
		}
		return 0;
	}

	/**
	 * テスト用メソッド
	 * @return 適当
	 * @author vvakame
	 */
	@Deferred
	public static long returnLong() {
		try {
			if (raiseError) {
				throw new RuntimeException();
			} else {
				return 0;
			}
		} catch (RuntimeException e) {
			DeferredTask deferred = SampleService2Deferred.returnLong();
			DeferredUtil.post(deferred, e);
		}
		return 0;
	}

	/**
	 * テスト用メソッド
	 * @return 適当
	 * @author vvakame
	 */
	@Deferred
	public static Float returnFloat() {
		try {
			if (raiseError) {
				throw new RuntimeException();
			} else {
				return 0.0f;
			}
		} catch (RuntimeException e) {
			DeferredTask deferred = SampleService2Deferred.returnFloat();
			DeferredUtil.post(deferred, e);
		}
		return 0.0f;
	}

	/**
	 * テスト用メソッド
	 * @return 適当
	 * @author vvakame
	 */
	@Deferred
	public static Double returnDouble() {
		try {
			if (raiseError) {
				throw new RuntimeException();
			} else {
				return 0.0;
			}
		} catch (RuntimeException e) {
			DeferredTask deferred = SampleService2Deferred.returnDouble();
			DeferredUtil.post(deferred, e);
		}
		return 0.0;
	}

	/**
	 * 動作確認
	 * @author vvakame
	 */
	@Deferred(task = RaiseUnsupportedOperationDeferred.class)
	public static void ownTask() {
		if (raiseError) {
			DeferredTask deferred = SampleService2Deferred.ownTask();
			DeferredUtil.post(deferred);
		}
	}
}
