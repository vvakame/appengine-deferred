package net.vvakame.appengine.deferred.util;

/**
 * 処理結果を保持しつつ例外を投げたい場合のラッパ.
 * @author vvakame
 */
@SuppressWarnings("serial")
public class PersistentException extends RuntimeException {

	Object value;


	/**
	 * the constructor.
	 * @param value 返却したい値
	 * @param th 発生した本来の例外
	 * @category constructor
	 */
	public PersistentException(Object value, Throwable th) {
		super(th);
		this.value = value;
	}

	/**
	 * the constructor.
	 * @param value 返却したい値
	 * @param msg 例外メッセージ
	 * @param th 発生した本来の例外
	 * @category constructor
	 */
	public PersistentException(Object value, String msg, Throwable th) {
		super(msg, th);
		this.value = value;
	}

	/**
	 * @param <T> 
	 * @return the value
	 */
	@SuppressWarnings("unchecked")
	public <T>T getValue() {
		return (T) value;
	}
}
