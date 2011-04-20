package net.vvakame.appengine.deferred.sample.util;

import net.vvakame.appengine.deferred.util.OwnDeferredTask;

/**
 * なんか適当な処理.
 * 
 * @author vvakame
 */
@SuppressWarnings("serial")
public abstract class RaiseUnsupportedOperationDeferred extends OwnDeferredTask {

	@Override
	public void run() {
		try {
			delegate();
		} catch (RuntimeException e) {
			e.getCause().printStackTrace();
			throw e;
		}
	}
}
