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

import java.io.IOException;

import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.Element;

/**
 * アノテーション処理の本体.<br>
 * 1インスタンス = 1 {@link JsonModel} の処理.
 * 
 * @author vvakame
 */
public class ClassGenerateHelper {

	static ProcessingEnvironment processingEnv = null;

	static String postfix = "";

	GeneratingModel g = new GeneratingModel();

	Element classElement;

	boolean encountError = false;

	/**
	 * 初期化処理
	 * 
	 * @param env
	 * @author vvakame
	 */
	public static void init(ProcessingEnvironment env) {
		processingEnv = env;
	}

	/**
	 * インスタンス生成
	 * 
	 * @param element
	 * @return {@link ClassGenerateHelper}
	 * @author vvakame
	 */
	public static ClassGenerateHelper newInstance(Element element) {
		return new ClassGenerateHelper(element);
	}

	/**
	 * the constructor.
	 * 
	 * @param element
	 * @category constructor
	 */
	public ClassGenerateHelper(Element element) {
		classElement = element;

	}

	public void process() {
	}

	public boolean isEncountError() {
		return false;
	}

	public void write() throws IOException {
	}
}
