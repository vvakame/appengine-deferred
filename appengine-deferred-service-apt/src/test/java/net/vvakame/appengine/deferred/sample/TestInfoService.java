package net.vvakame.appengine.deferred.sample;

/**
 * なんか適当な処理.
 * 
 * @author vvakame
 */
public class TestInfoService {

	static int count = 0;

	public static int test(long sample) {
		if (count == 0) {
			count += sample;
			throw new IllegalStateException();
		} else {
			count += sample;
		}
		return count;
	}
}
