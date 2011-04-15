package net.vvakame.appengine.deferred.util;

import com.google.appengine.api.taskqueue.DeferredTask;

/**
 * 非同期化用ユーティリティ.<br>
 * 例外のキャッチなど自分で処理したい場合に利用.<br>
 * 独自処理を {@link #run()} に記述し、必ず {@link #delegate()} を呼ぶこと.<br>
 * 自動生成クラスでは {@link #run()} の代わりに {@link #delegate()} に呼出しコードを生成する.
 * 
 * @author vvakame
 */
@SuppressWarnings("serial")
public abstract class OwnDeferredTask implements DeferredTask {

	public abstract void delegate();
}
