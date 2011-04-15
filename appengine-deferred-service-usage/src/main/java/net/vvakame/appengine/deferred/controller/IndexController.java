package net.vvakame.appengine.deferred.controller;

import net.vvakame.appengine.deferred.sample.SampleServiceDeferred;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

/**
 * サンプルコントローラ
 * @author vvakame
 */
public class IndexController extends Controller {

	@Override
	protected Navigation run() throws Exception {
		response.getWriter().println("hello, world!");
		response.flushBuffer();

		SampleServiceDeferred.hoge(1);

		return null;
	}
}
