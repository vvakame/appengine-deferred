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

package net.vvakame.appengine.deferred.factory.template;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.tools.JavaFileObject;

import net.vvakame.appengine.deferred.factory.GeneratingModel;
import net.vvakame.appengine.deferred.factory.Log;
import net.vvakame.appengine.deferred.factory.MethodModel;
import net.vvakame.appengine.deferred.factory.ParameterModel;

import org.mvel2.templates.TemplateRuntime;

/**
 * MVELを抽象化したテンプレートエンジンの表現.
 * 
 * @author vvakame
 */
public class MvelTemplate {

	private MvelTemplate() {
	}

	/**
	 * テンプレートエンジンを利用し fileObject に model の情報を流しこみソースを生成する.
	 * 
	 * @param fileObject
	 *            生成ソース
	 * @param model
	 *            ソース生成用の情報
	 * @throws IOException
	 * @author vvakame
	 */
	public static void write(JavaFileObject fileObject, GeneratingModel model)
			throws IOException {
		Map<String, Object> map = convModelToMap(model);

		Writer writer = fileObject.openWriter();
		PrintWriter printWriter = new PrintWriter(writer);
		String generated = (String) TemplateRuntime.eval(getTemplateString(),
				map);
		printWriter.write(generated);
		printWriter.flush();
		printWriter.close();
	}

	static Map<String, Object> convModelToMap(GeneratingModel model) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();

		map.put("packageName", model.getPackageName());
		map.put("className", model.getClassName());

		List<Map<String, Object>> methods = new ArrayList<Map<String, Object>>();
		for (MethodModel method : model.getMethods()) {
			Map<String, Object> toMap = convMethodElementToMap(method);
			methods.add(toMap);
		}
		map.put("methods", methods);

		return map;
	}

	private static Map<String, Object> convMethodElementToMap(MethodModel method) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("extends", method.getExtendsClass());
		map.put("name", method.getName());
		List<Map<String, Object>> params = new ArrayList<Map<String, Object>>();
		for (ParameterModel param : method.getParams()) {
			params.add(convParameterElementToMap(param));
		}
		map.put("params", params);
		map.put("return", method.getReturnType());
		map.put("throws", method.getThrowsTypes());

		StringBuilder builderArgsComma = new StringBuilder();
		StringBuilder builderCallComma = new StringBuilder();
		StringBuilder builderUnderscore = new StringBuilder();
		StringBuilder builderComma = new StringBuilder();
		for (int i = 0; i < method.getParams().size(); i++) {
			ParameterModel p = method.getParams().get(i);

			builderArgsComma.append(p.getType()).append(" ")
					.append(p.getName());
			builderCallComma.append(p.getName());
			builderUnderscore.append(p.getSimpleType());
			builderComma.append(p.getSimpleType());
			if (i < method.getParams().size() - 1) {
				builderArgsComma.append(",");
				builderCallComma.append(",");
				builderUnderscore.append("_");
				builderComma.append(",");
			}
		}
		map.put("_args", builderArgsComma.toString());
		map.put("_call", builderCallComma.toString());
		map.put("_underscore", builderUnderscore.toString());
		map.put("_comma", builderComma.toString());

		StringBuilder builderThrows = new StringBuilder();
		for (int i = 0; i < method.getThrowsTypes().size(); i++) {
			if (i == 0) {
				builderThrows.append("throws ");
			}
			builderThrows.append(method.getThrowsTypes().get(i));
			if (i < method.getThrowsTypes().size() - 1) {
				builderThrows.append(",");
			}
		}
		map.put("_throws", builderThrows.toString());

		return map;
	}

	private static Map<String, Object> convParameterElementToMap(
			ParameterModel param) {

		Map<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("name", param.getName());
		map.put("type", param.getType());

		return map;
	}

	static String getTemplateString() {
		InputStream stream = MvelTemplate.class.getClassLoader()
				.getResourceAsStream("DeferredModelGen.java.mvel");
		try {
			String template = streamToString(stream);
			// Log.d(template);
			return template;
		} catch (IOException e) {
			Log.e(e);
			return null;
		}
	}

	static String streamToString(InputStream is) throws IOException {
		if (is == null) {
			Log.e("not expected null value.");
			throw new IllegalStateException("not expected null value.");
		}
		BufferedReader br = new BufferedReader(new InputStreamReader(is,
				"utf-8"));
		StringBuilder builder = new StringBuilder();
		String line;
		while ((line = br.readLine()) != null) {
			builder.append(line).append("\n");
		}

		return builder.toString();
	}
}
