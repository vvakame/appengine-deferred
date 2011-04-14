package net.vvakame.appengine.deferred.util;

@SuppressWarnings("serial")
public class PersistentException extends RuntimeException {

	Object value;

	public PersistentException(Object value, Throwable th) {
		super(th);
		this.value = value;
	}

	public PersistentException(Object value, String msg, Throwable th) {
		super(msg, th);
		this.value = value;
	}

	/**
	 * @return the value
	 */
	public Object getValue() {
		return value;
	}
}
