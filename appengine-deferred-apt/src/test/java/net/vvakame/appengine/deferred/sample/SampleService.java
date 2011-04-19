package net.vvakame.appengine.deferred.sample;

import java.util.ConcurrentModificationException;
import java.util.List;
import java.util.Map;

import net.vvakame.appengine.deferred.annotation.Deferred;
import net.vvakame.appengine.deferred.util.DeferredUtil;

import com.google.appengine.api.datastore.Key;

/**
 * なんか適当な処理.
 * 
 * @author vvakame
 */
public class SampleService {

	static int count = 0;

	/**
	 * テスト用メソッド
	 * 
	 * @param sample
	 * @return 適当
	 * @author vvakame
	 */
	@Deferred
	public static int hoge(long sample) {
		try {
			if ((count % 4) == 0) {
				count += sample;
				throw new IllegalStateException();
			} else {
				count += sample;
			}
		} catch (IllegalStateException e) {
			DeferredUtil.throwWithValue(count, "error!", e);
		}
		return count;
	}

	/**
	 * テスト用メソッド
	 * 
	 * @param keyBy
	 * @return 適当
	 * @author vvakame
	 */
	@Deferred
	public static List<Key> getKeys(Key keyBy) {
		return null;
	}

	/**
	 * テスト用メソッド
	 * 
	 * @param key
	 * @param keys
	 * @return 適当
	 * @author vvakame
	 */
	@Deferred
	public static Map<String, List<? extends Number>> generics(Key key,
			List<Key> keys) {

		return null;
	}

	/**
	 * テスト用メソッド
	 * 
	 * @param objs
	 * @author vvakame
	 */
	@Deferred
	public static void arrayArgs(Object... objs) {

	}

	/**
	 * テスト用メソッド
	 * 
	 * @author vvakame
	 * @throws ConcurrentModificationException
	 */
	@Deferred
	public static void throwRuntimeException()
			throws ConcurrentModificationException {

	}

	/**
	 * テスト用メソッド
	 * 
	 * @throws Exception
	 * @author vvakame
	 */
	@Deferred
	public static void throwException() throws Exception {

	}

	/**
	 * テスト用メソッド
	 * 
	 * @return 適当
	 * @author vvakame
	 */
	@Deferred
	public static boolean returnBoolean() {
		return false;
	}

	/**
	 * テスト用メソッド
	 * 
	 * @return 適当
	 * @author vvakame
	 */
	@Deferred
	public static char returnChar() {
		return 'a';
	}

	/**
	 * テスト用メソッド
	 * 
	 * @return 適当
	 * @author vvakame
	 */
	@Deferred
	public static byte returnByte() {
		return 0;
	}

	/**
	 * テスト用メソッド
	 * 
	 * @return 適当
	 * @author vvakame
	 */
	@Deferred
	public static short returnShort() {
		return 0;
	}

	/**
	 * テスト用メソッド
	 * 
	 * @return 適当
	 * @author vvakame
	 */
	@Deferred
	public static int returnInteger() {
		return 0;
	}

	/**
	 * テスト用メソッド
	 * 
	 * @return 適当
	 * @author vvakame
	 */
	@Deferred
	public static long returnLong() {
		return 0;
	}

	/**
	 * テスト用メソッド
	 * 
	 * @return 適当
	 * @author vvakame
	 */
	@Deferred
	public static Float returnFloat() {
		return 0.0f;
	}

	/**
	 * テスト用メソッド
	 * 
	 * @return 適当
	 * @author vvakame
	 */
	@Deferred
	public static Double returnDouble() {
		return 0.0;
	}
}
