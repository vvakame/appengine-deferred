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
import java.util.List;
import java.util.Map;

import javax.annotation.processing.Filer;
import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.AnnotationValue;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.TypeMirror;
import javax.tools.JavaFileObject;

import net.vvakame.appengine.deferred.annotation.Deferred;
import net.vvakame.appengine.deferred.factory.template.Template;
import net.vvakame.appengine.deferred.util.OwnDeferredTask;
import static net.vvakame.apt.AptUtil.*;

/**
 * アノテーション処理の本体.<br>
 * 1インスタンス = 1 {@link JsonModel} の処理.
 * 
 * @author vvakame
 */
public class ClassGenerateHelper {

	static ProcessingEnvironment processingEnv = null;

	static String postfix = "Deferred";

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

	/**
	 * アノテーションの処理を行う.
	 * 
	 * @author vvakame
	 */
	public void process() {

		g.setPackageName(getPackageName(classElement));
		g.setClassName(getSimpleName(classElement));

		List<Element> methods = getEnclosedElementsByAnnotation(classElement,
				Deferred.class, ElementKind.METHOD);

		for (Element methodElement : methods) {

			ExecutableElement method = (ExecutableElement) methodElement;

			if (isPrivate(methodElement)) {
				encountError = true;
				Log.e("private method can't use.", method);
			} else if (!isStatic(method)) {
				encountError = true;
				Log.e("method must be static.", method);
			}

			MethodModel m = new MethodModel();

			String task = getDeferredTaskClassName(method);
			m.setExtendsClass(task);

			m.setName(method.getSimpleName().toString());

			for (VariableElement var : method.getParameters()) {
				ParameterModel p = new ParameterModel();
				p.setType(var.asType().toString());
				p.setSimpleType(getSimpleName(processingEnv.getTypeUtils()
						.erasure(var.asType())));
				p.setName(var.getSimpleName().toString());
				m.getParams().add(p);
			}

			m.setReturnType(method.getReturnType().toString());

			for (TypeMirror type : method.getThrownTypes()) {
				m.getThrowsTypes().add(type.toString());
			}
			g.getMethods().add(m);
		}

		System.out.println();
	}

	String getDeferredTaskClassName(Element el) {

		AnnotationValue task = null;

		for (AnnotationMirror am : el.getAnnotationMirrors()) {
			Map<? extends ExecutableElement, ? extends AnnotationValue> elementValues = am
					.getElementValues();
			for (ExecutableElement e : elementValues.keySet()) {
				if ("task".equals(e.getSimpleName().toString())) {
					task = elementValues.get(e);
				}
			}
		}

		String result = null;
		if (task != null
				&& !OwnDeferredTask.class.getCanonicalName().equals(task)) {
			String tmp = task.toString();
			if (tmp.endsWith(".class")) {
				int i = tmp.lastIndexOf('.');
				result = tmp.substring(0, i);
			} else {
				result = tmp;
			}
		}

		return result;
	}

	public boolean isEncountError() {
		return encountError;
	}

	public void write() throws IOException {
		Filer filer = processingEnv.getFiler();
		String generateClassName = classElement.asType().toString() + postfix;
		JavaFileObject fileObject = filer.createSourceFile(generateClassName,
				classElement);
		Template.write(fileObject, g);
	}
}
