/*
 * Copyright 2011 vvakame <vvakame@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package net.vvakame.appengine.deferred.factory;

import net.vvakame.appengine.deferred.sample.TestService;

import org.seasar.aptina.unit.AptinaTestCase;

/**
 * {@link DeferredAnnotationProcessor} のテスト.
 * 
 * @author vvakame
 */
public class DeferredAnnotationProcessorTest extends AptinaTestCase {

	/**
	 * 動作確認.
	 * 
	 * @throws Exception
	 * @author vvakame
	 */
	public void test() throws Exception {
		DeferredAnnotationProcessor processor = new DeferredAnnotationProcessor();
		addProcessor(processor);

		addCompilationUnit(TestService.class);

		compile();

		String source = getGeneratedSource(TestService.class.getName()
				+ "Deferred");
		System.out.println(source);
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		// ソースパスを追加
		addSourcePath("src/test/java");
		setCharset("utf-8");
	}
}
