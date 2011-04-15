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
public class TestService {

	static int count = 0;

	@Deferred
	public static int test(long sample) {
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

	@Deferred
	public static List<Key> getKeys(Key keyBy) {
		return null;
	}

	@Deferred
	public static Map<String, List<? extends Number>> generics(Key key,
			List<Key> keys) {

		return null;
	}

	@Deferred
	public static void throwRuntimeException()
			throws ConcurrentModificationException {

	}

	@Deferred
	public static void throwException() throws Exception {

	}

	@Deferred
	public static boolean returnBoolean() {
		return false;
	}

	@Deferred
	public static char returnChar() {
		return 'a';
	}

	@Deferred
	public static byte returnByte() {
		return 0;
	}

	@Deferred
	public static short returnShort() {
		return 0;
	}

	@Deferred
	public static int returnInteger() {
		return 0;
	}

	@Deferred
	public static long returnLong() {
		return 0;
	}

	@Deferred
	public static Float returnFloat() {
		return 0.0f;
	}

	@Deferred
	public static Double returnDouble() {
		return 0.0;
	}
}
