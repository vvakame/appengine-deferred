package net.vvakame.appengine.deferred.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 非同期化処理にしたいメソッドを修飾.
 * @author vvakame
 */
@Retention(RetentionPolicy.CLASS)
@Target({
	ElementType.METHOD
})
public @interface Deferred {
}
