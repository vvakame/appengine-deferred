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

import net.vvakame.appengine.deferred.sample.SampleService1;

import org.junit.Test;
import org.seasar.aptina.unit.AptinaTestCase;

import static org.hamcrest.CoreMatchers.*;

import static org.junit.Assert.*;

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
	@Test
	public void test() throws Exception {
		DeferredAnnotationProcessor processor = new DeferredAnnotationProcessor();
		addProcessor(processor);

		addCompilationUnit(SampleService1.class);

		compile();

		@SuppressWarnings("unused")
		String source = getGeneratedSource(SampleService1.class.getName()
				+ "Deferred");

		assertThat(getCompiledResult(), is(true));
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		// ソースパスを追加
		addSourcePath("src/test/java");
		setCharset("utf-8");
	}
}
