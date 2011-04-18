package net.vvakame.appengine.deferred.sample.util;

import net.vvakame.appengine.deferred.util.OwnDeferredTask;
import net.vvakame.appengine.deferred.util.PersistentException;

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
		} catch (PersistentException e) {
			e.getCause().printStackTrace();
			throw e;
		}
	}
}
