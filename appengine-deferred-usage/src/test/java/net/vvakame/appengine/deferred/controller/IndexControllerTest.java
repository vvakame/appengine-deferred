package net.vvakame.appengine.deferred.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slim3.tester.ControllerTester;

import static org.hamcrest.CoreMatchers.*;

import static org.junit.Assert.*;

/**
 * {@link IndexController} のテストケース.
 * @author vvakame
 */
public class IndexControllerTest {

	/**
	 * 動作確認
	 * @throws NullPointerException
	 * @throws IllegalArgumentException
	 * @throws IOException
	 * @throws ServletException
	 * @author vvakame
	 */
	@Test
	public void test() throws NullPointerException, IllegalArgumentException, IOException,
			ServletException {
		tester.start("/");
		assertThat(tester.response.getStatus(), is(equalTo(HttpServletResponse.SC_OK)));
	}


	ControllerTester tester;


	/**
	 * 初期化
	 * @throws Exception
	 * @author vvakame
	 */
	@Before
	public void setUp() throws Exception {
		tester = new ControllerTester(this.getClass());
		tester.setUp();
	}

	/**
	 * 後始末
	 * @throws Exception
	 * @author vvakame
	 */
	@After
	public void tearDown() throws Exception {
		tester.tearDown();
	}
}
