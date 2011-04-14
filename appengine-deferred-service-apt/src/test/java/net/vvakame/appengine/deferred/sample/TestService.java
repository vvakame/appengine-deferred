package net.vvakame.appengine.deferred.sample;

import net.vvakame.appengine.deferred.annotation.Deferred;
import net.vvakame.appengine.deferred.util.DeferredUtil;

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
}
