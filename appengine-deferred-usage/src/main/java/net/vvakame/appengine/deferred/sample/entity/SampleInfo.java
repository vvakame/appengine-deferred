package net.vvakame.appengine.deferred.sample.entity;

import org.slim3.datastore.Attribute;
import org.slim3.datastore.Model;

import com.google.appengine.api.datastore.Key;

/**
 * なんか適当なEntity.
 * 
 * @author vvakame
 */
@Model
public class SampleInfo {

	@Attribute(primaryKey = true)
	Key key;


	/**
	 * @return the key
	 * @category accessor
	 */
	public Key getKey() {
		return key;
	}

	/**
	 * @param key the key to set
	 * @category accessor
	 */
	public void setKey(Key key) {
		this.key = key;
	}
}
